package br.com.vinniccius.masteringextjs.interceptor;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.http.Parameter;
import br.com.caelum.vraptor.http.ParameterNameProvider;
import br.com.caelum.vraptor.serialization.Deserializee;
import br.com.caelum.vraptor.serialization.Deserializes;
import br.com.caelum.vraptor.serialization.gson.GsonDeserialization;
import br.com.caelum.vraptor.serialization.gson.GsonDeserializerBuilder;
import br.com.caelum.vraptor.view.ResultException;
import br.com.vinniccius.masteringextjs.extjs.ExtJSConsumes;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Specializes
@Deserializes({ "application/json", "json" })
public class JsonDeserializer extends GsonDeserialization {

	private static final Logger logger = LoggerFactory.getLogger(JsonDeserializer.class);

	private final GsonDeserializerBuilder builder;
	private final ParameterNameProvider paramNameProvider;
	private final HttpServletRequest request;
	private final Instance<Deserializee> deserializeeInstance;

	/** 
	 * @deprecated CDI eyes only
	 */
	protected JsonDeserializer() {
		this(null, null, null, null);
	}
	
	@Inject
	public JsonDeserializer(GsonDeserializerBuilder builder, ParameterNameProvider paramNameProvider, HttpServletRequest request, 
			Instance<Deserializee> deserializeeInstance) {
		super(null, null, null, null, null);
		this.builder = builder;
		this.paramNameProvider = paramNameProvider;
		this.request = request;
		this.deserializeeInstance = deserializeeInstance;
	}

	@Override
	public Object[] deserialize(InputStream inputStream, ControllerMethod method) {
		Class<?>[] types = getTypes(method);
		
		if (types.length == 0) {
			throw new IllegalArgumentException("Methods that consumes representations must receive just one argument");
		}

		Gson gson = builder.create();
		
		final Parameter[] parameterNames = paramNameProvider.parametersFor(method.getMethod());
		final Object[] values = new Object[parameterNames.length];
		final Deserializee deserializee = deserializeeInstance.get();

		try {
			String content = getContentOfStream(inputStream);
			logger.debug("json retrieved: {}", content);
			
			if (!isNullOrEmpty(content)) {
				JsonParser parser = new JsonParser();
				JsonElement jsonElement = parser.parse(content);
				if (jsonElement.isJsonObject()) {
					JsonObject root = jsonElement.getAsJsonObject();
					
					String rootName = "data";
					
					if (method.containsAnnotation(ExtJSConsumes.class)) {
						rootName = method.getMethod().getAnnotation(ExtJSConsumes.class).root();
					}
		
					boolean withoutRoot = isWithoutRoot(root, rootName);
					deserializee.setWithoutRoot(withoutRoot);
					
					for (int i = 0; i < types.length; i++) {
						Parameter parameter = parameterNames[i];
						
						if (withoutRoot) {
							rootName = parameter.getName();
						}
						
						JsonElement node = root.get(rootName);
						
						if (deserializee.isWithoutRoot()) { 
							values[i] = gson.fromJson(root, parameter.getParameterizedType());
							logger.info("json without root deserialized");
							break;

						} else if (node != null) {
							if (node.isJsonArray()) {
								JsonArray jsonArray= node.getAsJsonArray();
								Type type = parameter.getParameterizedType();
								if (type instanceof ParameterizedType) {
									values[i] = gson.fromJson(jsonArray, type);
								} else {
									values[i] = gson.fromJson(jsonArray, types[i]);
								}
							} else {
								values[i] = gson.fromJson(node, types[i]);
							}
						}
					}
				} else if (jsonElement.isJsonArray()) {
					if ((parameterNames.length != 1) || (!(parameterNames[0].getParameterizedType() instanceof ParameterizedType)))
						throw new IllegalArgumentException("Methods that consumes an array representation must receive only just one collection generic typed argument");

					JsonArray jsonArray= jsonElement.getAsJsonArray();
					values[0] = gson.fromJson(jsonArray, parameterNames[0].getParameterizedType());
				} else {
					throw new IllegalArgumentException("This is an invalid or not supported json content");
				}
			}
		} catch (Exception e) {
			throw new ResultException("Unable to deserialize data", e);
		}

		logger.debug("json deserialized: {}", (Object) values);
		return values;
	}

	private String getContentOfStream(InputStream input) throws IOException {
		Charset charset = Charset.forName(getRequestCharset());
		logger.debug("Using charset {}", charset);

		return CharStreams.toString(new InputStreamReader(input, charset));
	}

	private String getRequestCharset() {
		String charset = firstNonNull(request.getHeader("Accept-Charset"), "UTF-8");
		return charset.split(",")[0];
	}

	private boolean isWithoutRoot(JsonObject root, String rootName) {
		return !root.has(rootName);
	}

	protected Class<?>[] getTypes(ControllerMethod method) {
		Class<?>[] parameterTypes = method.getMethod().getParameterTypes();
		Type genericType = getGenericSuperClass(method);
		if (genericType != null) {
			return parseGenericParameters(parameterTypes, genericType);
		}

		return parameterTypes;
	}

	private Class<?>[] parseGenericParameters(Class<?>[] parameterTypes,
			Type genericType) {
		Class<?> type = (Class<?>) getGenericType(genericType);
		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isAssignableFrom(type)) {
				parameterTypes[i] = type;
			}
		}
		return parameterTypes;
	}

	private Type getGenericSuperClass(ControllerMethod method) {
		Type genericType = method.getController().getType().getGenericSuperclass();
		if (genericType instanceof ParameterizedType) {
			return genericType;
		}

		return null;
	}

	private Type getGenericType(Type type) {
		ParameterizedType paramType = (ParameterizedType) type;
		return paramType.getActualTypeArguments()[0];
	}
}

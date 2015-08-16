package br.com.vinniccius.masteringextjs.interceptor;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.MethodInfo;
import br.com.caelum.vraptor.events.InterceptorsReady;
import br.com.caelum.vraptor.ioc.Container;
import br.com.caelum.vraptor.observer.DeserializingObserver;
import br.com.caelum.vraptor.serialization.Deserializer;
import br.com.caelum.vraptor.serialization.Deserializers;

@ApplicationScoped
public class JsonDeserializingObserver extends DeserializingObserver {

	private static final String CONTENT_TYPE_JSON = "application/json";

	private static final Logger logger = getLogger(JsonDeserializingObserver.class);

	@Inject
	private Deserializers deserializers;
	
	@Inject
	private Container container;

	protected JsonDeserializingObserver() {
		super(null, null);
	}
	
	public void deserializes(@Observes InterceptorsReady event, HttpServletRequest request, MethodInfo methodInfo) throws IOException {

		ControllerMethod method = event.getControllerMethod();

		if (!accepts(request, method)) return;
		
		Deserializer deserializer = deserializers.deserializerFor(CONTENT_TYPE_JSON, container);

		Object[] deserialized = deserializer.deserialize(request.getInputStream(), method);
		logger.debug("Deserialized parameters for {} are {} ", method, deserialized);

		for (int i = 0; i < deserialized.length; i++) {
			if (deserialized[i] != null) {
				methodInfo.setParameter(i, deserialized[i]);
			}
		}
	}

	protected boolean accepts(HttpServletRequest request, ControllerMethod method) {
		return CONTENT_TYPE_JSON.equals(mime(request.getContentType())) && 
				(method.containsAnnotation(Post.class) ||
				 method.containsAnnotation(Put.class) ||
				 method.containsAnnotation(Delete.class));
	}
	
	private String mime(String contentType) {
		if (contentType == null) {
			return null;
		}
		if (contentType.contains(";")) {
			return contentType.split(";")[0];
		}
		return contentType;
	}
}
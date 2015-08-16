package br.com.vinniccius.masteringextjs.extjs.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.serialization.JSONSerialization;
import br.com.caelum.vraptor.serialization.Serializer;
import br.com.caelum.vraptor.validator.Message;
import br.com.vinniccius.masteringextjs.dto.DtoMap;
import br.com.vinniccius.masteringextjs.dto.DtoMappingRegisters;
import br.com.vinniccius.masteringextjs.extjs.Json;
import br.com.vinniccius.masteringextjs.extjs.Wrapper;

@RequestScoped
public class JsonSerializer implements Json {

	private Wrapper wrapper;
	private Serializer serializer;
	
	private JSONSerialization serialization;
	private DtoMap mapping;
	private DtoMappingRegisters registers;
	
	protected JsonSerializer() {
		this(null, null);
	}

	@Inject
	public JsonSerializer(JSONSerialization serialization, DtoMappingRegisters registers) {
		this.serialization = serialization;
		this.registers = registers;
		this.wrapper = new Wrapper();
	}
	
	@PostConstruct
	public void init() {
		this.mapping = registers.getMapping();
	}

	@Override
	public Json from(Object data) {
		if (data == null)  {
			serializer = serialization.withoutRoot().from(wrapper);
			return this;
		}
		Object json = transform(data);
		wrapper.setData(json);
		serializer = serialization.withoutRoot().from(json);
		return this;
	}

	private Object transform(Object data) {
		Class<?> modelClass = data.getClass();
		if (Collection.class.isAssignableFrom(modelClass)) {
			Collection<?> models = (Collection<?>)data;
			List<Object> dtos = new ArrayList<Object>();
			for (Object o : models) {
				dtos.add(mapping.instanceFor(o)); 
			}
			return dtos;
		} else if (mapping.contains(modelClass)) {
			return mapping.instanceFor(data);
		}
		return data;
	}
	
	@Override
	public Json serialize() {
		serialization.withoutRoot().from(wrapper).recursive().serialize();
		return this;
	}

	@Override
	public Json success() {
		wrapper.setSuccess(true);
		return this;
	}

	@Override
	public Json success(boolean success) {
		wrapper.setSuccess(success);
		return this;
	}

	@Override
	public Json total(Long total) {
		wrapper.setTotal(total);
		return this;
	}

	@Override
	public Json message(String message) {
		wrapper.setMessage(message);
		return this;
	}

	@Override
	public Json badRequest(List<Message> errors) {
		this.wrapper = new Wrapper();
		return from(errors).success(false).serialize();
	}

}

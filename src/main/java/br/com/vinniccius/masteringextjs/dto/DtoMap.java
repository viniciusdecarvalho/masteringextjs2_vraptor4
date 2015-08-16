package br.com.vinniccius.masteringextjs.dto;

import java.util.HashMap;
import java.util.Map;

public class DtoMap {

	private Map<Class<?>, Class<? extends DtoObject<?>>> mappings = 
			new HashMap<Class<?>, Class<? extends DtoObject<?>>>();
		
	public <T> void put(Class<T> modelClass, Class<? extends DtoObject<T>> dtoClass) {
		mappings.put(modelClass, dtoClass);
	}
	
	public Class<? extends DtoObject<?>> get(Class<?> type) {
		if (contains(type))
			return mappings.get(type);
		return null;
	}
	
	public boolean contains(Class<?> type) {
		if (type != null) 
			return mappings.containsKey(type);
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private <T> Dto<T> instanceFor(Class<T> modelClass) {
		try {
			return (Dto<T>) get(modelClass).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> Object instanceFor(T model) {
		Class<T> modelClass = (Class<T>) model.getClass();

		if (!contains(modelClass))
			return model;
		
		Dto<T> dto = instanceFor(modelClass);
		dto.bind(model);
		return dto;
	}
	
}   

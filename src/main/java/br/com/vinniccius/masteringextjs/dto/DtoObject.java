package br.com.vinniccius.masteringextjs.dto;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public abstract class DtoObject<TModel> implements Dto<TModel> {

	private transient TModel model;
	private transient boolean modified;

	public DtoObject() {
		modified = true;		
	}
	
	public DtoObject(TModel model) {
		this();
		bind(model);		
	}
	
	public TModel getModel() {
		if (!modified && model != null) {
			return model;
		}
		model = transform();
		modified = false;
		return model;
	}

	@Override
	public void bind(TModel model) {
		this.model = model;
		modified = false;
	}
	
	/**
	 * Call when object is created without use of contructor with args
	 * ex: deserialization 
	 * @return
	 */
	protected TModel transform() {
		return model;
	}
	
	@Override
	public String toString() {
		return "DTO: [ " + getModel().toString() + " ]";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		DtoObject<TModel> other = (DtoObject<TModel>) obj;
		return getModel().equals(other.getModel());
	}
	
	public static <T> List<T> getModels(List<? extends DtoObject<T>> dtos) {
		return Lists.transform(dtos, new Function<DtoObject<T>, T>() {
			@Override
			public T apply(DtoObject<T> input) {
				return input.getModel();
			}
		});
	}
	
}
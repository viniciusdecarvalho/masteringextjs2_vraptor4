package br.com.vinniccius.masteringextjs.dto;

public interface Dto<TModel> {

	void bind(TModel model);

}
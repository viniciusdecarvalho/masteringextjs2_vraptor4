package br.com.vinniccius.masteringextjs.extjs;

import java.util.List;

public interface Filters extends Prepared {

	void set(List<Filter> filters);

	List<Filter> get();

	boolean isEmpty();

}
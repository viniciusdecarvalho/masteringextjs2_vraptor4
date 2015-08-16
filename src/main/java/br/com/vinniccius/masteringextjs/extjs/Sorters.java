package br.com.vinniccius.masteringextjs.extjs;

import java.util.List;

public interface Sorters extends Prepared {

	void set(List<Sorter> sorters);

	List<Sorter> get();
	
}
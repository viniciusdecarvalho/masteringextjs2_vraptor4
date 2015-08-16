package br.com.vinniccius.masteringextjs.extjs;

import java.util.List;

public interface Groupers extends Prepared {

	void set(List<Grouper> groups);

	List<Grouper> get();
	
}


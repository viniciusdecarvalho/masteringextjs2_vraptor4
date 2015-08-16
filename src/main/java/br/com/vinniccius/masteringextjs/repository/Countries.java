package br.com.vinniccius.masteringextjs.repository;

import java.util.Collection;
import java.util.List;

import br.com.vinniccius.masteringextjs.model.Country;

public interface Countries {

	List<Country> list();
	
	Country get(Integer id);
	
	void saveAll(Collection<Country> actors);
	
	void delete(Country actor);
	
}

package br.com.vinniccius.masteringextjs.repository;

import java.util.Collection;
import java.util.List;

import br.com.vinniccius.masteringextjs.model.City;

public interface Cities {

	List<City> list();
	
	City get(Integer id);
	
	void saveAll(Collection<City> actors);
	
	void delete(City actor);
	
}

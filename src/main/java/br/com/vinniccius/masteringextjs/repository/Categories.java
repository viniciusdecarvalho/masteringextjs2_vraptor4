package br.com.vinniccius.masteringextjs.repository;

import java.util.Collection;
import java.util.List;

import br.com.vinniccius.masteringextjs.model.Category;

public interface Categories {

	List<Category> list();
	
	Category get(Integer id);
	
	void saveAll(Collection<Category> actors);
	
	void delete(Category actor);

	List<? extends Category> getByFilm(int filmId);
}

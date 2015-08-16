package br.com.vinniccius.masteringextjs.repository;

import java.util.Collection;
import java.util.List;

import br.com.vinniccius.masteringextjs.extjs.Filters;
import br.com.vinniccius.masteringextjs.extjs.Parameters;
import br.com.vinniccius.masteringextjs.model.Actor;

public interface Actors {

	List<Actor> list(Filters filter);
	
	Actor get(Integer id);
	
	void delete(Actor actor);

	List<Actor> getByFilm(int filmId);

	void saveAll(Collection<Actor> actors);

	Page<Actor> search(Parameters parameters);

}

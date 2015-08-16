package br.com.vinniccius.masteringextjs.repository.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.vinniccius.masteringextjs.model.Film;
import br.com.vinniccius.masteringextjs.repository.Films;

@RequestScoped
public class FilmRepository extends RepositoryImpl<Film, Integer> implements Films {

	protected FilmRepository() {
		this(null);
	}
	
	@Inject
	public FilmRepository(Session session) {
		super(session);
	}

}

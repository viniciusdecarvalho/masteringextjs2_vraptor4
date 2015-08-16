package br.com.vinniccius.masteringextjs.repository.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.vinniccius.masteringextjs.model.City;
import br.com.vinniccius.masteringextjs.repository.Cities;

@RequestScoped
public class CityRepository extends RepositoryImpl<City, Integer> implements Cities {

	protected CityRepository() {
		this(null);
	}
	
	@Inject
	public CityRepository(Session session) {
		super(session);
	}

}

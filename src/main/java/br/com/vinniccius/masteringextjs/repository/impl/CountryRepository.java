package br.com.vinniccius.masteringextjs.repository.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.vinniccius.masteringextjs.model.Country;
import br.com.vinniccius.masteringextjs.repository.Countries;

@RequestScoped
public class CountryRepository extends RepositoryImpl<Country, Integer> implements Countries {

	protected CountryRepository() {
		this(null);
	}
	
	@Inject
	public CountryRepository(Session session) {
		super(session);
	}

}

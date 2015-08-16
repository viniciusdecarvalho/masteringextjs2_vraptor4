package br.com.vinniccius.masteringextjs.repository.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.vinniccius.masteringextjs.model.Language;
import br.com.vinniccius.masteringextjs.repository.Languages;

@RequestScoped
public class LanguageRepository extends RepositoryImpl<Language, Integer> implements Languages {

	protected LanguageRepository() {
		this(null);
	}
	
	@Inject
	public LanguageRepository(Session session) {
		super(session);
	}

}

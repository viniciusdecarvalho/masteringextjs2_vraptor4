package br.com.vinniccius.masteringextjs.repository.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.vinniccius.masteringextjs.model.Category;
import br.com.vinniccius.masteringextjs.repository.Categories;

@RequestScoped
public class CategoryRepository extends RepositoryImpl<Category, Integer> implements Categories {

	protected CategoryRepository() {
		this(null);
	}
	
	@Inject
	public CategoryRepository(Session session) {
		super(session);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Category> getByFilm(int filmId) {		
		return getCachedCriteria()
				.createAlias("filmCategories", "fc")
				.add(Restrictions.eq("fc.film.id", filmId))
				.list();
	}

}

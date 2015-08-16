package br.com.vinniccius.masteringextjs.repository.impl;

import static org.hibernate.criterion.MatchMode.ANYWHERE;
import static org.hibernate.criterion.Restrictions.ilike;
import static org.hibernate.criterion.Restrictions.or;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.vinniccius.masteringextjs.extjs.Filter;
import br.com.vinniccius.masteringextjs.extjs.Filters;
import br.com.vinniccius.masteringextjs.extjs.Paginator;
import br.com.vinniccius.masteringextjs.extjs.Parameters;
import br.com.vinniccius.masteringextjs.extjs.impl.DefaultFilter;
import br.com.vinniccius.masteringextjs.model.Actor;
import br.com.vinniccius.masteringextjs.repository.Actors;
import br.com.vinniccius.masteringextjs.repository.Page;

@RequestScoped
public class ActorRepository extends RepositoryImpl<Actor, Integer> implements Actors {

	protected ActorRepository() {
		this(null);
	}
	
	@Inject
	public ActorRepository(Session session) {
		super(session);
	}

	@SuppressWarnings("unchecked")
	private List<Actor> getByFilters(Filters filters) {
		Criteria criteria = getCachedCriteria();
		filters.prepare(criteria);
		return criteria.list();
	}

	@Override
	public List<Actor> list(Filters filters) {
		if (filters.isEmpty() ) {
			return super.list();
		}
		
		return getByFilters(filters);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Actor> getByFilm(int filmId) {
		return getCachedCriteria()
				.createAlias("filmActors", "fa")
				.add(Restrictions.eq("fa.film.id", filmId))
				.list();
	}

	@Override
	public Page<Actor> search(Parameters parameters) {
		
		Paginator paginator = parameters.getPaginator();
		int start = paginator.getStart();
		int limit = paginator.getLimit();
		
		List<Filter> filters = parameters.getFilters().get();
		Filter filter = filters.stream().findFirst().orElse(new DefaultFilter(){{setValue("");}});
		String value = filter.getValue().toString();
		
		Criteria criteria = getCachedCriteria()	
			.add(or(ilike("firstName", value, ANYWHERE))
					.add(ilike("lastName", value, ANYWHERE)))			
			.addOrder(Order.asc("lastName"));
	
		Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		@SuppressWarnings("unchecked")
		List<Actor> list = criteria.setProjection(null)
				.setFirstResult(paginator.getStart())
				.setMaxResults(paginator.getLimit())
				.list();
	
		return new PageImpl<Actor>(list, start, limit, total);
	}

}

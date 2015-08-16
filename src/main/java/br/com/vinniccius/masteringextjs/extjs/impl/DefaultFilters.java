package br.com.vinniccius.masteringextjs.extjs.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;

import br.com.vinniccius.masteringextjs.extjs.Filter;
import br.com.vinniccius.masteringextjs.extjs.Filters;

@Dependent
public class DefaultFilters implements Filters {

	private List<Filter> filters;
	
	public DefaultFilters() {
		this.filters = new ArrayList<Filter>();
	}
	
	@Override
	public void prepare(Criteria criteria) {
		for (Filter filter : filters) {
			filter.prepare(criteria);
		}
	}

	@Override
	public <T> void prepare(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> root) {
		for (Filter filter : filters) {
			filter.prepare(builder, query, root);
		}
	}

	@Override
	public List<Filter> get() {
		return filters;
	}

	@Override
	public void set(List<Filter> filters) {
		this.filters = filters;
	}

	@Override
	public boolean isEmpty() {
		return filters.isEmpty();
	}
	
}
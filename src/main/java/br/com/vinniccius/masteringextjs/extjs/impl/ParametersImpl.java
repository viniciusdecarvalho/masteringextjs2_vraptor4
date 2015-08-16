package br.com.vinniccius.masteringextjs.extjs.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;

import br.com.vinniccius.masteringextjs.extjs.Filters;
import br.com.vinniccius.masteringextjs.extjs.Groupers;
import br.com.vinniccius.masteringextjs.extjs.Paginator;
import br.com.vinniccius.masteringextjs.extjs.Parameters;
import br.com.vinniccius.masteringextjs.extjs.Prepared;
import br.com.vinniccius.masteringextjs.extjs.Sorters;

public class ParametersImpl implements Parameters {

	private final Paginator paginator;
	private final Filters filters;
	private final Sorters sorters;
	private final Groupers groupers;

	public ParametersImpl(Paginator paginator, Filters filters, Sorters sorters, Groupers groupers) {
		this.filters = filters;
		this.groupers = groupers;
		this.sorters = sorters;
		this.paginator = paginator;
	}

	@Override
	public Paginator getPaginator() {
		return paginator;
	}
	
	@Override
	public Filters getFilters() {
		return filters;
	}

	@Override
	public Sorters getSorters() {
		return sorters;
	}

	@Override
	public Groupers getGroupers() {
		return groupers;
	}

	private List<Prepared> prepareds() {
		ArrayList<Prepared> prepareds = new ArrayList<Prepared>();
		prepareds.addAll(filters.get());
		prepareds.addAll(groupers.get());
		prepareds.addAll(sorters.get());
		return prepareds;
	}
	
	@Override
	public void prepare(Criteria criteria) {
		prepareds().stream().forEach(p -> p.prepare(criteria));
	}

	@Override
	public <T> void prepare(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> root) {
		prepareds().stream().forEach(p -> p.prepare(builder, query, root));
	}

}

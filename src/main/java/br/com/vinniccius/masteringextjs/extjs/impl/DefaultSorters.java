package br.com.vinniccius.masteringextjs.extjs.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;

import br.com.vinniccius.masteringextjs.extjs.Sorter;
import br.com.vinniccius.masteringextjs.extjs.Sorters;

@SuppressWarnings("serial")
public class DefaultSorters extends ArrayList<Sorter> implements Sorters {

	private List<Sorter> sorters;
	
	@Override
	public void prepare(Criteria criteria) {
		for (Sorter sorter : sorters) {
			sorter.prepare(criteria);
		}
	}

	@Override
	public <T> void prepare(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> root) {
		for (Sorter sorter : sorters) {
			sorter.prepare(builder, query, root);
		}
	}

	@Override
	public void set(List<Sorter> sorters) {
		this.sorters = sorters;
	}

	@Override
	public List<Sorter> get() {
		return sorters;
	}
	
}
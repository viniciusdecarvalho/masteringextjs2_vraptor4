package br.com.vinniccius.masteringextjs.extjs.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;

import br.com.vinniccius.masteringextjs.extjs.Grouper;
import br.com.vinniccius.masteringextjs.extjs.Groupers;

public class DefaultGroupers implements Groupers {

	private List<Grouper> groupers;
	
	@Override
	public void prepare(Criteria criteria) {
		for (Grouper grouper : groupers) {
			grouper.prepare(criteria);
		}
	}

	@Override
	public <T> void prepare(CriteriaBuilder builder, CriteriaQuery<T> query,
			Root<T> root) {
		for (Grouper grouper : groupers) {
			grouper.prepare(builder, query, root);
		}
	}

	@Override
	public void set(List<Grouper> groupers) {
		this.groupers = groupers;
	}

	@Override
	public List<Grouper> get() {
		return groupers;
	}
}
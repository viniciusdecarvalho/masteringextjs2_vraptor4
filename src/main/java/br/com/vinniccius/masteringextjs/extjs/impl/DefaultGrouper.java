package br.com.vinniccius.masteringextjs.extjs.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

import br.com.vinniccius.masteringextjs.extjs.Grouper;

public class DefaultGrouper implements Grouper {

	private String property;

	@Override
	public String getProperty() {
		return property;
	}
	
	public void setProperty(String property) {
		this.property = property;
	}
	
	@Override
	public void prepare(Criteria criteria) {
		if (property != null)
			criteria.setProjection(Projections.groupProperty(property));
	}

	@Override
	public <T> void prepare(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> root) {
		if (property != null)
			query.groupBy(root.get(property));
	}
	
}

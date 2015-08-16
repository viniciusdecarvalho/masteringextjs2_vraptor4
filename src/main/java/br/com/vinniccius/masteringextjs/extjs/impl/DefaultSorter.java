package br.com.vinniccius.masteringextjs.extjs.impl;

import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Order.desc;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import br.com.vinniccius.masteringextjs.extjs.Sorter;

public class DefaultSorter implements Sorter {

	private String direction;
	private String property;
	
	public DefaultSorter() {}

	@Override
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	private Order getOrder() {
		return isAsc() ? asc(property) : desc(property); 
	}

	private boolean isAsc() {
		return "ASC".equalsIgnoreCase(direction);
	}

	public void prepare(Criteria criteria) {
		criteria.addOrder(getOrder());
	}

	public <T> void prepare(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> root) {
		Path<Object> path = root.get(property);
		if (isAsc()) {
			builder.asc(path);
		} else {
			builder.desc(path);
		}
	}

}

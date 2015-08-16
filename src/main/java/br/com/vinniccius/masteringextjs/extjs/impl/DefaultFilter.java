package br.com.vinniccius.masteringextjs.extjs.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import br.com.vinniccius.masteringextjs.extjs.Filter;
import br.com.vinniccius.masteringextjs.extjs.Operator;

public class DefaultFilter implements Filter {

	private String property;
	private Object value;
	private String operator = "";
	
	@Override
	public void prepare(Criteria criteria) {		
		
		if (isNullOrEmpty(value) || isNullOrEmpty(property)){
			return;
		}
		
		SimpleExpression expression = null;
		
		switch (Operator.of(operator)) {
		case NOT_EQUAL:
			expression = Restrictions.ne(property, value);
			break;
		case LESS_THAN:
			expression = Restrictions.lt(property, value);
			break;
		case GREATER_THAN:
			expression = Restrictions.gt(property, value);
			break;
		case LESS_EQUAL:
			expression = Restrictions.le(property, value);
			break;
		case GREATER_EQUAL:
			expression = Restrictions.ge(property, value);
			break;
		default:
			expression = Restrictions.eq(property, value);
			break;
		}
		criteria.add(expression);
	}
	
	private boolean isNullOrEmpty(Object value) {
		if (value == null) {
			return true;
		}
		if (value.toString().isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	@Override
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public Operator getOperator() {
		return Operator.valueOf(operator.toUpperCase());
	}

	public void setOperator(Operator operator) {
		this.operator = operator.getOperator();
	}

	@Override
	public <T> void prepare(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> root) {
		if (isNullOrEmpty(value) || isNullOrEmpty(property)){
			return;
		}
		
		Path<Integer> pathAsInteger = root.get(property);
		
		Expression<Boolean> expression = null;
		
		switch (Operator.of(operator)) {
		case NOT_EQUAL:
			expression = builder.notEqual(root.get(property), value);
			break;
		case LESS_THAN:
			expression = builder.lt(pathAsInteger, valueAsInt());
			break;
		case GREATER_THAN:
			expression = builder.gt(pathAsInteger, valueAsInt());
			break;
		case LESS_EQUAL:
			expression = builder.le(pathAsInteger, valueAsInt());
			break;
		case GREATER_EQUAL:
			expression = builder.ge(pathAsInteger, valueAsInt());
			break;
		default:
			expression = builder.equal(root.get(property), value);
			break;
		}
		
		query.where(expression);
	}

	private Integer valueAsInt() {
		return Integer.valueOf(value.toString());
	}

}

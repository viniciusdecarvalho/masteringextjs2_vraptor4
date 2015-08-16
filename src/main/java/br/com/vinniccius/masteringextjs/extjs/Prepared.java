package br.com.vinniccius.masteringextjs.extjs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;

public interface Prepared {

	void prepare(Criteria criteria);
	
	<T> void prepare(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> root);

}
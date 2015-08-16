package br.com.vinniccius.masteringextjs.extjs.impl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;

import br.com.vinniccius.masteringextjs.extjs.Paginator;

public class DefaultPaginator implements Paginator {

	private int start;
	private int limit;
	private int page;
	
	private DefaultPaginator(){}
	
	public static class Builder {
		
		private int start;
		private int limit;
		private int page;
		
		public void setStart(int start) {
			this.start = start;
		}
		public void setLimit(int limit) {
			this.limit = limit;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public DefaultPaginator build() {
			DefaultPaginator paginator = new DefaultPaginator();
			paginator.start = this.start;
			paginator.limit = this.limit;
			paginator.page = this.page;
			return paginator;
		}
	}
	
	@Override
	public int getStart() {
		return start;
	}

	@Override
	public int getLimit() {
		return limit;
	}

	@Override
	public int getPage() {
		return page;
	}

	@Override
	public int getPageSize() {
		return limit - start;
	}
	
	@Override
	public void prepare(Criteria criteria) {
		if (start != 0 && limit != 0)
			criteria.setFirstResult(start).setMaxResults(limit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void prepare(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> root) {
		if (query instanceof TypedQuery) {
			if (start != 0 && limit != 0)
				((TypedQuery<T>)query).setFirstResult(start).setMaxResults(limit);
		}
	}

}

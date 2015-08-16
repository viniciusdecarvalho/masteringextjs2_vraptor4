package br.com.vinniccius.masteringextjs.repository.impl;

import java.util.List;

import br.com.vinniccius.masteringextjs.repository.Page;

public class PageImpl<T> implements Page<T> {

	private final List<T> elements;
	private final int start;
	private final int limit;
	private final long total;
	
	public PageImpl(List<T> elements, int start, int limit, long total) {
		this.elements = elements;
		this.start = start;
		this.limit = limit;
		this.total = total;
	}

	@Override
	public List<T> getElements() {
		return elements;
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
	public long getTotal() {
		return total;
	}
	
}

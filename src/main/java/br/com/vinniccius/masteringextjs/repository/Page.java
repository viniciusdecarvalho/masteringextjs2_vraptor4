package br.com.vinniccius.masteringextjs.repository;

import java.util.List;

public interface Page<T> {

	List<T> getElements();

	int getStart();

	int getLimit();

	long getTotal();

}
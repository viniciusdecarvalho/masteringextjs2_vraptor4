package br.com.vinniccius.masteringextjs.extjs;

public interface Parameters extends Prepared {

	Paginator getPaginator();

	Filters getFilters();

	Sorters getSorters();

	Groupers getGroupers();

}
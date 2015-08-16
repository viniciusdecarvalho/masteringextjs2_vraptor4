package br.com.vinniccius.masteringextjs.extjs;

public interface Paginator extends Prepared {

	int getStart();

	int getLimit();
	
	int getPage();
	
	int getPageSize();

}
package br.com.vinniccius.masteringextjs.extjs;

public interface Filter extends Prepared {

	String getProperty();

	Object getValue();

	Operator getOperator();	
	
}
package br.com.vinniccius.masteringextjs.converter;

import javax.enterprise.context.RequestScoped;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;
import br.com.vinniccius.masteringextjs.extjs.Operator;

@RequestScoped
@Convert(Operator.class)
public class OperatorConverter implements Converter<Operator> {

	@Override
	public Operator convert(String value, Class<? extends Operator> type) {		
		return Operator.of(value);
	}
	
}

package br.com.vinniccius.masteringextjs.converter;

import javax.enterprise.context.ApplicationScoped;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;
import br.com.vinniccius.masteringextjs.dto.DtoObject;

@ApplicationScoped
@Convert(DtoObject.class)
public class DtoConverter implements Converter<DtoObject<?>> {

	@Override
	public DtoObject<?> convert(String value, Class<? extends DtoObject<?>> type) {
		try {
			return type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalArgumentException("constructor without params doesn't found in " + type.getClass().getSimpleName());
		}
	}

}

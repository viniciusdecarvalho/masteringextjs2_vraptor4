package br.com.vinniccius.masteringextjs.converter;

import java.lang.reflect.Type;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;
import br.com.caelum.vraptor.serialization.gson.GsonInterfaceBuilder;
import br.com.vinniccius.masteringextjs.extjs.Filter;
import br.com.vinniccius.masteringextjs.extjs.Filters;
import br.com.vinniccius.masteringextjs.extjs.impl.DefaultFilter;
import br.com.vinniccius.masteringextjs.extjs.impl.DefaultFilters;

import com.google.common.base.Strings;
import com.google.gson.reflect.TypeToken;

@RequestScoped
@Convert(Filters.class)
public class FiltersConverter implements Converter<Filters> {

	private final GsonInterfaceBuilder gsonBuilder;
	
	protected FiltersConverter() {
		this(null);
	}
	
	@Inject
	public FiltersConverter(GsonInterfaceBuilder gsonBuilder) {
		this.gsonBuilder = gsonBuilder;
	}

	@Override
	public Filters convert(String value, Class<? extends Filters> type) {
		Filters filters = new DefaultFilters();
		
		if (Strings.isNullOrEmpty(value)) {
			return filters;
		}
		
		Type typeToken = new TypeToken<List<DefaultFilter>>(){}.getType();
		List<Filter> paramFilters = gsonBuilder.create().fromJson(value, typeToken);
		filters.set(paramFilters);

		return filters;
	}
	
}

package br.com.vinniccius.masteringextjs.converter;

import java.lang.reflect.Type;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;
import br.com.caelum.vraptor.serialization.gson.GsonInterfaceBuilder;
import br.com.vinniccius.masteringextjs.extjs.Sorter;
import br.com.vinniccius.masteringextjs.extjs.Sorters;
import br.com.vinniccius.masteringextjs.extjs.impl.DefaultSorter;
import br.com.vinniccius.masteringextjs.extjs.impl.DefaultSorters;

import com.google.common.base.Strings;
import com.google.gson.reflect.TypeToken;

@RequestScoped
@Convert(Sorters.class)
public class SortersConverter implements Converter<Sorters> {

	@Inject
	private GsonInterfaceBuilder builder;
	
	@Override
	public Sorters convert(String value, Class<? extends Sorters> type) {
		Sorters sorters = new DefaultSorters();

		if (Strings.isNullOrEmpty(value)) {
			return sorters;
		}

		Type typeToken = new TypeToken<List<DefaultSorter>>() {}.getType();
		List<Sorter> jsonSorters = builder.create().fromJson(value, typeToken);
		sorters.set(jsonSorters);
		return sorters;
	}

}

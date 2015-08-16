package br.com.vinniccius.masteringextjs.converter;

import java.lang.reflect.Type;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;
import br.com.caelum.vraptor.serialization.gson.GsonInterfaceBuilder;
import br.com.vinniccius.masteringextjs.extjs.Grouper;
import br.com.vinniccius.masteringextjs.extjs.Groupers;
import br.com.vinniccius.masteringextjs.extjs.impl.DefaultGrouper;
import br.com.vinniccius.masteringextjs.extjs.impl.DefaultGroupers;

import com.google.common.base.Strings;
import com.google.gson.reflect.TypeToken;

@RequestScoped
@Convert(Groupers.class)
public class GroupersConverter implements Converter<Groupers> {

	private final GsonInterfaceBuilder builder;
	
	protected GroupersConverter() {
		this(null);
	}

	@Inject
	public GroupersConverter(GsonInterfaceBuilder builder) {
		this.builder = builder;
	}

	@Override
	public Groupers convert(String value, Class<? extends Groupers> type) {
		Groupers groupers = new DefaultGroupers();
		
		if (Strings.isNullOrEmpty(value)) {
			return groupers;
		}
		
		Type typeToken = new TypeToken<List<DefaultGrouper>>(){}.getType();
		List<Grouper> jsonGroupers = builder.create().fromJson(value, typeToken);
		groupers.set(jsonGroupers);
		return groupers;
	}
	
}

package br.com.vinniccius.masteringextjs.converter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Strings;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;
import br.com.vinniccius.masteringextjs.extjs.Filters;
import br.com.vinniccius.masteringextjs.extjs.Groupers;
import br.com.vinniccius.masteringextjs.extjs.Operator;
import br.com.vinniccius.masteringextjs.extjs.Paginator;
import br.com.vinniccius.masteringextjs.extjs.Parameters;
import br.com.vinniccius.masteringextjs.extjs.Sorters;
import br.com.vinniccius.masteringextjs.extjs.impl.DefaultFilter;
import br.com.vinniccius.masteringextjs.extjs.impl.ParametersImpl;

@RequestScoped
@Convert(Parameters.class)
public class ParametersConverter implements Converter<Parameters> {

	private final HttpServletRequest request;
	private final FiltersConverter filterConverter;
	private final SortersConverter sorterConverter;
	private final GroupersConverter grouperConverter;
	private final PaginatorConverter paginatorConverter;

	protected ParametersConverter() {
		this(null, null, null, null, null);
	}
	
	@Inject
	public ParametersConverter(HttpServletRequest request, FiltersConverter filterConverter, SortersConverter sorterConverter, GroupersConverter grouperConverter, PaginatorConverter paginatorConverter) {
		this.request = request;
		this.filterConverter = filterConverter;
		this.sorterConverter = sorterConverter;
		this.grouperConverter = grouperConverter;
		this.paginatorConverter = paginatorConverter;
	}

	@Override
	public Parameters convert(String value, Class<? extends Parameters> type) {
		Paginator paginator = paginatorConverter.convert(null, null);		
		
		String filterParam = request.getParameter("filters");
		Filters filters = filterConverter.convert(filterParam, null);
		
		String sorterParam = request.getParameter("sorters");
		Sorters sorters = sorterConverter.convert(sorterParam, null);
		
		String grouperParam = request.getParameter("groupers");
		Groupers groupers = grouperConverter.convert(grouperParam, null);
		
		String queryParam = request.getParameter("query");
		if (!Strings.isNullOrEmpty(queryParam)) {
			DefaultFilter filter = new DefaultFilter();
			filter.setValue(queryParam);
			filter.setOperator(Operator.LIKE);
			filters.get().add(filter);
		}
		
		return new ParametersImpl(paginator, filters, sorters, groupers);
	}

}

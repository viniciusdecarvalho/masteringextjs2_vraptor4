package br.com.vinniccius.masteringextjs.converter;

import static com.google.common.base.Strings.isNullOrEmpty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.ConversionException;
import br.com.caelum.vraptor.converter.ConversionMessage;
import br.com.caelum.vraptor.converter.Converter;
import br.com.vinniccius.masteringextjs.extjs.Paginator;
import br.com.vinniccius.masteringextjs.extjs.impl.DefaultPaginator;
import br.com.vinniccius.masteringextjs.extjs.impl.DefaultPaginator.Builder;

@RequestScoped
@Convert(Paginator.class)
public class PaginatorConverter implements Converter<Paginator> {

	public static final String INVALID_MESSAGE_KEY = "is_not_a_valid_number";
	
	private final HttpServletRequest request;
	
	protected PaginatorConverter() {
		this(null);
	}
	
	@Inject
	public PaginatorConverter(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Paginator convert(String value, Class<? extends Paginator> type) {
		int start = -1, 
			limit = -1,
			page = 1;
		
		Builder paginatorBuilder = new DefaultPaginator.Builder();
		
		try {
			String startParam = request.getParameter("start");
			if (!isNullOrEmpty(startParam)) {
				start = Integer.parseInt(startParam);
			}
			paginatorBuilder.setStart(start);
			
			String limitParam = request.getParameter("limit");
			if (!isNullOrEmpty(limitParam)) {
				limit = Integer.parseInt(limitParam);
			}
			paginatorBuilder.setLimit(limit);
			
			String pageParam = request.getParameter("page");
			if (!isNullOrEmpty(pageParam)) {
				page = Integer.parseInt(pageParam);
			}
			paginatorBuilder.setStart(page);
		} catch (NumberFormatException e) {
			throw new ConversionException(new ConversionMessage(INVALID_MESSAGE_KEY, value));
		}
		
		return paginatorBuilder.build();
	}

}

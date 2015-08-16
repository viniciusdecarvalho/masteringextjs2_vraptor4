package br.com.vinniccius.masteringextjs.converter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;
import br.com.vinniccius.masteringextjs.interceptor.Login;

@Convert(Login.class)
public class LoginConverter implements Converter<Login> {

	private final HttpServletRequest request;
	
	/**
	 * @deprecated cdi eyes only
	 */
	protected LoginConverter() {
		this(null);
	}
	
	@Inject
	public LoginConverter(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Login convert(String value, Class<? extends Login> type) {
		return new Login(request.getParameter("user"), request.getParameter("password"));
	}

}

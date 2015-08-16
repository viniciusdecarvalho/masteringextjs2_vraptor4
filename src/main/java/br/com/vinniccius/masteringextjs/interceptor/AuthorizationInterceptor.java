package br.com.vinniccius.masteringextjs.interceptor;

import java.util.Arrays;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.vinniccius.masteringextjs.repository.Users;

/**
 * Interceptor to check if the user is in the session.
 */
//@RequestScoped
//@Intercepts(after=ValidatorInterceptor.class)
public class AuthorizationInterceptor {

	@Inject
	private Users users;
	
	@Accepts
	public boolean accepts(ControllerMethod method) {
		return !method.containsAnnotation(Public.class);
	}

	/**
	 * Intercepts the request and checks if there is a user logged in.
	 */
//	@AroundCall
	public void intercept(SimpleInterceptorStack stack, HttpServletRequest request, Login login) {
		
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		
//		login.setUserName(username);
//		login.setPassword(password);
		try {
			if (!users.containsLogin(login)) {
				Message msg = new I18nMessage("user", "not_logged_user");				
				throw new ValidationException(Arrays.asList(msg));
			}
		} catch (Exception e) {
			throw new InterceptionException(e);
		}
		stack.next();
	}

}

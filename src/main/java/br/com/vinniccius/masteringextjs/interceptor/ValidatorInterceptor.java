package br.com.vinniccius.masteringextjs.interceptor;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.Messages;
import br.com.vinniccius.masteringextjs.extjs.Json;

@RequestScoped
@Intercepts
public class ValidatorInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(ValidatorInterceptor.class);
	
	private Messages messages;
	private Result result;
	
	protected ValidatorInterceptor() {
		this(null, null);
	}
	
	@Inject
	public ValidatorInterceptor(Result result, Messages messages) {
		this.result = result;
		this.messages = messages;
	}

	@AroundCall
	public void intercept(SimpleInterceptorStack stack, ControllerMethod method) {
		try {		
			stack.next();
		} catch (Exception e) {
			String message = Throwables.getRootCause(e).getMessage();
			LOG.warn("controller: {} exception: {}", method, message);
			
			List<Message> errors = messages.handleErrors();
			
			result.use(Json.class).from(errors)
				.message(message)
				.success(false)
				.serialize();
		}
		
	}
	
}

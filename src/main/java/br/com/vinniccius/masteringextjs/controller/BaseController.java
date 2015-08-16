package br.com.vinniccius.masteringextjs.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.View;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.vinniccius.masteringextjs.dto.DtoObject;
import br.com.vinniccius.masteringextjs.extjs.Json;
import br.com.vinniccius.masteringextjs.repository.Page;

@RequestScoped
public class BaseController {

	public static final String APPLICATION_JSON = "application/json";
	
	private final Result result;
	private final Validator validator;
	
	/**
	 * @deprecated cdi eyes only
	 */
	protected BaseController() {
		this(null, null);
	}
	
	@Inject
	public BaseController(Result result, Validator validator) {
		this.result = result;
		this.validator = validator;
	}
	
	protected Result include(String key, Object value) {
		return result.include(key, value);
	}

	protected Result include(Object value) {
		return result.include(value);
	}

	protected <T extends View> T use(Class<T> view) {
		return result.use(view);
	}

	protected Result on(Class<? extends Exception> exception) {
		return result.on(exception);
	}

	protected boolean used() {
		return result.used();
	}

	protected Map<String, Object> included() {
		return result.included();
	}

	protected void forwardTo(String uri) {
		result.forwardTo(uri);
	}

	protected void redirectTo(String uri) {
		result.redirectTo(uri);
	}

	protected <T> T forwardTo(Class<T> controller) {
		return result.forwardTo(controller);
	}

	protected <T> T redirectTo(Class<T> controller) {
		return result.redirectTo(controller);
	}

	protected <T> T of(Class<T> controller) {
		return result.of(controller);
	}

	protected <T> T redirectTo(T controller) {
		return result.redirectTo(controller);
	}

	protected <T> T forwardTo(T controller) {
		return result.forwardTo(controller);
	}

	protected <T> T of(T controller) {
		return result.of(controller);
	}

	protected void nothing() {
		result.nothing();
	}

	protected void notFound() {
		result.notFound();
	}

	protected void permanentlyRedirectTo(String uri) {
		result.permanentlyRedirectTo(uri);
	}

	protected <T> T permanentlyRedirectTo(Class<T> controller) {
		return result.permanentlyRedirectTo(controller);
	}

	protected <T> T permanentlyRedirectTo(T controller) {
		return result.permanentlyRedirectTo(controller);
	}
	
	protected Validator ensure(boolean expression, Message message) {
		return validator.ensure(expression, message);
	}
	
	protected Validator check(boolean expression, Message message) {
		return validator.check(expression, message);
	}

	protected Validator addIf(boolean expression, Message message) {
		return validator.addIf(expression, message);
	}

	protected Validator validate(Object object, Class<?>... groups) {
		return validator.validate(object, groups);
	}

	protected Validator validate(String alias, Object object, Class<?>... groups) {
		return validator.validate(alias, object, groups);
	}
	
	protected Validator add(Message message) {
		return validator.add(message);
	}

	protected Validator addAll(Collection<? extends Message> messages){
		return validator.addAll(messages);
	}

	protected <T> Validator addAll(Set<ConstraintViolation<T>> errors){
		return validator.addAll(errors);
	}

	protected <T> Validator addAll(String alias, Set<ConstraintViolation<T>> errors) {
		return validator.addAll(alias, errors);
	}

	protected List<Message> getErrors() {
		return validator.getErrors();
	}

	protected boolean hasErrors() {
		return validator.hasErrors();
	}

	protected <T extends View> T onErrorUse(Class<T> view) {
		return validator.onErrorUse(view);
	}
	
	protected <T> T onErrorForwardTo(Class<T> controller) {
		return validator.onErrorForwardTo(controller);
	}

	protected <T> T onErrorForwardTo(T controller) {
		return validator.onErrorForwardTo(controller);
	}

	protected <T> T onErrorRedirectTo(Class<T> controller) {
		return validator.onErrorRedirectTo(controller);
	}
	
	protected <T> T onErrorRedirectTo(T controller) {
		return validator.onErrorRedirectTo(controller);
	}

	protected <T> T onErrorUsePageOf(Class<T> controller) {
		return validator.onErrorUsePageOf(controller);
	}
	
	protected <T> T onErrorUsePageOf(T controller) {
		return validator.onErrorUsePageOf(controller);
	}

	protected void onErrorSendBadRequest() {
		validator.onErrorSendBadRequest();
	}
	
	protected Message message(String category, String message, Object... messageParameters) {
		return new SimpleMessage(category, message, messageParameters);
	}
	
	protected Message message(String category, String message, Severity severity, Object... messageParameters) {
		return new SimpleMessage(category, message, severity ,messageParameters);
	}
	
	protected Message message(String message, Object... messageParameters) {
		return new SimpleMessage("error", message, messageParameters);
	}
	
	protected Json json() {
		return use(Json.class);
	}
	
	protected Json json(Object object, boolean success, String message, Long total) {	
		return json().from(object).message(message).total(total).success(success).serialize();
	}
	
	protected Json json(Object object, boolean success, String message) {	
		return json(object, success, message, null);
	}
	
	protected Json json(Object object, boolean success) {
		return json(object, success, null);
	}
	
	protected Json json(Object object) {
		return json(object, true, null);
	}

	protected Json json(Object data, String message) {
		return json(data, true, message);
	}
	
	protected Json json(String message, boolean success) {
		return json(null, success, message);
	}
	
	protected Json json(String message) {
		return json(message, true);
	}
	
	protected Json json(Page<?> page) {		
		return json(page.getElements(), true, null, page.getTotal());
	}
	
	protected <T> List<T> modelsFor(List<? extends DtoObject<T>> dtos) {
		return DtoObject.getModels(dtos);
	}
}
package br.com.vinniccius.masteringextjs.specialize;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import br.com.caelum.vraptor.http.route.PathAnnotationRoutesParser;
import br.com.caelum.vraptor.http.route.Router;

@Specializes 
@ApplicationScoped
public class RestRoutesParser extends PathAnnotationRoutesParser {

	/** 
	 * @deprecated CDI eyes only
	 */
	protected RestRoutesParser() {
		this(null);
	}
	
	@Inject
	public RestRoutesParser(Router router) {
		super(router);
	}
	
    protected String defaultUriFor(String controllerName, String methodName) {
		return controllerName;
    }    
}
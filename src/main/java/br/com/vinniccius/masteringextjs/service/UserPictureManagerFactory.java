package br.com.vinniccius.masteringextjs.service;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.events.VRaptorInitialized;

@ApplicationScoped
public class UserPictureManagerFactory {

	private final ServletContext context;
	private final Environment environment;
	private String profileImages;
	
	/**
	 * @deprecated cdi eyes only
	 */
	protected UserPictureManagerFactory() {	
		this(null, null);
	}
	
	@Inject
	public UserPictureManagerFactory(Environment environment, ServletContext context) {
		this.environment = environment;
		this.context = context;
	}

	@PostConstruct
	public void init() {
		profileImages = context.getRealPath("/") + environment.get("user.profile.images");		
	}
	
	public void insert(@Observes VRaptorInitialized event) {
		if (!new File(profileImages).isDirectory()) {
			throw new IllegalStateException("Pasta de armazenamento de imagens dos usuarios nao encontrada.");
		}
	}
	
	@Produces
	@ApplicationScoped
	public UserPictureManager create() {
		return new UserPictureManagerImpl(profileImages);
	}
	
}

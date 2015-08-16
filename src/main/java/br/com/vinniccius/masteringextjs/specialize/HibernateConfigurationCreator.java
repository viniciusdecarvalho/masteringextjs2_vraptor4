package br.com.vinniccius.masteringextjs.specialize;

import java.net.URL;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.hibernate.ConfigurationCreator;

@Specializes
@ApplicationScoped
public class HibernateConfigurationCreator extends ConfigurationCreator {

	private Environment environment;

	/**
	 * @deprecated cdi eyes
	 */
	public HibernateConfigurationCreator() {
		this(null);
	}
	
	@Inject
	public HibernateConfigurationCreator(Environment environment) {
		super(environment);
		this.environment = environment;
	}
	
	@Override
	protected URL getHibernateCfgLocation() {
		String hibernateCfg = environment.get("hibernate.cfg");
		return environment.getResource(hibernateCfg);
	}
	

	@Override
	@Produces
	@ApplicationScoped
	public Configuration getInstance() {
		return super.getInstance();
	}
}

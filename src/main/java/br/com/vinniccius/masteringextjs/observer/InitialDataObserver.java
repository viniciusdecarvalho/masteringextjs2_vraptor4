package br.com.vinniccius.masteringextjs.observer;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.events.VRaptorInitialized;
import br.com.vinniccius.masteringextjs.model.Group;
import br.com.vinniccius.masteringextjs.model.User;

@Dependent
public class InitialDataObserver {
	
	private SessionFactory factory;

	@Inject 
	public InitialDataObserver(SessionFactory factory) {
		this.factory = factory;
	}

	public void insert(@Observes VRaptorInitialized event) {

		Session session = null;
		
		try {
			
			session = factory.openSession();
			
			if (adminExists(session)) {
				return;
			}
			
			session.getTransaction().begin();
			
			Group group = new Group(1);
			
			User admin = new User();
			admin.setName("Administrator");
			admin.setUserName("admin");
			admin.setPassword("@Senha1");
			admin.setEmail("admin@masteringextjsjava.com");
			admin.setGroup(group);
			
			session.persist(admin);
			session.getTransaction().commit();
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		} 
	}

	private boolean adminExists(Session session) {
		return session.createCriteria(User.class).add(Restrictions.eq(User.PROPERTY_USERNAME, "admin")).uniqueResult() != null;
	}
}
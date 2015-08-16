package br.com.vinniccius.masteringextjs.repository.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;

import br.com.vinniccius.masteringextjs.model.Group;
import br.com.vinniccius.masteringextjs.model.Menu;
import br.com.vinniccius.masteringextjs.repository.Groups;

@RequestScoped
public class GroupRepository extends RepositoryImpl<Group, Integer> implements Groups {

	protected GroupRepository() {
		this(null);
	}
	
	@Inject
	public GroupRepository(Session session) {
		super(session);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> rootMenu(Integer groupId) {
		return query("select m from Menu m where m.menu is null")
				.list();
	}
	
}

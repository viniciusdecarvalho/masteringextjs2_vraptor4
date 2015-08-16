package br.com.vinniccius.masteringextjs.repository.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.vinniccius.masteringextjs.interceptor.Login;
import br.com.vinniccius.masteringextjs.model.Menu;
import br.com.vinniccius.masteringextjs.model.User;
import br.com.vinniccius.masteringextjs.repository.Users;

@RequestScoped
public class UserRepository extends RepositoryImpl<User, Integer> implements Users {

	protected UserRepository() {
		this(null);
	}
	
	@Inject
	public UserRepository(Session session) {
		super(session);
	}

	@Override
	public User login(Login login) {
		User user = null;
		try {
			user = (User) getSession().createCriteria(User.class)
						      .add(Restrictions.eq("userName", login.getUser()))
						      .add(Restrictions.eq("password", login.getPassword()))
						      .uniqueResult();
		} catch (HibernateException e) {
			throw new IllegalArgumentException("Falha na autenticacao, usuario e/ou senha invalidos");
		}
		
		if (user == null) {
			throw new IllegalArgumentException("Falha na autenticacao, usuario e/ou senha invalidos");
		}
		
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getRootMenu(User user) {
		StringBuilder hql = new StringBuilder();
		hql.append("select m from Menu m where exists(");
		hql.append("	select u.group from User u where u.userName = :userName");
		hql.append(	")and m.menu is null ");
		return query(hql.toString())
				.setParameter("userName", user.getUserName())
				.list();
	}

	@Override
	public boolean containsLogin(Login login) {
		try {
			return login(login) != null;
		} catch (Exception e) {
			return false;
		}
	}

}

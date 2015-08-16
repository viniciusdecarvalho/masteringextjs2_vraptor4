package br.com.vinniccius.masteringextjs.repository;

import java.util.List;

import br.com.vinniccius.masteringextjs.interceptor.Login;
import br.com.vinniccius.masteringextjs.model.Menu;
import br.com.vinniccius.masteringextjs.model.User;

public interface Users {

	User login(Login login);
	
	User get(Integer id);

	List<Menu> getRootMenu(User user);
	
	List<User> list();

	boolean containsLogin(Login login);
	
	void delete(Integer id);
	
	void save(User user);
	
}

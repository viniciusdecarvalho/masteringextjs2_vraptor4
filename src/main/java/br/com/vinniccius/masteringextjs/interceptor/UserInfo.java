package br.com.vinniccius.masteringextjs.interceptor;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import com.google.common.base.Objects;

import br.com.vinniccius.masteringextjs.model.Menu;
import br.com.vinniccius.masteringextjs.model.User;

@SessionScoped
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -5202317776670109943L;
	private User user;
	private List<Menu> rootMenu;
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setRootMenu(List<Menu> rootMenu) {
		this.rootMenu = rootMenu;
	}
	
	public User getUser() {
		return user;
	}
	
	public void logout() {
		this.user = null;
		this.rootMenu = null;
	}
	
	public List<Menu> getRootMenu() {
		checkArgument(user != null, "Usuario nao logado.");
		return rootMenu;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(UserInfo.class)
				.add("user", user)
				.add("menu", rootMenu)
				.toString();
	}
	
}

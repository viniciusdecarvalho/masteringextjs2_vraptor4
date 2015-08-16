package br.com.vinniccius.masteringextjs.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="groups")
public class Group extends DefaultModel<Integer> {
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String name;

	@ManyToMany(mappedBy="permissions", fetch=FetchType.EAGER)
	private List<Menu> menus;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="group")
	private List<User> users;

    public Group() {
    	menus = new ArrayList<>();
    	users = new ArrayList<>();
    }

	public Group(Integer id) {
		setId(id);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
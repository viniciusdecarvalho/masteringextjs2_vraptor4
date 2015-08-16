package br.com.vinniccius.masteringextjs.dto;

import br.com.vinniccius.masteringextjs.model.User;

public class DtoUser extends DtoObject<User> {

	private int id;
	private String name;
	private String userName;
	private String email;
	private String picture;
	private DtoGroup group;
	private String password;

	public DtoUser() {
		super();
	}
	
	public DtoUser(User user) {
		super();
		bind(user);
	}

	@Override
	public void bind(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.group = new DtoGroup(user.getGroup());
		this.userName = user.getUserName();
		this.email = user.getEmail();
		this.picture = user.getPicture();
		this.password = user.getPassword();
	}

	@Override
	protected User transform() {
		User user = new User();
		user.setId(id);
		user.setGroup(group.getModel());
		user.setName(name);
		user.setUserName(userName);
		user.setEmail(email);
		user.setPicture(picture);
		user.setPassword(password);
		return user;
	}

}

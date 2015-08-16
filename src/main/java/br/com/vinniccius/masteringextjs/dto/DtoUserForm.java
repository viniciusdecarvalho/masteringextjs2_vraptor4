package br.com.vinniccius.masteringextjs.dto;

import br.com.vinniccius.masteringextjs.model.Group;
import br.com.vinniccius.masteringextjs.model.User;

public class DtoUserForm {

	private int id;
	private String name;
	private String password;
	private String userName;
	private String email;
	private String picture;
	private Integer groupId;

	public User getUserModel() {
		User user = new User();
		user.setId(id);
		user.setGroup(new Group(groupId));
		user.setName(name);
		user.setUserName(userName);
		user.setEmail(email);
		user.setPicture(picture);
		user.setPassword(password);
		return user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getGroup() {
		return groupId;
	}

	public void setGroup(Integer groupId) {
		this.groupId = groupId;
	}

}

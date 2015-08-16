package br.com.vinniccius.masteringextjs.interceptor;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

import org.hibernate.validator.constraints.NotEmpty;

@RequestScoped
public class Login implements Serializable {

	private static final long serialVersionUID = -6650335305322136271L;
	
	@NotEmpty
	private String userName;
	
	@NotEmpty
	private String password;

	public Login(String username, String password) {
		setUser(username);
		setPassword(password);
	}

	@Override
	public String toString() {
		return "Login [" + (userName != null ? "userName=" + userName + ", " : "")
				+ (password != null ? "password=" + password : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public String getUser() {
		return userName;
	}

	public void setUser(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}

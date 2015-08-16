package br.com.vinniccius.masteringextjs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * The primary key class for the user database table.
 * 
 */
//@Embeddable
public class UserPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer userId;

	@Column(name = "Group_id")
	private Integer groupId;

	public UserPK() {
	}

	public UserPK(Integer userId, Integer groupId) {
		this.userId = userId;
		this.groupId = groupId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int id) {
		this.userId = id;
	}

	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int group_id) {
		this.groupId = group_id;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserPK)) {
			return false;
		}
		UserPK castOther = (UserPK) other;
		return (this.userId == castOther.userId) && (this.groupId == castOther.groupId);

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.groupId;

		return hash;
	}
}
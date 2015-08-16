package br.com.vinniccius.masteringextjs.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="actor")
public class Actor implements Model<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="actor_id")
	private Integer id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="last_update", updatable=false, insertable=false)
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="actor", fetch=FetchType.LAZY)
	private Set<FilmActor> filmActors;

    public Actor() {
    	filmActors = new HashSet<FilmActor>();
    }

	public Actor(Integer id) {
		setId(id);
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Set<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(Set<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}

	@Override
	public String toString() {
		return String.format("%s %s", getFirstName(), getLastName());
	}
	
}
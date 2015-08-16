package br.com.vinniccius.masteringextjs.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="language")
public class Language implements Model<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="language_id")
	private Integer id;

	@Column(name="last_update", updatable=false, insertable=false)
	private Timestamp lastUpdate;

	private String name;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="language1")
	private Set<Film> films1;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="language2")
	private Set<Film> films2;

    public Language() {
    }

	public Language(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Film> getFilms1() {
		return this.films1;
	}

	public void setFilms1(Set<Film> films1) {
		this.films1 = films1;
	}
	
	public Set<Film> getFilms2() {
		return this.films2;
	}

	public void setFilms2(Set<Film> films2) {
		this.films2 = films2;
	}
	
}
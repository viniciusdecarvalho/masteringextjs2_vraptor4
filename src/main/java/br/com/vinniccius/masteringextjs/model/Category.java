package br.com.vinniccius.masteringextjs.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category  implements Model<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	private Integer id;

	@Column(name="last_update", updatable=false, insertable=false)
	private Timestamp lastUpdate;

	private String name;

	//bi-directional many-to-one association to FilmCategory
	@OneToMany(mappedBy="category")
	private Set<FilmCategory> filmCategories;

    public Category() {
    	filmCategories = new HashSet<FilmCategory>();
    }

	public Category(Integer id) {
		this();
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

	public Set<FilmCategory> getFilmCategories() {
		return this.filmCategories;
	}

	public void setFilmCategories(Set<FilmCategory> filmCategories) {
		this.filmCategories = filmCategories;
	}
	
}
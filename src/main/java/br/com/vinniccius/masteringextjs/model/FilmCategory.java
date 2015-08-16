package br.com.vinniccius.masteringextjs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the film_category database table.
 * 
 */
@Entity
@Table(name="film_category")
public class FilmCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilmCategoryPK id = new FilmCategoryPK();

	@Column(name="last_update", updatable=false, insertable=false)
	private Timestamp lastUpdate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id", insertable=false, updatable=false)
	private Category category;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="film_id", insertable=false, updatable=false)
	private Film film;

    public FilmCategory() {
    }

	public FilmCategory(Film film, Category category) {
		setFilm(film);
		setCategory(category);
	}

	public FilmCategoryPK getId() {
		return this.id;
	}

	public void setId(FilmCategoryPK id) {
		this.id = id;
	}
	
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
		this.id.setCategoryId(category.getId());
	}
	
	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
		this.id.setFilmId(film.getId());
	}
	
}
package br.com.vinniccius.masteringextjs.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the film_actor database table.
 * 
 */
@Entity
@Table(name="film_actor")
public class FilmActor implements Serializable {
	private static final long serialVersionUID = 1L;

	public FilmActor(Film film, Actor actor) {
		setFilm(film);
		setActor(actor);
	}
	
	@EmbeddedId
	private FilmActorPK id = new FilmActorPK();

	@Column(name="last_update", updatable=false, insertable=false)
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to Actor
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="actor_id", insertable=false, updatable=false)
	private Actor actor;

	//bi-directional many-to-one association to Film
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="film_id", insertable=false, updatable=false)
	private Film film;

    public FilmActor() {
    }

	public FilmActor(FilmActorPK id) {
		this.id = id;
	}

	public FilmActorPK getId() {
		return this.id;
	}

	public void setId(FilmActorPK id) {
		this.id = id;
	}
	
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Actor getActor() {
		return this.actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
		this.id.setActorId(actor.getId());
	}
	
	public Film getFilm() {
		return this.film;		
	}

	public void setFilm(Film film) {
		this.film = film;
		this.id.setFilmId(film.getId());
	}
	
}
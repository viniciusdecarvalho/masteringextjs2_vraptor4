package br.com.vinniccius.masteringextjs.dto;

import java.sql.Timestamp;

import br.com.vinniccius.masteringextjs.model.Actor;
import br.com.vinniccius.masteringextjs.model.Film;
import br.com.vinniccius.masteringextjs.model.FilmActor;

import com.google.gson.annotations.Expose;

public class DtoFilmActor extends DtoObject<FilmActor> {
	
	private Integer actorId;
	private Integer filmId;
	
	@Expose(serialize=true, deserialize= false)
	private Timestamp lastUpdate;
	
	public DtoFilmActor() {
		super();
	}
	
	public DtoFilmActor(FilmActor filmActor) {
		super();
		bind(filmActor);
	}

	public void bind(FilmActor filmActor) {
		actorId = filmActor.getActor().getId();
		filmId = filmActor.getFilm().getId();
		lastUpdate = filmActor.getLastUpdate();
	}

	@Override
	protected FilmActor transform() {
		FilmActor filmActor = new FilmActor();		
		filmActor.setActor(new Actor(actorId));
		filmActor.setFilm(new Film(filmId));
		return filmActor;
	}

}

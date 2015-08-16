package br.com.vinniccius.masteringextjs.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.vinniccius.masteringextjs.model.Actor;

@Entity
@Table(name="actor_info")
public class ActorInfo extends Actor {

	private static final long serialVersionUID = -6754036642168909351L;
	
	@Column(name="film_info")
	private String filmInfo;

	public String getFilmInfo() {
		return filmInfo;
	}

	public void setFilmInfo(String filmInfo) {
		this.filmInfo = filmInfo;
	}
	
}

package br.com.vinniccius.masteringextjs.dto;

import java.sql.Timestamp;

import br.com.vinniccius.masteringextjs.model.Actor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DtoActor extends DtoObject<Actor> {

	@SerializedName("actor_id")
	private Integer id;
	
	@SerializedName("first_name")
	private String firstName;
	
	@SerializedName("last_name")
	private String lastName;
	
	@Expose(serialize=true, deserialize= false)
	private Timestamp lastUpdate;
	
	public DtoActor() {
		super();
	}
	
	public DtoActor(Actor actor) {
		super();
		bind(actor);
	}

	public void bind(Actor actor) {
		id = actor.getId();
		firstName = actor.getFirstName();
		lastName = actor.getLastName();
		lastUpdate = actor.getLastUpdate();
	}

	public Actor transform() {
		Actor actor = new Actor();
		actor.setId(id);
		actor.setFirstName(firstName);
		actor.setLastName(lastName);
		return actor;
	}

	@Override
	public String toString() {
		return "ActorDto [id=" + id + ", name=" + firstName + " " + lastName + "]";
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	
}

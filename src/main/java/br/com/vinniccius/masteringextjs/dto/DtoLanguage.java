package br.com.vinniccius.masteringextjs.dto;

import java.sql.Timestamp;

import br.com.vinniccius.masteringextjs.model.Language;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DtoLanguage extends DtoObject<Language> {

	@SerializedName("language_id")
	private Integer id;
	
	@SerializedName("name")
	private String name;

	@Expose(deserialize = false, serialize = true)
	private Timestamp lastUpdate;

	public DtoLanguage(Language language) {
		super();
		bind(language);
	}

	public DtoLanguage() {
		super();
	}
	
	public void bind(Language language) {
		id = language.getId();
		name = language.getName();
		lastUpdate = language.getLastUpdate();
	}

	@Override
	protected Language transform() {
		Language language = new Language();
		language.setId(id);
		language.setName(name);
		return language;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

}
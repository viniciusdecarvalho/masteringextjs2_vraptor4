package br.com.vinniccius.masteringextjs.dto;

import java.sql.Timestamp;

import br.com.vinniccius.masteringextjs.model.Country;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DtoCountry extends DtoObject<Country> {

	@SerializedName("country_id")
	private int id;
	
	@SerializedName("country")
	private String country;
	
	@Expose(deserialize=false, serialize=true)
	private Timestamp lastUpdate;
	
	public DtoCountry() {
		super();
	}
	
	public DtoCountry(Country country) {
		super();
		bind(country);
	}

	@Override
	public void bind(Country country) {
		this.id = country.getId();
		this.country = country.getCountry();
		this.lastUpdate = country.getLastUpdate();
	}

	@Override
	protected Country transform() {
		Country country = new Country();
		country.setId(id);
		country.setCountry(this.country);
		country.setLastUpdate(lastUpdate);
		return country;
	}

	public int getId() {
		return id;
	}

	public String getCountry() {
		return country;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

}
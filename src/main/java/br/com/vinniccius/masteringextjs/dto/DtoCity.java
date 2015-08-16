package br.com.vinniccius.masteringextjs.dto;

import java.sql.Timestamp;

import br.com.vinniccius.masteringextjs.model.City;
import br.com.vinniccius.masteringextjs.model.Country;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DtoCity extends DtoObject<City> {

	@SerializedName("city_id")
	private Integer id;
	
	@SerializedName("city")
	private String city;
	
	@SerializedName("country_id")
	private Integer countryId;
	
	@Expose(deserialize=false, serialize=true)
	private Timestamp lastUpdate;
	
	public DtoCity() {
		super();
	}
	
	public DtoCity(City city) {
		super(city);
		bind(city);
	}

	@Override
	public void bind(City city) {
		id = city.getId();
		this.city = city.getCity();
		countryId = city.getCountry().getId();
		lastUpdate = city.getLastUpdate();
	}

	@Override
	protected City transform() {
		City city = new  City();
		city.setId(id);
		city.setCity(this.city);
		city.setCountry(new Country(countryId));
		city.setLastUpdate(lastUpdate);
		return city;
	}

	public Integer getId() {
		return id;
	}

	public String getCity() {
		return city;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	
}
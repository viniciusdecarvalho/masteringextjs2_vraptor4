package br.com.vinniccius.masteringextjs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.vinniccius.masteringextjs.dto.DtoCity;
import br.com.vinniccius.masteringextjs.dto.validation.DtoValidation;
import br.com.vinniccius.masteringextjs.extjs.ExtJSConsumes;
import br.com.vinniccius.masteringextjs.extjs.Json;
import br.com.vinniccius.masteringextjs.model.City;
import br.com.vinniccius.masteringextjs.repository.Cities;

@Controller
public class CitiesController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CitiesController.class);
	private Cities cities;
	
	protected CitiesController() {
		this(null, null, null);
	}
	
	@Inject
	public CitiesController(Result result, Validator validator, Cities cities) {
		super(result, validator);
		this.cities = cities;
	}
	
	@Transactional
	@ExtJSConsumes
	@Put
	@Post
	@Path({"cities", "cities/{id}"})
	public Json save(@Valid @DtoValidation List<DtoCity> dtos) {
		List<City> cities = modelsFor(dtos);
		this.cities.saveAll(cities);
		logger.debug(cities.toString() + " saved.");	
		return json(cities);
	}	
	
	@Transactional
	@ExtJSConsumes
	@Delete("cities/{id}")
	public Json destroy(@Valid @DtoValidation DtoCity dto) {		
		final City city = cities.get(dto.getModel().getId());
		check(city.getAddresses().isEmpty(), message("constraint", String.format("city %s belong to any address, then doesn\'t deleted.", city.toString())));
		this.cities.delete(city);
		logger.debug(city.toString() + " removed.");
		return json("actor destoyed sucessfully", true);
	}
	
	@Get("cities")
	public Json list() {
		return json(cities.list());
	}
	
}

package br.com.vinniccius.masteringextjs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

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
import br.com.vinniccius.masteringextjs.dto.DtoCountry;
import br.com.vinniccius.masteringextjs.extjs.ExtJSConsumes;
import br.com.vinniccius.masteringextjs.extjs.Json;
import br.com.vinniccius.masteringextjs.model.Country;
import br.com.vinniccius.masteringextjs.repository.Countries;

@Controller
public class CountriesController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CountriesController.class);
	private final Countries countries;

	/**
	 * @deprecated cdi eyes only
	 */
	protected CountriesController() {
		this(null, null, null);
	}
	
	@Inject
	public CountriesController(Result result, Validator validator, Countries countries) {
		super(result, validator);
		this.countries = countries;
	}
	
	@Transactional
	@ExtJSConsumes
	@Put
	@Post
	@Path({"countries", "countries/{id}"})
	public Json save(List<DtoCountry> dtos) {
		List<Country> countries = modelsFor(dtos);
		this.countries.saveAll(countries);
		logger.debug(countries.toString() + " saved.");	
		return json(countries);
	}	
	
	@Transactional
	@ExtJSConsumes
	@Delete("countries/{id}")
	public Json destroy(DtoCountry dto) {		
		final Country country = countries.get(dto.getModel().getId());
		check(country.getCities().isEmpty(), message("constraint", String.format("country %s belong to any city, then doesn\'t deleted.", country.toString())));
		this.countries.delete(country);
		logger.debug(country.toString() + " removed.");
		return json("country destoyed sucessfully", true);
	}
	
	@Get("countries")
	public Json list() {
		return json(countries.list());
	}
	
}

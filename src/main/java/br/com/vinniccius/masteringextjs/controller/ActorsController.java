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
import br.com.vinniccius.masteringextjs.dto.DtoActor;
import br.com.vinniccius.masteringextjs.dto.validation.DtoValidation;
import br.com.vinniccius.masteringextjs.extjs.ExtJSConsumes;
import br.com.vinniccius.masteringextjs.extjs.Json;
import br.com.vinniccius.masteringextjs.extjs.Parameters;
import br.com.vinniccius.masteringextjs.model.Actor;
import br.com.vinniccius.masteringextjs.repository.Actors;

@Controller
public class ActorsController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ActorsController.class);
	private final Actors actors;
	
	/**
	 * @deprecated cdi eyes only
	 */
	protected ActorsController() {
		this(null, null, null);
	}
	
	@Inject
	public ActorsController(Result result, Validator validator, Actors actors) {
		super(result, validator);
		this.actors = actors;
	}
	
	@Transactional
	@ExtJSConsumes
	@Put
	@Post
	@Path({"actors", "actors/{id}"})
	public Json save(@Valid @DtoValidation List<DtoActor> dtos) {
		List<Actor> actors = modelsFor(dtos);
		this.actors.saveAll(actors);
		logger.debug(actors.toString() + " saved.");
		return json(actors);
	}	
	
	@Transactional
	@ExtJSConsumes
	@Delete("actors/{id}")
	public Json destroy(@Valid @DtoValidation DtoActor dto) {		
		final Actor actor = actors.get(dto.getModel().getId());
		check(actor.getFilmActors().isEmpty(), message("constraint", String.format("actor %s belong to any film, then doesn\'t deleted.", actor.toString())));
		this.actors.delete(actor);
		logger.debug(actor.toString() + " removed.");
		return json("actor destoyed sucessfully", true);
	}
	
	@Get("actors")
	public Json list(Parameters param, int filmId) {
		if (filmId != 0) {
			return json(actors.getByFilm(filmId));
		}
		return json(actors.list(param.getFilters()));
	}
	
	@Get("actors")
	public Json search(Parameters parameters) {
		return json(actors.search(parameters));
	}
	
	@Get("actors/{id}")
	public Json get(int id) {
		return json(actors.get(id));
	}

}

package br.com.vinniccius.masteringextjs.controller;

import java.util.Iterator;

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
import br.com.vinniccius.masteringextjs.dto.DtoFilm;
import br.com.vinniccius.masteringextjs.extjs.ExtJSConsumes;
import br.com.vinniccius.masteringextjs.extjs.Json;
import br.com.vinniccius.masteringextjs.model.Film;
import br.com.vinniccius.masteringextjs.model.FilmActor;
import br.com.vinniccius.masteringextjs.model.FilmCategory;
import br.com.vinniccius.masteringextjs.repository.Films;
import br.com.vinniccius.masteringextjs.repository.Page;

@Controller
public class FilmsController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(FilmsController.class);
	private final Films films;
		
	/**
	 * @deprecated cdi eyes only
	 */
	protected FilmsController() {
		this(null, null, null);
	}
	
	@Inject
	public FilmsController(Result result, Validator validator, Films films) {
		super(result, validator);
		this.films = films;
	}
	
	@Transactional
	@ExtJSConsumes
	@Put
	@Post
	@Path({"films", "films/{id}"})
	public Json save(DtoFilm dto) {
		Film film = dto.getModel();
		validate(film);
		
		films.save(film);
		
		Iterator<FilmActor> itFilmActors = film.getFilmActors().iterator();
		while (itFilmActors.hasNext()) {
			itFilmActors.next().setFilm(film);
		}
		
		Iterator<FilmCategory> itFilmCategories = film.getFilmCategories().iterator();
		while (itFilmCategories.hasNext()) {
			itFilmCategories.next().setFilm(film);
		}

		logger.info(film.getTitle() + " saved.");
		return json(dto);
	}
	
	@Transactional
	@ExtJSConsumes
	@Delete("films/{id}")
	public Json destroy(DtoFilm dto) {
		Film film = films.get(dto.getId());
		films.delete(film);
		logger.info(dto.getTitle() + " removed.");
		return json(dto);
	}

	@Get("films")
	public Json list(int start, int limit) {
		Page<Film> page = this.films.page(start, limit);
		return json(page);
	}
	
//	@Get("pdf")
//	public Download pdf() {
//		InputStream stream = new Pdf().films(this.films.list());
//		return new InputStreamDownload(stream, "application/pdf", "films.pdf");
//	}
	
}

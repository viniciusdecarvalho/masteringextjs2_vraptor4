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
import br.com.vinniccius.masteringextjs.dto.DtoLanguage;
import br.com.vinniccius.masteringextjs.extjs.ExtJSConsumes;
import br.com.vinniccius.masteringextjs.extjs.Json;
import br.com.vinniccius.masteringextjs.model.Language;
import br.com.vinniccius.masteringextjs.repository.Languages;

@Controller
public class LanguagesController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(LanguagesController.class);
	private final Languages languages;
	
	/**
	 * @deprecated cdi eyes only
	 */
	protected LanguagesController() {
		this(null, null, null);
	}
	
	@Inject
	public LanguagesController(Result result, Validator validator, Languages languages) {
		super(result, validator);
		this.languages = languages;
	}
	
	@Transactional
	@ExtJSConsumes
	@Put
	@Post
	@Path({"languages", "languages/{id}"})
	public Json save(List<DtoLanguage> dtos) {
		List<Language> languages = modelsFor(dtos);
		this.languages.saveAll(languages);
		logger.debug(dtos.toString() + " saved.");	
		return json(languages);
	}	
	
	@Transactional
	@ExtJSConsumes
	@Delete("languages/{id}")
	public Json destroy(DtoLanguage dto) {		
		final Language actor = languages.get(dto.getModel().getId());
		check(actor.getFilms1().isEmpty(), message("constraint", String.format("language %s belong to any film, then doesn\'t deleted.", actor.toString())));
		check(actor.getFilms2().isEmpty(), message("constraint", String.format("language %s belong to any film, then doesn\'t deleted.", actor.toString())));
		this.languages.delete(actor);
		logger.debug(actor.toString() + " removed.");
		return json("actor destoyed sucessfully", true);
	}
	
	@Get("languages")
	public Json list() {
		return json(languages.list());
	}
	
}

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
import br.com.vinniccius.masteringextjs.dto.DtoCategory;
import br.com.vinniccius.masteringextjs.dto.validation.DtoValidation;
import br.com.vinniccius.masteringextjs.extjs.ExtJSConsumes;
import br.com.vinniccius.masteringextjs.extjs.Json;
import br.com.vinniccius.masteringextjs.model.Category;
import br.com.vinniccius.masteringextjs.repository.Categories;

import com.google.common.collect.Lists;

@Controller
public class CategoriesController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CategoriesController.class);
	private final Categories categories;
	
	/**
	 * @deprecated cdi eyes only
	 */
	protected CategoriesController() {
		this(null, null, null);
	}
	
	@Inject
	public CategoriesController(Result result, Validator validator, Categories categories) {
		super(result, validator);
		this.categories = categories;
	}
	
	@Transactional
	@ExtJSConsumes
	@Put
	@Post
	@Path({"categories", "categories/{id}"})
	public Json save(@Valid @DtoValidation List<DtoCategory> dtos) {
		List<Category> categories = Lists.newArrayList();
		for (DtoCategory dto : dtos) {
			Category category = dto.getModel();
			validate(category);
			categories.add(category);
		}
		if (hasErrors()) {
			return json(getErrors(), false);
		}
		this.categories.saveAll(categories);
		logger.debug(dtos.toString() + " saved.");	
		return json(categories);
	}	
	
	@Transactional
	@ExtJSConsumes
	@Delete("categories/{id}")
	public Json destroy(@Valid @DtoValidation DtoCategory dto) {		
		final Category category = categories.get(dto.getModel().getId());
		check(category.getFilmCategories().isEmpty(), message("constraint", String.format("category %s belong to any film, then doesn\'t deleted.", category.getName())));
		this.categories.delete(category);
		logger.debug(category.toString() + " removed.");
		return json("actor destoyed sucessfully", true);
	}
	
	@Get("categories")
	public Json list(int filmId) {
		if (filmId != 0) {
			return json(categories.getByFilm(filmId));
		}
		return json(categories.list());
	}
	
}

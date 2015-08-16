package br.com.vinniccius.masteringextjs.dto;

import java.sql.Timestamp;

import br.com.vinniccius.masteringextjs.model.Category;
import br.com.vinniccius.masteringextjs.model.Film;
import br.com.vinniccius.masteringextjs.model.FilmCategory;

import com.google.gson.annotations.Expose;

public class DtoFilmCategory extends DtoObject<FilmCategory> {
	
	private Integer categoryId;
	private Integer filmId;
	
	@Expose(serialize=true, deserialize= false)
	private Timestamp lastUpdate;
	
	public DtoFilmCategory() {
		super();
	}
	
	public DtoFilmCategory(FilmCategory filmcategory) {
		super();
		bind(filmcategory);
	}

	public void bind(FilmCategory filmcategory) {
		categoryId = filmcategory.getCategory().getId();
		filmId = filmcategory.getFilm().getId();
		lastUpdate = filmcategory.getLastUpdate();
	}

	@Override
	protected FilmCategory transform() {
		FilmCategory filmCategory = new FilmCategory();		
		filmCategory.setCategory(new Category(categoryId));
		filmCategory.setFilm(new Film(filmId));
		return filmCategory;
	}

}
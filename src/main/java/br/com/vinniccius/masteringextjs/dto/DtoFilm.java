package br.com.vinniccius.masteringextjs.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import br.com.vinniccius.masteringextjs.model.Film;
import br.com.vinniccius.masteringextjs.model.FilmActor;
import br.com.vinniccius.masteringextjs.model.FilmCategory;
import br.com.vinniccius.masteringextjs.model.Language;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DtoFilm extends DtoObject<Film> {
	
	@SerializedName("film_id")
	private Integer id;
	
	@SerializedName("title")
	private String title;
	
	@SerializedName("description")
	private String description;
	
	@SerializedName("release_year")
	private Integer releaseYear; // YYYY
	
	@SerializedName("language_id")
	private Integer languageId;
	
	@SerializedName("original_language_id")
	private Integer originalLanguageId;
	
	@SerializedName("rental_duration")
	private Byte rentalDuration;
	
	@SerializedName("rental_rate")
	private BigDecimal rentalRate;
	
	@SerializedName("length")
	private Integer length;

	@SerializedName("replacement_cost")
	private BigDecimal replacementCost;
	
	@SerializedName("rating")
	private String rating;
	
	@SerializedName("special_features")
	private String specialFeatures;
	
	@SerializedName("last_update")	
	@Expose(deserialize=false)
	private Timestamp lastUpdate;
	
	@SerializedName("actors")
	private List<DtoActor> actors;
	
	@SerializedName("categories")
	private List<DtoCategory> categories;
	
	public DtoFilm() {
		super();
		this.actors = Lists.newArrayList();
		this.categories = Lists.newArrayList();
	}
	
	public DtoFilm(Film film) {
		this();
		bind(film);
	}

	@Override
	public void bind(Film film) {
		this.id = film.getId();
		this.title = film.getTitle();
		this.description = film.getDescription();
		this.releaseYear = film.getReleaseYear();
		Language language = film.getLanguage1();
		if (language != null) {
			this.languageId = language.getId();
		}
		Language originalLanguage = film.getLanguage2();
		if (originalLanguage != null) {
			this.originalLanguageId = originalLanguage.getId();
		}
		this.rentalDuration = film.getRentalDuration();
		this.rentalRate = film.getRentalRate();
		this.length = film.getLength();
		this.replacementCost = film.getReplacementCost();
		this.rating = film.getRating();
		this.specialFeatures = film.getSpecialFeatures();
		this.lastUpdate = film.getLastUpdate();
		
		film.getFilmActors()
			.stream()
			.map(f -> f.getActor())
			.forEach(a -> actors.add(new DtoActor(a)));
		
		film.getFilmCategories()
			.stream()
			.map(f -> f.getCategory())
			.forEach(c -> categories.add(new DtoCategory(c)));
	}							

	@Override	
	protected Film transform() {
		Film film = new Film(id);
		film.setDescription(description);
		film.setTitle(title);
		film.setLength(length);
		film.setReleaseYear(releaseYear);
		film.setLanguage1(new Language(originalLanguageId));
		film.setLanguage2(new Language(languageId));
		film.setRating(rating);
		film.setRentalDuration(rentalDuration);
		film.setRentalRate(rentalRate);
		film.setReplacementCost(replacementCost);
		film.setSpecialFeatures(specialFeatures);		
		film.setFilmActors(resolveFilmActors(film, actors));
		film.setFilmCategories(resolveFilmCategories(film, categories));
		return film;
	}

	private List<FilmActor> resolveFilmActors(final Film film, List<DtoActor> actors) {
		if (actors == null) {
			return Lists.newArrayList();
		}
		return Lists.transform(actors, new Function<DtoActor, FilmActor>() {
			@Override
			public FilmActor apply(DtoActor dto) {				
				FilmActor filmActor = new FilmActor(film, dto.getModel());
				film.getFilmActors().add(filmActor);
				return filmActor;
			}
		});
	}
	
	private List<FilmCategory> resolveFilmCategories(final Film film, List<DtoCategory> categories) {
		if (categories == null) {
			return Lists.newArrayList();
		}
		return Lists.transform(categories, new Function<DtoCategory, FilmCategory>() {
			@Override
			public FilmCategory apply(DtoCategory dto) {
				FilmCategory filmCategory = new FilmCategory(film, dto.getModel());
				film.getFilmCategories().add(filmCategory);
				return filmCategory;
			}
		});
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Integer getOriginalLanguageId() {
		return originalLanguageId;
	}

	public void setOriginalLanguageId(Integer originalLanguageId) {
		this.originalLanguageId = originalLanguageId;
	}

	public Byte getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(Byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public BigDecimal getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public BigDecimal getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
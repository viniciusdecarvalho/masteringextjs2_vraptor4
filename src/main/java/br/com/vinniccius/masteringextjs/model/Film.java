package br.com.vinniccius.masteringextjs.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="film")
public class Film implements Model<Integer>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="film_id")
	private Integer id;

    @Lob()
	private String description;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	private int length;

//	@Enumerated(EnumType.ORDINAL)
	private String rating;

	@Column(name="release_year")
	private Integer releaseYear;

	@Column(name="rental_duration")
	private byte rentalDuration;

	@Column(name="rental_rate")
	private BigDecimal rentalRate;

	@Column(name="replacement_cost")
	private BigDecimal replacementCost;

	@Column(name="special_features")
//	@Enumerated(EnumType.STRING)
	private String specialFeatures;

	private String title;

	//bi-directional many-to-one association to Language
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="language_id")
	private Language language1;

	//bi-directional many-to-one association to Language
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="original_language_id")
	private Language language2;

	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="film", cascade= {CascadeType.ALL})
	@NotEmpty(message="A film must have at least one Actor")
	private List<FilmActor> filmActors;

	//bi-directional many-to-one association to FilmCategory
	@OneToMany(mappedBy="film", cascade={CascadeType.ALL})
	@NotEmpty(message="A film must have at least one Category")
	private List<FilmCategory> filmCategories;

	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="film")
	private Set<Inventory> inventories;

    public Film() {
    	filmCategories = new ArrayList<FilmCategory>();
    	filmActors = new ArrayList<FilmActor>();
    }

	public Film(Integer id) {
		this();
		setId(id);
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Integer getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public byte getRentalDuration() {
		return this.rentalDuration;
	}

	public void setRentalDuration(byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public BigDecimal getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	public BigDecimal getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getSpecialFeatures() {
		return this.specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Language getLanguage1() {
		return this.language1;
	}

	public void setLanguage1(Language language1) {
		this.language1 = language1;
	}
	
	public Language getLanguage2() {
		return this.language2;
	}

	public void setLanguage2(Language language2) {
		this.language2 = language2;
	}
	
	public List<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(List<FilmActor> filmActors) {
		this.filmActors = filmActors;
		for (FilmActor filmActor : filmActors) {
			filmActor.setFilm(this);
		}
	}
	
	public List<FilmCategory> getFilmCategories() {		
		return this.filmCategories;
	}

	public void setFilmCategories(List<FilmCategory> filmCategories) {
		this.filmCategories = filmCategories;
		for (FilmCategory filmCategory : this.filmCategories) {
			filmCategory.setFilm(this);
		}
	}
	
	public Set<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}
	
}
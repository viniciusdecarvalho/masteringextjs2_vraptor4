package br.com.vinniccius.masteringextjs.dto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import br.com.vinniccius.masteringextjs.model.Actor;
import br.com.vinniccius.masteringextjs.model.Category;
import br.com.vinniccius.masteringextjs.model.City;
import br.com.vinniccius.masteringextjs.model.Country;
import br.com.vinniccius.masteringextjs.model.Film;
import br.com.vinniccius.masteringextjs.model.Group;
import br.com.vinniccius.masteringextjs.model.Language;
import br.com.vinniccius.masteringextjs.model.Menu;
import br.com.vinniccius.masteringextjs.model.User;

@RequestScoped
public class DtoMappingRegisters {

	private DtoMap mapping;
	
	public DtoMappingRegisters() {
		this.mapping = new DtoMap();
	}

	@PostConstruct
	public void init() {
		mapping.put(Actor.class, 		DtoActor.class);
		mapping.put(Category.class, 	DtoCategory.class);
		mapping.put(City.class, 		DtoCity.class);
		mapping.put(Country.class, 		DtoCountry.class);
		mapping.put(Language.class, 	DtoLanguage.class);
		mapping.put(User.class, 		DtoUser.class);
		mapping.put(Film.class, 		DtoFilm.class);
		mapping.put(Group.class, 		DtoGroup.class);
		mapping.put(Menu.class, 		DtoMenu.class);	
	}
	
	public DtoMap getMapping() {
		return mapping;
	}
	
}

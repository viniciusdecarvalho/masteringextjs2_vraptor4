package br.com.vinniccius.masteringextjs.repository;

import java.util.Collection;
import java.util.List;

import br.com.vinniccius.masteringextjs.model.Language;

public interface Languages {

	List<Language> list();
	
	Language get(Integer id);
	
	void saveAll(Collection<Language> actors);
	
	void delete(Language actor);
	
}

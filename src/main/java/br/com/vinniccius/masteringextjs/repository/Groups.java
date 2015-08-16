package br.com.vinniccius.masteringextjs.repository;

import java.util.Collection;
import java.util.List;

import br.com.vinniccius.masteringextjs.model.Group;
import br.com.vinniccius.masteringextjs.model.Menu;

public interface Groups {

	Collection<Group> list();

	Group get(Integer id);

	List<Menu> rootMenu(Integer groupId);
}
package br.com.vinniccius.masteringextjs.dto;

import br.com.vinniccius.masteringextjs.model.Group;

public class DtoGroup extends DtoObject<Group> {

	private Integer id;
	private String name;
	
	public DtoGroup() {
		super();
	}
	
	public DtoGroup(Group group) {
		super();
		bind(group);
	}

	public void bind(Group group) {
		id = group.getId();
		name = group.getName();
	}

	@Override
	protected Group transform() {
		Group group = new Group(id);
		group.setName(name);
		return group;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
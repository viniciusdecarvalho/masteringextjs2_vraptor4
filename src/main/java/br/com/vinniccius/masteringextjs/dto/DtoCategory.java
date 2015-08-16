package br.com.vinniccius.masteringextjs.dto;

import java.sql.Timestamp;

import br.com.vinniccius.masteringextjs.model.Category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DtoCategory extends DtoObject<Category> {

	@SerializedName("category_id")
	private Integer id;
	
	@SerializedName("name")
	private String name;
	
	@Expose(serialize=true, deserialize= false)
	private Timestamp lastUpdate;
	
	public DtoCategory() {
		super();
	}
	
	public DtoCategory(Category category) {
		super();
		bind(category);
	}

	@Override
	public void bind(Category category) {
		id = category.getId();
		name = category.getName();
		lastUpdate = category.getLastUpdate();
	}

	@Override
	protected Category transform() {
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		category.setLastUpdate(lastUpdate);
		return category;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	
}
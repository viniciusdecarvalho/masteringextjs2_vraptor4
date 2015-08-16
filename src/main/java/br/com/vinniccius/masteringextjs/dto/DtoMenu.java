package br.com.vinniccius.masteringextjs.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.vinniccius.masteringextjs.model.Menu;

public class DtoMenu extends DtoObject<Menu> {
	
	private Integer id;
	private String text;
	private String iconCls;
	private String className;
	private List<DtoMenu> items;
	
	public DtoMenu() {
		super();
	}
	
	public DtoMenu(Menu menu) {
		super();
		bind(menu);
	}

	public void bind(Menu menu) {
		id = menu.getId();
		className = menu.getClassName();
		iconCls = menu.getIconCls();
		text = menu.getText();
		List<Menu> children = menu.getMenus();
		if (!children.isEmpty()) {
			this.items = new ArrayList<DtoMenu>();
			for (Menu c : children) {
				DtoMenu child = new DtoMenu(c);
				this.items.add(child);
			}
		}
	}

	@Override
	protected Menu transform() {
		Menu menu = new Menu();
		menu.setId(id);
		menu.setClassName(className);
		menu.setIconCls(iconCls);
		menu.setText(text);
		return menu;
	}

}
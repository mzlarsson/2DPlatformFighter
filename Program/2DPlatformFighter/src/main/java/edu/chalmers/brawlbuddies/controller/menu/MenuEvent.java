package edu.chalmers.brawlbuddies.controller.menu;

import edu.chalmers.brawlbuddies.view.menu.MenuItem;

public class MenuEvent {
	
	private MenuItem item;
	
	public MenuEvent(MenuItem item){
		this.item = item;
	}
	
	public MenuItem getMenuItem(){
		return this.item;
	}
	
	public String getName(){
		return this.item.getItemName();
	}
	
	public String getValue(){
		return this.item.getValue();
	}

}

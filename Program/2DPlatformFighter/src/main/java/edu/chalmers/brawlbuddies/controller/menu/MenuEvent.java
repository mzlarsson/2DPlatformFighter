package edu.chalmers.brawlbuddies.controller.menu;

import edu.chalmers.brawlbuddies.view.menu.MenuItem;

/**
 * Sets up an event for handling changes in MenuItems
 * @author Matz Larsson
 * @version 1.0
 */

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

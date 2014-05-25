package edu.chalmers.brawlbuddies.view.menu;

import java.util.List;

import edu.chalmers.brawlbuddies.view.IView;
/**
 * A interface to describe a menu view 
 * @author Matz Larsson
 *
 */
public interface MenuView extends IView{
	/**
	 * Get the menu items of the view
	 * @return List<MenuItem> - the menu items in the viw
	 */
	public List<MenuItem> getMenuItems();
	/**
	 * Get the selected item in the view
	 * @return MenuItem - the selected menu item
	 */
	public MenuItem getSelectedItem();
	/**
	 * Get the menu item by a name
	 * @param itemName - the name of the menu item
	 * @return MenuItem - the menu item corresponding to the itemName
	 */
	public MenuItem get(String itemName);
	/**
	 * Set given item to the selected menu item
	 * @param item - The given item 
	 */
	public void setSelectedItem(MenuItem item);
	/**
	 * Selects the next item
	 */
	public void nextItem();
	/**
	 * Selects the previous item
	 */
	public void prevItem();
	/**
	 * Update the graphical representation of the menu system
	 */
	public void updateContents();
	
}

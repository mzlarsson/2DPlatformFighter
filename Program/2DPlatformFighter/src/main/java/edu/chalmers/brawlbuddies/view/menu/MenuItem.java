package edu.chalmers.brawlbuddies.view.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
/**
 * A interface to describe a graphical representation of a item in a menu 
 * @author Matz Larsson
 * @version 1.0
 *
 */
public interface MenuItem {
	/**
	 * Renders the menu item in a GameContainer with a Graphics object
	 * @param gc - the GameContainer
	 * @param g - the Graphics object
	 */
	public void render(GameContainer gc, Graphics g);
	/**
	 * Get the name of the menu item
	 * @return String - the name of the menu item
	 */
	public String getItemName();
	/**
	 * Get the value of the menu item
	 * @return String - the value of the menu item
	 */
	public String getValue();
	/**
	 * Set the menu item to active if true or inactive if false
	 * @param active - if the menu item shall be set to active
	 */
	public void setActive(boolean active);
	/**
	 * Return if the menu item is active
	 * @return boolean - true if the menu item is active
	 */
	public boolean isActive();
	/**
	 * Set if this menu item is considered to have a error
	 * @param hasError - true if the menu item have a error
	 */
	public void setError(boolean hasError);
	/**
	 * Return true if position described by x and y is within the menu item
	 * @param x - the x value of the position
	 * @param y - the y value of the position
	 * @return boolean - true if x and y is within the menu item
	 */
	public boolean isWithin(float x, float y);
	/***
	 * Recalculate the graphical representation of the menu item
	 */
	public void recalculate();
	
}

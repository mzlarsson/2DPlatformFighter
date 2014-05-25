package edu.chalmers.brawlbuddies.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import edu.chalmers.brawlbuddies.model.IWrapper;
/**
 * A interface for objects that can be rendered by the view
 * @author Lisa Lipkin
 *
 */
public interface IDrawable {
	/**
	 * Slick render method that forwards the render call
	 * @param gc the slick GameContainer
	 * @param g the associated slick Graphics object
	 */
	public void render(GameContainer gc, Graphics g);
	
	/**
	 * handles the update event for the object
	 * @param obj1 the object affected by the event.
	 * @param obj2 complimentary object, oftentimes the source
	 */
	public void update(IWrapper obj1, IWrapper obj2);

	public Integer getUniqeID();


}

package edu.chalmers.brawlbuddies.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
/**
 * A interface to describe the view
 * @author Lisa Lipkin
 *
 */
public interface IView {
	/**
	 * Slick render method that forwards the render call
	 * @param gc the slick GameContainer
	 * @param g the associated slick Graphics object
	 */
	public void render(GameContainer gc, Graphics g);
	
	/**
	 * Returns the scroller used for this view
	 * @return The scroller used for this view
	 */
	public SideScroller getScroller();
	
	/**
	 * Updates timers in the view.
	 * @param delta Time since last update in milliseconds.
	 */
	public void update(int delta);

	/**
	 * Removes everything connected to the view.
	 */
	public void close();
}

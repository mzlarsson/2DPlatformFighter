package edu.chalmers.brawlbuddies.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface IView {
	/**
	 * Slick render method that forwards the render call
	 * @param gc the slick GameContainer
	 * @param g the associated slick Graphics object
	 */
	public void render(GameContainer gc, Graphics g);

}

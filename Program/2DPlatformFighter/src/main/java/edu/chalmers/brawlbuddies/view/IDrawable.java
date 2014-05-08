package edu.chalmers.brawlbuddies.view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface IDrawable {
	public void render(GameContainer gc, Graphics g);
	public void update(IWrapper obj1, IWrapper obj2);

}

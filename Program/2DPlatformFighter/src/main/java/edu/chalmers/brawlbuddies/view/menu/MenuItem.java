package edu.chalmers.brawlbuddies.view.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface MenuItem {

	public void render(GameContainer gc, Graphics g);
	
	public String getValue();
	public void setActive(boolean active);
	public boolean isActive();
	public boolean isWithin(float x, float y);
	
	public void recalculate();
	
}

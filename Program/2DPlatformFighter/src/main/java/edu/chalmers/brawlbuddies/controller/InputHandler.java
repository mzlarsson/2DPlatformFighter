package edu.chalmers.brawlbuddies.controller;

import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;

public interface InputHandler {

	public void resetDefault();
	public int getValue(GameKey key);
	public void setValue(GameKey key, int value);
	public boolean isActivated(GameKey key);
	public boolean isActive(GameKey key);
	public Direction getDirection();
	public Position getMousePosition();
	public boolean isMousePositionRelative();
	
}

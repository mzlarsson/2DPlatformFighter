package edu.chalmers.brawlbuddies.controller;

import org.newdawn.slick.Input;

import edu.chalmers.brawlbuddies.model.Direction;

public interface InputHandler {

	public void resetDefault();
	public int getValue(GameKey key);
	public void setValue(GameKey key, int value);
	public boolean isActive(Input input, GameKey key);
	public Direction getDirection(Input input);
	
}

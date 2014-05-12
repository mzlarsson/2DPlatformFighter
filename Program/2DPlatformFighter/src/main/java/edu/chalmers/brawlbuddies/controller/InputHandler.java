package edu.chalmers.brawlbuddies.controller;

import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;

/**
 * Basic interface for describing the what methods the classes which
 * handles input must have.
 * @author Matz Larsson
 * @version 1.0
 *
 */

public interface InputHandler {

	/**
	 * Resets all settings to default
	 */
	public void resetDefault();
	
	/**
	 * Gets the numeric key value (current settings) of a certain GameKey
	 * @param key The GameKey to get value for
	 * @return The numeric current key value for the GameKey
	 */
	public int getValue(GameKey key);
	
	/**
	 * Sets a given GameKey to a new custom value
	 * @param key The GameKey to set
	 * @param value The value to use for the given GameKey
	 */
	public void setValue(GameKey key, int value);
	
	/**
	 * Determines if this GameKey has been activated.
	 * E.g. buttons are activated when they are pressed, not held down.
	 * @param key The GameKey to check
	 * @return <code>true</code> if the GameKey is activated, <code>false</code> otherwise
	 */
	public boolean isActivated(GameKey key);
	
	/**
	 * Determines if the GameKey currently is active
	 * E.g. buttons are active when they are held down
	 * @param key The GameKey to check
	 * @return <code>true</code> if the GameKey is active, <code>false</code> otherwise
	 */
	public boolean isActive(GameKey key);
	
	/**
	 * Calculates a Direction depending on the directional GameKeys (LEFT, RIGHT, UP, DOWN)
	 * @return A move Direction depending on current input state
	 */
	public Direction getDirection();
	
	/**
	 * Determines the position of the virtual mouse of this input
	 * @return The position of the virtual mouse
	 */
	public Position getMousePosition();
	
	/**
	 * Determines whether the method getMousePosition() returns a relative or absolute position
	 * @return <code>true</code> if the position is relative, <code>false</code> if it is absolute
	 */
	public boolean isMousePositionRelative();
	
}

package edu.chalmers.brawlbuddies;

/**
 * An interface to handle the movement for game objects with different states.
 * 
 * @author Patrik Haar
 * @version 1.0
 */
public interface MovementState {
	
	public static final int MODIFIER = 1000;
	
	/**
	 * Updates the velocity and position of the GameObject and returns the old position.
	 * @param delta The time passed since last update in milliseconds.
	 * @return The position before the movement.
	 */
	public Position update(int delta);
}

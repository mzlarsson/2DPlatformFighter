package edu.chalmers.brawlbuddies.model;

/**
 * A handler for the game's goals.
 * @author Patrik Haar
 * @version 1.0
 */
public interface IGoal extends GameListener{

	/**
	 * Updates the goals.
	 * @param delta The time since last update in milliseconds.
	 */
	public void update(int delta);
	/**
	 * Adds the given GameListener as a listener to catch game events.
	 * @param gl The GameListener to handle the events this class throws.
	 */
	public void addGameListener(GameListener gl);
	
	/**
	 * Removes the given GameListener as a listener to this class.
	 * @param gl The GameListener to be removed.
	 */
	public void removeGameListener(GameListener gl);
}

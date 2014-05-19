package edu.chalmers.brawlbuddies.model;

/**
 * An interface for GoalHandlers to handle the goals of the game. 
 * @author Patrik Haar
 * @version 1.0
 */
public interface IGoalHandler extends GameListener{

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
	
	/**
	 * Adds a goal to the game.
	 * @param goal The goal to add.
	 */
	public void addGoal(IGoal goal);
}

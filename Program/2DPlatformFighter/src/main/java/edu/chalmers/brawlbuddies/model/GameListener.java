package edu.chalmers.brawlbuddies.model;

/**
 * A listener interface for handling events from the model.
 * @author Patrik Haar
 * @version 1.0
 */
public interface GameListener {
	
	/**
	 * This method will be called at a Game Over event.
	 */
	public void gameOver();
	
	/**
	 * 
	 * @param playerID
	 */
	public void playerKilled(int playerID);
}

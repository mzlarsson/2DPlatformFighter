package edu.chalmers.brawlbuddies.model;

/**
 * A listener interface for handling events from the model.
 * @author Patrik Haar
 * @version 1.0
 */
public interface GameListener {
	
	/**
	 * This method will be called at a Game Over event.
	 * @param winnerID The ID of the character who won.
	 */
	public void gameOver(int winnerID);
	
	/**
	 * 
	 * @param playerID
	 */
	public void playerKilled(int playerID);
}

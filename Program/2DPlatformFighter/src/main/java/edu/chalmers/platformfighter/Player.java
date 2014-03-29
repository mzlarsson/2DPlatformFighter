package edu.chalmers.platformfighter;

/**
 * A class to describe a player playing the game.
 * 
 * @author Patrik Haar
 * @version 0.1
 */
public class Player {
	
	private String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of the player.
	 * @return String, The name of the player.
	 */
	public String getName() {
		return name;
	}
}

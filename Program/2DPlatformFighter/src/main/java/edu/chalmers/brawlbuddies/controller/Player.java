package edu.chalmers.brawlbuddies.controller;

/**
 * A class to describe a player playing the game.
 * 
 * @author Patrik Haar
 * @version 0.1
 * @author Matz Larsson
 */
public class Player {
	
	private String name;
	private int characterID;
	private InputHandler handler;

	/**
	 * Creates a player with a name and an invalid characterID
	 * @param name The name of the player
	 */
	public Player(String name){
		this(name, -1);
	}

	/**
	 * Creates a new player with deafult key input.
	 * @param name The name of the player
	 * @param ch The character to use
	 */
	public Player(String name, int characterID) {
		this(name, characterID, new KeyInputHandler());
	}
	
	/**
	 * Creates a new player with the given name and input handler
	 * @param name The name of the player
	 * @param handler The input handler of the player
	 */
	public Player(String name, InputHandler handler){
		this(name, -1, handler);
	}
	
	/**
	 * Creates a new player with predefined control settings.
	 * @param name Name of the player
	 * @param ch Character to use
	 * @param handler Handler for handling controls.
	 */
	public Player(String name, int characterID, InputHandler handler) {
		this.name = name;
		this.characterID = characterID;
		this.handler = handler;
	}
	
	/**
	 * Returns the name of the player.
	 * @return String, The name of the player.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the character connected with the player.
	 * @return The character connected with the player.
	 */
	public int getCharacterID() {
		return this.characterID;
	}
	
	/**
	 * Returns the input handler of this player (contains control settings etc.)
	 * @return The input handler of this player.
	 */
	public InputHandler getInputHandler(){
		return this.handler;
	}
	
	/**
	 * Sets the characterID bound to this player
	 * @param characterID The ID of the character
	 */
	public void setCharacterID(int characterID){
		this.characterID = characterID;
	}
	
	/**
	 * Sets the input handler of this player to the given one
	 * @param handler The new input handler
	 */
	public void setInputHandler(InputHandler handler){
		this.handler = handler;
	}
}

package edu.chalmers.brawlbuddies.controller;

import org.newdawn.slick.Input;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.Character;

/**
 * A class to describe a player playing the game.
 * 
 * @author Patrik Haar
 * @version 0.1
 * @author Matz Larsson
 */
public class Player {
	
	private String name;
	private Character ch;
	private InputHandler handler;


	/**
	 * Creates a new player.
	 * @param name The name of the player
	 * @param ch The character to use
	 */
	public Player(String name, Character ch) {
		this(name, ch, new KeyInputHandler(new Input(0)));		//FIXME random initialization. change.
	}
	
	/**
	 * Creates a new player with predefined control settings.
	 * @param name Name of the player
	 * @param ch Character to use
	 * @param handler Handler for handling controls.
	 */
	public Player(String name, Character ch, InputHandler handler) {
		this.name = name;
		this.ch = ch;
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
	public Character getCharacter() {
		return this.ch;
	}
	
	/**
	 * Returns the input handler of this player (contains control settings etc.)
	 * @return The input handler of this player.
	 */
	public InputHandler getInputHandler(){
		return this.handler;
	}
	
	/**
	 * Updates the velocity and position of the Player´s Character and returns the old position.
	 * @param delta The time passed since last update in milliseconds.
	 * @return The position before movement.
	 */
	public Position update(int delta) {
		return this.ch.update(delta);
	}
}

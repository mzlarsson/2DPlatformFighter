package edu.chalmers.brawlbuddies.controller;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.Character;

/**
 * A class to describe a player playing the game.
 * 
 * @author Patrik Haar
 * @version 0.1
 */
public class Player {
	
	private String name;
	private Character ch;
	
	public Player(String name, Character ch) {
		this.name = name;
		this.ch = ch;
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
	 * Updates the velocity and position of the Player´s Character and returns the old position.
	 * @param delta The time passed since last update in milliseconds.
	 * @return The position before movement.
	 */
	public Position update(int delta) {
		return this.ch.update(delta);
	}
}

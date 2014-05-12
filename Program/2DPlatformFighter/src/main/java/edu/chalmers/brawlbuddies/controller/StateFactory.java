package edu.chalmers.brawlbuddies.controller;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.state.BasicGameState;

/**
 * Factory for creating the states of the game Brawl Buddies
 * @author Matz Larsson
 * @version 1.0
 * 
 */

public class StateFactory {

	/**
	 * Creates a list of all the possible game states
	 * @return List of all the game states
	 */
	public static List<BasicGameState> getAllStates(){
		List<BasicGameState> states = new ArrayList<BasicGameState>();
		states.add(new MenuState());
		states.add(new PlayState());
		return states;
	}
}

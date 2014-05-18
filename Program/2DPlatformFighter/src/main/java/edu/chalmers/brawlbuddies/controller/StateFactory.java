package edu.chalmers.brawlbuddies.controller;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.state.BasicGameState;

import edu.chalmers.brawlbuddies.controller.menu.CreditsMenuState;
import edu.chalmers.brawlbuddies.controller.menu.EndScreenState;
import edu.chalmers.brawlbuddies.controller.menu.MainMenuState;
import edu.chalmers.brawlbuddies.controller.menu.OptionMenuState;
import edu.chalmers.brawlbuddies.controller.menu.GameSetupState;

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
		states.add(new MainMenuState());
		states.add(new PlayState());
		states.add(new OptionMenuState());
		states.add(new CreditsMenuState());
		states.add(new EndScreenState());
		states.add(new GameSetupState());
		return states;
	}
}

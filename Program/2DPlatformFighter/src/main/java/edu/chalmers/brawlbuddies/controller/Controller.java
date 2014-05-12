package edu.chalmers.brawlbuddies.controller;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.chalmers.brawlbuddies.controller.input.InputHandlerChooser;
import edu.chalmers.brawlbuddies.controller.input.midi.MidiDeviceFinder;

/**
 * Basic controller used for handling the general aspects of the
 * game Brawl Buddies.
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class Controller extends StateBasedGame {

	/**
	 * Creates a new Controller and a new Slick window
	 */
	public Controller() {
		super("BrawlBuddies v1.0");
	}

	/**
	 * Inits all the states used in the game
	 * @see StateFactory
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		//Inits all the states
		List<BasicGameState> states = StateFactory.getAllStates();
		for(BasicGameState state : states){
			this.addState(state);
		}
		
		//Sets up and starts a new game. NOTE: This is only temporarily.
		//TODO remove this when the menu is in place.
		Player[] players = { new Player("Player1"),
							 new Player("Player2", InputHandlerChooser.getInstance().getInputHandler(3, false))};
		String[] characterNames = {"bob", "bob"};
		this.startGame(players, characterNames);
	}
	
	/**
	 * Redirects the game to the main menu
	 */
	public void gotoMenu(){
		this.enterState(Constants.GAMESTATE_MENU);
	}
	
	/**
	 * Starts up a new game with some game parameters
	 * @param players The players to take part of this game
	 * @param characterNames The names of the characters used, synched with the player array
	 */
	public void startGame(Player[] players, String[] characterNames){
		this.enterState(Constants.GAMESTATE_PLAY);
		((PlayState)(this.getState(Constants.GAMESTATE_PLAY))).startGame(players, characterNames);
	}

}

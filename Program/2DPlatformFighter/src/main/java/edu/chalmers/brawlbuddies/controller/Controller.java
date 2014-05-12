package edu.chalmers.brawlbuddies.controller;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

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
		KeyInputHandler p2handler = new KeyInputHandler();
		p2handler.setValue(GameKey.DOWN, Input.KEY_DOWN);
		p2handler.setValue(GameKey.UP, Input.KEY_UP);
		p2handler.setValue(GameKey.LEFT, Input.KEY_LEFT);
		p2handler.setValue(GameKey.RIGHT, Input.KEY_RIGHT);
		p2handler.setValue(GameKey.JUMP, Input.KEY_I);
		//p2handler.setValue(GameKey.SKILL1, Input.KEY_O);
		//p2handler.setValue(GameKey.SKILL2, Input.KEY_P);
		p2handler.setValue(GameKey.SKILL3, Input.KEY_K);
		p2handler.setValue(GameKey.SKILL4, Input.KEY_L);

		Player[] players = { new Player("BobTheSparklyMidget"), new Player("Nano", p2handler)};
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

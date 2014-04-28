package edu.chalmers.brawlbuddies.controller;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.chalmers.brawlbuddies.Constants;

public class Controller extends StateBasedGame {

	public Controller() {
		super("BrawlBuddies v1.0");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		List<BasicGameState> states = StateFactory.getAllStates();
		for(BasicGameState state : states){
			this.addState(state);
		}
	}
	
	public void gotoMenu(){
		this.enterState(Constants.GAMESTATE_MENU);
	}
	
	public void startGame(Player[] players){
		this.enterState(Constants.GAMESTATE_PLAY);
		((PlayState)(this.getState(Constants.GAMESTATE_PLAY))).startGame(players);
	}

}

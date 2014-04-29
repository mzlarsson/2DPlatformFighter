package edu.chalmers.brawlbuddies.controller;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.services.factories.CharacterFactory;

public class Controller extends StateBasedGame {

	public Controller() {
		super("BrawlBuddies v1.0");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		System.out.println("init states");
		List<BasicGameState> states = StateFactory.getAllStates();
		for(BasicGameState state : states){
			this.addState(state);
		}
		
		Player[] players = { new Player("BobTheSparklyMidget", CharacterFactory.create("bob")) };
		this.startGame(players);
	}
	
	public void gotoMenu(){
		this.enterState(Constants.GAMESTATE_MENU);
	}
	
	public void startGame(Player[] players){
		System.out.println("start");
		this.enterState(Constants.GAMESTATE_PLAY);
		((PlayState)(this.getState(Constants.GAMESTATE_PLAY))).startGame(players);
	}

}

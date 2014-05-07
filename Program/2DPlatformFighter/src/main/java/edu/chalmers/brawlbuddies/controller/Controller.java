package edu.chalmers.brawlbuddies.controller;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

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
		
		KeyInputHandler p2handler = new KeyInputHandler();
		p2handler.setValue(GameKey.DOWN, Input.KEY_DOWN);
		p2handler.setValue(GameKey.UP, Input.KEY_UP);
		p2handler.setValue(GameKey.LEFT, Input.KEY_LEFT);
		p2handler.setValue(GameKey.RIGHT, Input.KEY_RIGHT);
		p2handler.setValue(GameKey.JUMP, Input.KEY_I);
		p2handler.setValue(GameKey.SKILL1, Input.KEY_O);
		p2handler.setValue(GameKey.SKILL2, Input.KEY_P);
		p2handler.setValue(GameKey.SKILL3, Input.KEY_K);
		p2handler.setValue(GameKey.SKILL4, Input.KEY_L);
		Player[] players = { new Player("BobTheSparklyMidget"), new Player("Nano", p2handler)};
		String[] characterNames = {"bob", "bob"};
		this.startGame(players, characterNames);
	}
	
	public void gotoMenu(){
		this.enterState(Constants.GAMESTATE_MENU);
	}
	
	public void startGame(Player[] players, String[] characterNames){
		System.out.println("start");
		this.enterState(Constants.GAMESTATE_PLAY);
		((PlayState)(this.getState(Constants.GAMESTATE_PLAY))).startGame(players, characterNames);
	}

}

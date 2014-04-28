package edu.chalmers.brawlbuddies.controller;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.state.BasicGameState;

public class StateFactory {

	public static List<BasicGameState> getAllStates(){
		List<BasicGameState> states = new ArrayList<BasicGameState>();
		states.add(new MenuState());
		states.add(new PlayState());
		return states;
	}
}

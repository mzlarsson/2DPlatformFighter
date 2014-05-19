package edu.chalmers.brawlbuddies.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for handling goals for the game
 * @author Patrik Haar
 * @version 1.0
 */
public class GoalHandler implements IGoalHandler {

	private List<IGoal> goals;
	private List<GameListener> listeners;
	
	public GoalHandler() {
		goals = new ArrayList<IGoal>();
		listeners = new ArrayList<GameListener>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addGoal(IGoal goal) {
		goals.add(goal);
		for (GameListener gl : listeners) {
			goal.addGameListener(gl);
		}
	}

	public void gameEventPerformed(String evtName, Object value) {
		for(IGoal goal : goals) {
			goal.gameEventPerformed(evtName, value);
		}
	}

	public void update(int delta) {
		for (IGoal goal : goals) {
			goal.update(delta);
		}
	}

	public void addGameListener(GameListener gl) {
		listeners.add(gl);
	}

	public void removeGameListener(GameListener gl) {
		listeners.remove(gl);
	}
}

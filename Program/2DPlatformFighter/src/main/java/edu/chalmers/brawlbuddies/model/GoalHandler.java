package edu.chalmers.brawlbuddies.model;

import java.util.ArrayList;
import java.util.List;

import edu.chalmers.brawlbuddies.model.world.ICharacter;

/**
 * A class for handling goals for the game
 * @author Patrik Haar
 * @version 1.0
 */
public class GoalHandler implements IGoalHandler {

	private List<IGoal> goals;
	private List<GameListener> listeners;
	private List<ICharacter> characters;
	/**
	 * Creates a new GoalHandler
	 */
	public GoalHandler() {
		goals = new ArrayList<IGoal>();
		listeners = new ArrayList<GameListener>();
		characters = new ArrayList<ICharacter>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addGoal(IGoal goal) {
		goals.add(goal);
		for (GameListener gl : listeners) {
			goal.addGameListener(gl);
		}
		for (ICharacter character : characters) {
			goal.gameEventPerformed("characterAdded", character);
		}
	}
	/**
	 * {@inheritDoc}
	 */
	public void gameEventPerformed(String evtName, Object value) {
		if (evtName.equals("characterAdded")) {
			characters.add((ICharacter)value);
		}
		for(IGoal goal : goals) {
			goal.gameEventPerformed(evtName, value);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(int delta) {
		for (IGoal goal : goals) {
			goal.update(delta);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void addGameListener(GameListener gl) {
		listeners.add(gl);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeGameListener(GameListener gl) {
		listeners.remove(gl);
	}
}

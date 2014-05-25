package edu.chalmers.brawlbuddies.model;

import java.util.ArrayList;
import java.util.List;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.world.ICharacter;

/**
 * A wrapper for handling goals and throw events to the view.
 * @author Patrik Haar
 * @version 1.0
 */
public class GoalHandlerWrapper implements IGoalHandler {

	private GoalHandler gh;
	private List<GameListener> listeners;
	/**
	 * Creates a new GoalHandlerWrapper
	 */
	public GoalHandlerWrapper() {
		this.gh = new GoalHandler();
		this.listeners = new ArrayList<GameListener>();
	}

	/**
	 * {@inheritDoc}
	 */
	public void gameEventPerformed(String evtName, Object value) {
		if (evtName.equals("characterKilled")) {
			EventBus.getInstance().fireEvent(new EventBusEvent("characterDied", ((ICharacter)value).getID(), null));
		}
		gh.gameEventPerformed(evtName, value);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(int delta) {
		gh.update(delta);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addGameListener(GameListener gl) {
		listeners.add(gl);
		gh.addGameListener(gl);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeGameListener(GameListener gl) {
		listeners.remove(gl);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addGoal(IGoal goal) {
		gh.addGoal(goal);
	}
}

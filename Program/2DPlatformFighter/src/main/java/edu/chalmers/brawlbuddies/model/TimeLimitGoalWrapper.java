package edu.chalmers.brawlbuddies.model;

import java.util.ArrayList;
import java.util.List;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;

/**
 * A wrapper for time-limit goals to throw events to the view.
 * @author Patrik Haar
 * @version 1.0
 */
public class TimeLimitGoalWrapper implements IGoal {

	private TimeLimitGoal tlg;
	
	private List<GameListener> listeners;
	
	/**
	 * Creates a time-limit goal.
	 * @param timeLimit The time-limit in seconds.
	 */
	public TimeLimitGoalWrapper(int timeLimit) {
		this.tlg = new TimeLimitGoal(timeLimit);
		tlg.addGameListener(this);
		this.listeners = new ArrayList<GameListener>();
		EventBus.getInstance().fireEvent(new EventBusEvent("timeLimitAdded", timeLimit, null));
	}

	/**
	 * {@inheritDoc}
	 */
	public void gameEventPerformed(String evtName, Object value) {
		if (evtName.equals("gameOver")) {
			for (GameListener gl : listeners) {
				gl.gameEventPerformed(evtName, value);
			}
		} else {
			tlg.gameEventPerformed(evtName, value);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(int delta) {
		tlg.update(delta);
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

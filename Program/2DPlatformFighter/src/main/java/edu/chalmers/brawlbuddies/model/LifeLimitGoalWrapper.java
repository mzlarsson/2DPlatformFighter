package edu.chalmers.brawlbuddies.model;

import java.util.ArrayList;
import java.util.List;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;

/**
 * A wrapper for life-limit goals to throw events to the view.
 * @author Patrik Haar
 * @version 1.0
 */
public class LifeLimitGoalWrapper implements IGoal{

	private LifeLimitGoal llg;

	private List<GameListener> listeners;
	/**
	 * Creates a LifeLimitGoalWrapper with a life limit
	 * @param lifeLimit - the life limit
	 */
	public LifeLimitGoalWrapper(int lifeLimit) {
		this.listeners = new ArrayList<GameListener>();
		this.llg = new LifeLimitGoal(lifeLimit);
		llg.addGameListener(this);
		EventBus.getInstance().fireEvent(new EventBusEvent("lifeLimitAdded", lifeLimit, null));
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
			llg.gameEventPerformed(evtName, value);
		}
	}
	/**
	 * {@inheritDoc}
	 */
	public void update(int delta) {
		llg.update(delta);
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

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
	
	private int timeLeft;
	private int secondsLeft;
	
	private List<GameListener> listeners;
	
	/**
	 * Creates a time-limit goal.
	 * @param timeLimit The time-limit in seconds.
	 */
	public TimeLimitGoalWrapper(int timeLimit) {
		this.tlg = new TimeLimitGoal(timeLimit);
		tlg.addGameListener(this);
		this.listeners = new ArrayList<GameListener>();
		this.timeLeft = timeLimit*1000;
		this.secondsLeft = timeLimit;
		EventBus.getInstance().fireEvent(new EventBusEvent("timeLimitAdded", timeLimit, null));
	}
	
	public void gameEventPerformed(String evtName, Object value) {
		if (evtName.equals("gameOver")) {
			EventBus.getInstance().fireEvent(new EventBusEvent("gameOver", null, null));
			for (GameListener gl : listeners) {
				gl.gameEventPerformed(evtName, value);
			}
		} else {
			tlg.gameEventPerformed(evtName, value);
		}
	}

	public void update(int delta) {
		decreaseTime(delta);
		tlg.update(delta);
	}

	private void decreaseTime(int delta) {
		timeLeft -= delta;
		if (timeLeft/1000!=secondsLeft) {
			EventBus.getInstance().fireEvent(new EventBusEvent("updateTime", secondsLeft, null));
			secondsLeft = timeLeft/1000;
		}
	}
	
	public void addGameListener(GameListener gl) {
		listeners.add(gl);
	}

	public void removeGameListener(GameListener gl) {
		listeners.remove(gl);
	}

}

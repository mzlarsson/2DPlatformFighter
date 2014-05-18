package edu.chalmers.brawlbuddies.model;

import java.util.ArrayList;
import java.util.List;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.world.ICharacter;

/**
 * A wrapper for life-limit goals to throw events to the view.
 * @author Patrik Haar
 * @version 1.0
 */
public class LifeLimitGoalWrapper implements IGoal{

	private LifeLimitGoal llg;

	private List<GameListener> listeners;
	
	public LifeLimitGoalWrapper(int lifeLimit) {
		this.listeners = new ArrayList<GameListener>();
		this.llg = new LifeLimitGoal(lifeLimit);
		llg.addGameListener(this);
		EventBus.getInstance().fireEvent(new EventBusEvent("lifeLimitAdded", lifeLimit, null));
	}
	
	public void gameEventPerformed(String evtName, Object value) {
		if (evtName.equals("gameOver")) {
			EventBus.getInstance().fireEvent(new EventBusEvent("gameOver", null, null));
			for (GameListener gl : listeners) {
				gl.gameEventPerformed(evtName, value);
			}
		} else {
			if (evtName.equals("characterKilled")) {
				EventBus.getInstance().fireEvent(new EventBusEvent("characterDied", ((ICharacter)value).getID(), null));
			}
			llg.gameEventPerformed(evtName, value);
		}
	}

	public void update(int delta) {
		llg.update(delta);
	}

	public void addGameListener(GameListener gl) {
		listeners.add(gl);
		
	}

	public void removeGameListener(GameListener gl) {
		listeners.remove(gl);
	}
	
}

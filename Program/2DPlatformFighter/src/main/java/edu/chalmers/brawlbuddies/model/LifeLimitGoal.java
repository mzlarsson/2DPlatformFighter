package edu.chalmers.brawlbuddies.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.chalmers.brawlbuddies.model.world.ICharacter;

/**
 * An end-condition for games with a lifelimit.
 * @author Patrik Haar
 * @version 1.0
 */
public class LifeLimitGoal implements IGoal {
	
	private Map<Integer,Integer> lives;
	private int maxLives;
	
	private List<GameListener> listeners;
	/**
	 * Create a new LifeLimitGoal with a life limit
	 * @param lifeLimit - the life limit
	 */
	public LifeLimitGoal(int lifeLimit) {
		this.lives = new HashMap<Integer,Integer>();
		this.listeners = new ArrayList<GameListener>();
		this.maxLives = lifeLimit;
	}

	/**
	 *  {@inheritDoc}
	 */
	private void characterKilled(int id) {
		lives.put(id, lives.get(id)-1);
		int pplAlive = 0;
		int potentialWinner = -1;
		for(int i : lives.keySet()) {
			if (lives.get(i)>0) {
				pplAlive += 1;
				potentialWinner = i;
			}
		}
		if (pplAlive < 2) {
			gameOver(potentialWinner);
		}
	}
	
	/**
	 * Fires a Game Over event.
	 */
	private void gameOver(int id) {
		for (GameListener gl : listeners) {
			gl.gameEventPerformed("gameOver", id);
		}
	}
	/**
	 * {@inheritDoc}
	 */
	public void update(int delta) {
		// Does nothing.
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
	/**
	 * {@inheritDoc}
	 */
	public void gameEventPerformed(String evtName, Object value) {
		if (evtName.equals("characterAdded")) {
			lives.put(((ICharacter)value).getID(), maxLives);
		} else if (evtName.equals("characterKilled")) {
			characterKilled(((ICharacter)value).getID());
		}
	}
}

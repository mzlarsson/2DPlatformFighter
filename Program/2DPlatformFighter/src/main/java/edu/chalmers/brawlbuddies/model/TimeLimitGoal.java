package edu.chalmers.brawlbuddies.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.chalmers.brawlbuddies.model.world.ICharacter;

/**
 * An end-condition for games with a time-limit.
 * @author Patrik Haar
 * @version 1.0
 */
public class TimeLimitGoal implements IGoal{
	
	private Map<Integer,Integer> lives;
	private int timeLeft;
	
	private List<GameListener> listeners;
	
	/**
	 * Creates a time-limit goal.
	 * @param timeLimit The time-limit in seconds.
	 */
	public TimeLimitGoal(int timeLimit) {
		this.lives = new HashMap<Integer,Integer>();
		this.listeners = new ArrayList<GameListener>();
		this.timeLeft = timeLimit*1000;
	}

	public void update(int delta) {
		if (!decreaseGameTime(delta)) {
			gameOver();
		}
	}
	
	/**
	 * If the game is on a timer it is reduced.
	 * @param delta The time to be subtracted in milliseconds.
	 * @return <code>true</code> if there is time left on the clock. <code>false</code> if not.
	 */
	private boolean decreaseGameTime(int delta) {
		this.timeLeft -= delta;
		return this.timeLeft>0;
	}
	
	/**
	 * Decides the winner and fires a Game Over event.
	 */
	private void gameOver() {
		List<Integer> winnerID = new ArrayList<Integer>();
		int winnerLives = -9999999;
		for(int i : lives.keySet()) {
			if (lives.get(i)>winnerLives) {
				winnerID.clear();
				winnerID.add(i);
				winnerLives = lives.get(i);
			} else if (lives.get(i)==winnerLives) {
				winnerID.add(i);
			}
		}
		for (GameListener gl : listeners) {
			gl.gameEventPerformed("gameOver", winnerID.size()==1?winnerID.get(0):-1);
		}
	}

	public void addGameListener(GameListener gl) {
		listeners.add(gl);
	}

	public void removeGameListener(GameListener gl) {
		listeners.remove(gl);
	}

	public void gameEventPerformed(String evtName, Object value) {
		if (evtName.equals("characterAdded")) {
			lives.put(((ICharacter)value).getID(), 0);
		} else if (evtName.equals("characterKilled")) {
			int id = ((ICharacter)value).getID();
			lives.put(id, lives.get(id)-1);
		}
	}

}

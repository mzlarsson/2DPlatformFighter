package edu.chalmers.brawlbuddies.model;

/**
 * A listener interface for handling events from the model.
 * @author Patrik Haar
 * @version 1.0
 */
public interface GameListener {
	
	/**
	 * Triggered on a game change.
	 * @param evtName The name of the event.
	 * @param value The value of the event.
	 */
	public void gameEventPerformed(String evtName, Object value);
}

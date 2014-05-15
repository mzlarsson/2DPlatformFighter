package edu.chalmers.brawlbuddies.services.factories;

/**
 * A Class for creating a new unique ID.
 * @author Patrik Haar
 * @version 1.0
 */
public class IDFactory {

	private int currentID = 1;
	private static IDFactory instance;
	
	private IDFactory() {
	}
	
	public static IDFactory getInstance() {
		if (instance == null) {
			instance = new IDFactory();
		}
		return instance;
	}
	
	/**
	 * Creates a unique ID.
	 * @return The unique ID.
	 */
	public int getID() {
		return currentID++;
	}
}

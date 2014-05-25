package edu.chalmers.brawlbuddies.model;

/**
 * A Class for creating a new unique ID.
 * @author Patrik Haar
 * @version 1.0
 */
public class IDFactory {

	private int currentID = 1;
	private static IDFactory instance;
	/**
	 * Creates a new IDFactory
	 */
	private IDFactory() {
	}
	/**
	 * Get the instance of the IDFactory
	 * @return IDFactory - the instance of the IDFactory
	 */
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

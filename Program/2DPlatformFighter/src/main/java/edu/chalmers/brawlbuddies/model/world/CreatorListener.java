package edu.chalmers.brawlbuddies.model.world;

/**
 * This is the listener class to be used together with the class Creator.
 * @author Matz Larsson
 * @version 1.0
 *
 */

public interface CreatorListener {

	/**
	 * Triggered with the IGameObject that has been created
	 * @param object The object that has been created
	 */
	public void createdObject(IGameObject object);
	
}

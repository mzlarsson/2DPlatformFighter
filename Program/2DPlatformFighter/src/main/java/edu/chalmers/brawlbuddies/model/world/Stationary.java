package edu.chalmers.brawlbuddies.model.world;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;

/**
 * A MovementState for stationary GameObjects.
 * @author Patrik Haar
 * @version 1.0
 */
public class Stationary implements MovementState {

	private GameObject gameO;
	
	/**
	 * Creates a Stationary MovementState.
	 * @param gameO The GameObject affected.
	 * @param outerForce The outer forces affecting the GameObject.
	 */
	public Stationary(GameObject gameO, Velocity outerForce) {
		this.gameO = gameO;
		// Stationary things don't care about trivial things like gravity.
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Position update(int delta) {
		return gameO.getCenterPosition();
	}

}

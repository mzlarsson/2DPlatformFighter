package edu.chalmers.brawlbuddies.model.world;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;

/**
 * A MovementState for GameObjects on the ground.
 * @author Patrik Haar
 * @version 1.0
 */
public class Walking implements MovementState {

	private GameObject gameO;
	private Velocity outerForce;
	
	/**
	 * Creates a Walking MovementState.
	 * @param gameO The GameObject affected.
	 * @param outerForce The outer forces affecting the GameObject.
	 */
	public Walking(GameObject gameO, Velocity outerForce) {
		this.outerForce = outerForce;
		this.gameO = gameO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Position update(int delta) {
		Position oldPos = gameO.getCenterPosition().copy();
		Velocity tot = gameO.getTotalVelocity().add(outerForce);
		gameO.setCenterX( gameO.getCenterX() + (tot.getX()*((float)(delta))/MODIFIER) );
		gameO.setCenterY( gameO.getCenterY() + (tot.getY()*((float)(delta))/MODIFIER) );
		return oldPos;
	}

}

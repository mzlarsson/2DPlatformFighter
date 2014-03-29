package edu.chalmers.platformfighter;

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
		float yDiff = (tot.getY()*(delta/MODIFIER));
		gameO.setCenterX( gameO.getCenterX() + (tot.getX()*(delta/MODIFIER)) );
		gameO.setCenterY( gameO.getCenterY() + yDiff>0?0:yDiff );
		return oldPos;
	}

}

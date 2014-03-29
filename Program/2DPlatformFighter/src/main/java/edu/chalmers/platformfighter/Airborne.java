package edu.chalmers.platformfighter;

/**
 * A MovementState for GameObjects in the air.
 * @author Patrik Haar
 * @version 1.0
 */
public class Airborne implements MovementState {

	private GameObject gameO;
	private Velocity outerForce;
	
	/**
	 * Creates a Airborne MovementState.
	 * @param gameO The GameObject affected.
	 * @param outerForce The outer forces affecting the GameObject.
	 */
	public Airborne(GameObject gameO, Velocity outerForce) {
		this.outerForce = outerForce;
		this.gameO = gameO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Position update(int delta) {
		Position oldPos = gameO.getCenterPosition().copy();
		this.applyOuterForce(delta);
		Velocity tot = gameO.getTotalVelocity().add(outerForce);
		gameO.setCenterX( gameO.getCenterX() + (tot.getX()*(delta/MODIFIER)) );
		gameO.setCenterY( gameO.getCenterY() + (tot.getY()*(delta/MODIFIER)) );
		return oldPos;
	}

	/**
	 * Apply the outer force to the variable velocity.
	 * @param delta The time passed since last update in milliseconds.
	 */
	private void applyOuterForce(int delta) {
		gameO.getVariableVelocity().add(outerForce.scale(delta/MODIFIER));
	}
}

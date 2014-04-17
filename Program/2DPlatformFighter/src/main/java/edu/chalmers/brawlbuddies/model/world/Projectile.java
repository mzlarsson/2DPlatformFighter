package edu.chalmers.brawlbuddies.model.world;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.model.Position;

/**
 * A class for representing a Projectile in-game.
 * @author Patrik Haar
 * @version 0.1
 */
public class Projectile extends GameObject {

	private float lifetime;
	
	/**
	 * Creates a projectile with a Shape, Velocity and lifetime.
	 * @param shape The Shape of the projectile.
	 * @param vel The base Velocity of the projectile.
	 * @param lifetime How long the projectile shall exist in milliseconds.
	 */
	public Projectile(Shape shape, Movement mov, float lifetime) {
		super(mov, shape);
		this.lifetime = lifetime;
	}
	
	/**
	 * Check if the Projectile is still active.
	 * @return true if the Projectile is active.
	 */
	public boolean isActive() {
		return lifetime>0;
	}
	
	@Override
	public GameObject copy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//TODO Currently unaffected by gravity.
	@Override
	public Position update(int delta) {
		lifetime -= delta;
		Position oldPos = this.getCenterPosition().copy();
		Position newPos = this.getMovement().nextPosition(this.getCenterPosition(), delta);
		this.setCenterPosition(newPos);
		return oldPos;
	}

}

package edu.chalmers.brawlbuddies.model.world;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;

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
	public Projectile(Shape shape, Velocity vel, float lifetime) {
		super(vel, shape);
		this.lifetime = lifetime;
	}
	
	@Override
	public void setMovementState(MovementState ms) {
		// TODO Auto-generated method stub
	}

	@Override
	public GameObject copy() {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO Currently unaffected by gravity.
	@Override
	public Position update(int delta) {
		Position oldPos = getCenterPosition().copy();
		Velocity tot = getTotalVelocity();
		setCenterX( getCenterX() + (tot.getX()*((float)(delta))/1000) );	//FIXME Change the 1000 value to a constant modifier in the later Constants class.
		setCenterY( getCenterY() + (tot.getY()*((float)(delta))/1000) );	//FIXME Change the 1000 value to a constant modifier in the later Constants class.
		return oldPos;
	}

}

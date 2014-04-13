package edu.chalmers.brawlbuddies.model.world;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.util.SlickUtil;

/**
 * A class to hold and fire a specific Projectile-type.
 * @author Patrik Haar
 * @version 0.1
 */
public class ProjectileCreator {

	private Shape shape;
	private float speed;
	private float lifetime;
	
	/**
	 * Creates a new projectileCreator with a Shape, projectile speed and lifetime duration.
	 * @param shape The Shape of the projectile.
	 * @param speed How fast the projectile shall travel.
	 * @param lifetime How long the projectile shall exist in milliseconds.
	 */
	public ProjectileCreator(Shape shape, float speed, float lifetime) {
		this.shape = shape;
		this.speed = speed;
		this.lifetime = lifetime;
	}
	
	/**
	 * Creates and launches a projectile from the given position with the given aim.
	 * @param pos The position where the projectile will be created at.
	 * @param aim The direction the projectile will fly.
	 * @return The projectile created.
	 */
	public Projectile fire(Position pos, Aim aim) {
		Shape tmp = SlickUtil.copy(shape);
		tmp.setCenterX(pos.getX());
		tmp.setCenterY(pos.getY());
		return new Projectile(tmp, new Velocity(aim, speed), lifetime);
	}

}

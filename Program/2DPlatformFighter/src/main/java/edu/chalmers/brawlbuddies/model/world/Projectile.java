package edu.chalmers.brawlbuddies.model.world;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Skills.CharacterInterface;
import edu.chalmers.brawlbuddies.model.Skills.Effect;

/**
 * A class for representing a Projectile in-game.
 * 
 * @author Patrik Haar
 * @version 0.1
 */
public class Projectile extends GameObject {

	private float lifetime;
	private Effect[] effects;

	/**
	 * Creates a projectile with a Shape, Velocity and lifetime.
	 * 
	 * @param shape
	 *            The Shape of the projectile.
	 * @param vel
	 *            The base Velocity of the projectile.
	 * @param lifetime
	 *            How long the projectile shall exist in milliseconds.
	 */
	public Projectile(Shape shape, Movement mov, float lifetime,
			Effect[] effects) {
		super(mov, shape);
		this.lifetime = lifetime;
		this.effects = effects;
	}

	/**
	 * Check if the Projectile is still active.
	 * 
	 * @return true if the Projectile is active.
	 */
	public boolean isActive() {
		return lifetime > 0;
	}

	@Override
	public GameObject copy() {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO Currently unaffected by gravity.
	@Override
	public Position update(int delta) {
		lifetime -= delta;
		Position oldPos = this.getCenterPosition().copy();
		Position newPos = this.getMovement().nextPosition(
				this.getCenterPosition(), delta);
		this.setCenterPosition(newPos);
		return oldPos;
	}

	public void onCollision(GameObject o, Movement.Alignment alignment) {
		if (o instanceof CharacterInterface) {
			System.out.println("i hit a character");
			for (int i = 0; i < effects.length; i++) {
				effects[i].effect(o);
				super.onCollision(o, alignment);
			}
		} else {
			System.out.println("i didnt hit a character");
			super.onCollision(o, alignment);
		}
	}
}

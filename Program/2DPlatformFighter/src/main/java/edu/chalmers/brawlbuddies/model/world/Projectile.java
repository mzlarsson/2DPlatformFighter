package edu.chalmers.brawlbuddies.model.world;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Skills.CharacterInterface;
import edu.chalmers.brawlbuddies.model.Skills.Effect;
import edu.chalmers.brawlbuddies.util.ListCopy;

/**
 * A class for representing a Projectile in-game.
 * 
 * @author Patrik Haar
 * @version 0.1
 */
public class Projectile extends GameObject {

	private float lifetime;
	private List<Effect> effects;

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
			List<Effect> effects) {
		super(mov, shape);
		this.lifetime = lifetime;
		this.effects = ListCopy.simpleCopy(effects);
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
			if(!effects.isEmpty()){
				for(int i = 0 ; i < effects.size(); i++){
					if(effects.get(i).effect(o)){
						System.out.println("Effective");
						effects.remove(i);
						i--;
					}
				}
			}
			if( effects.isEmpty() ){
				System.out.println("No more effects");
				lifetime = -1;
			}
		} else {
			lifetime = -1;
		}
	}
}

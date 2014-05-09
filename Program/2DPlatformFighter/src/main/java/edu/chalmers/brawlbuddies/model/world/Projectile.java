package edu.chalmers.brawlbuddies.model.world;

import java.util.List;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.skills.IEffect;
import edu.chalmers.brawlbuddies.util.ListCopy;

/**
 * A class for representing a Projectile in-game.
 * 
 * @author Patrik Haar
 * @version 0.1
 */
public class Projectile extends GameObject implements IProjectile{

	private int typeID;
	private float lifetime;
	private List<IEffect> effects;
	private boolean destroyed = false;

	/**
	 * Creates a projectile with a Shape, Velocity and lifetime.
	 * 
	 * @param shape The Shape of the projectile.
	 * @param vel The base Velocity of the projectile.
	 * @param lifetime How long the projectile shall exist in milliseconds.
	 */
	public Projectile(Shape shape, Movement mov, float lifetime, int id, List<IEffect> effects) {
		super(mov, shape);
		this.lifetime = lifetime;
		this.typeID = id;
		//TODO is this good?
		this.effects = ListCopy.simpleCopy(effects);
	}
	
	/**
	 * Returns the angle of this projectile
	 * @return The angle of this projectile
	 */
	public double getTheta(){
		return this.getTotalVelocity().getTheta();
	}
	
	@Override
	public int getTypeID() {
		return typeID;
	}

	@Override
	public Position update(int delta) {
		lifetime -= delta;
		return this.getMovement().nextPosition(this.getCenterPosition(), delta);
	}

	public void onCollision(IGameObject obj, Movement.Alignment alignment) {
		if(obj instanceof Impassible){
			this.destroyed = true;
		}
		else if(!effects.isEmpty()){
				for(int i = 0 ; i < effects.size(); i++){
					if(effects.get(i).effect(this, obj)){
						effects.remove(i);
						i--;
					}
				}
		}
	}

	public boolean isDestroyed() {
		return this.effects.isEmpty() || this.destroyed || this.lifetime<0;
	}

}

package edu.chalmers.brawlbuddies.model.world;

import java.util.List;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.skills.IEffect;
import edu.chalmers.brawlbuddies.util.SlickUtil;

/**
 * A class to hold and fire a specific Projectile-type.
 * 
 * @author Patrik Haar
 * @version 0.1
 */
public class ProjectileCreator implements IProjectileCreator {
	private List<IEffect> effects;
	private Shape shape;
	private float speed;
	private float lifetime;
	private Velocity gravity;
	private int typeID;
	private int creatorId;

	/**
	 * Creates a new projectileCreator with a Shape, projectile speed and
	 * lifetime duration.
	 * 
	 * @param shape The Shape of the projectile.
	 * @param speed How fast the projectile shall travel.
	 * @param lifetime How long the projectile shall exist in milliseconds.
	 * @param typeID TypeID of the projectile to create
	 * @param gravity What the gravity should be, uses default if null.
	 * @param effects The effects to apply to the projectile
	 */
	public ProjectileCreator(Shape shape, float speed, float lifetime, int typeID,
			Velocity gravity, List<IEffect> effects) {
		this.shape = shape;
		this.speed = speed;
		this.lifetime = lifetime;
		this.typeID = typeID;
		this.gravity = gravity==null?Movement.DEFAULT_GRAVITY.copy():gravity;
		this.effects = effects;
	}

	public void setCreatorID(int a) {
		this.creatorId = a;
		if (effects != null) {
			for (int i = 0; i < effects.size(); i++) {
				effects.get(i).setCreatorID(a);
			}
		}
	}
	
	public int getTypeID() {
		return typeID;
	}
	
	/**
	 * Creates a projectile with given data
	 * @param character The character which fires it
	 * @return The created projectile
	 */
	public Projectile fire(Character character){
		return this.fire(character.getCenterPosition(), character.getAim());
	}

	/**
	 * Creates a projectile from the given position with the given aim.
	 * 
	 * @param pos The position where the projectile will be created at.
	 * @param aim The direction the projectile will fly.
	 * @return The projectile created.
	 */
	public Projectile fire(Position pos, Aim aim) {
		Shape tmp = SlickUtil.copy(shape);
		tmp.setCenterX(pos.getX());
		tmp.setCenterY(pos.getY());
		return new Projectile(tmp, new Movement(new Velocity(aim, speed), gravity, true),
							lifetime, typeID, effects);
	}

}

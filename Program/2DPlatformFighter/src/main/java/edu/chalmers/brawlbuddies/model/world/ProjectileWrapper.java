package edu.chalmers.brawlbuddies.model.world;

import java.util.List;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.IWrapper;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.skills.IEffect;
import edu.chalmers.brawlbuddies.model.world.Movement.Alignment;
/**
 * A wrapper class for a Projectile
 * @author Patrik Haar
 *
 */
public class ProjectileWrapper implements IWrapper, IProjectile {
	private Projectile projectile;
	
	/**
	 * Creates a new ProjectileWrapper with a projectile
	 * @param proj - the projectile the wrapper contains
	 */
	public ProjectileWrapper(Projectile proj) {
		this.projectile = proj;
		EventBus.getInstance().fireEvent(new EventBusEvent("createObject", this, null));
	}
	/**
	 * Creates a new ProjectileWrapper with a projectile that is set by a
	 * shape, movement, lifetime , ID and a list of effects
	 * @param shape - the shape of the projectile
	 * @param mov - the movement of the projectile
	 * @param lifetime - the lifetime of the projectile
	 * @param id - the ID of the projectile
	 * @param effects - the list of effects of the projectile
	 */
	public ProjectileWrapper(Shape shape, Movement mov, float lifetime, int id,
			List<IEffect> effects) {
		this(new Projectile(shape, mov, lifetime, id, effects));
	}
	/**
	 * {@inheritDoc}
	 */
	public int getTypeID() {
		return projectile.getTypeID();
	}
	/**
	 * {@inheritDoc}
	 */
	public int getUniqeID() {
		return projectile.getID();
	}
	/**
	 * {@inheritDoc}
	 */
	public Velocity getTotalVelocity() {

		return projectile.getTotalVelocity();
	}
	/**
	 * {@inheritDoc}
	 */
	public Position getPosition() {

		return projectile.getPosition();
	}
	/**
	 * {@inheritDoc}
	 */
	public void setPosition(Position pos) {
		projectile.setPosition(pos);
		EventBus eb = EventBus.getInstance();
		eb.fireEvent(new EventBusEvent("updateObject", this, null));

	}
	/**
	 * {@inheritDoc}
	 */
	public Position getCenterPosition() {

		return projectile.getCenterPosition();
	}
	/**
	 * {@inheritDoc}
	 */
	public void setCenterPosition(Position pos) {
		projectile.setCenterPosition(pos);
		EventBus eb = EventBus.getInstance();
		eb.fireEvent(new EventBusEvent("updateObject", this, null));

	}
	/**
	 * {@inheritDoc}
	 */
	public Shape getShape() {
		return projectile.getShape();
	}
	/**
	 * {@inheritDoc}
	 */
	public void setShape(Shape shape) {
		projectile.setShape(shape);

	}
	/**
	 * {@inheritDoc}
	 */
	public Position update(int delta) {
		return projectile.update(delta);
	}
	/**
	 * {@inheritDoc}
	 */
	public boolean isDestroyed() {
		if (projectile.isDestroyed()) {
			EventBus eb = EventBus.getInstance();
			eb.fireEvent(new EventBusEvent("removeObject", this, null));
		}
		return projectile.isDestroyed();
	}
	/**
	 * {@inheritDoc}
	 */
	public void onCollision(IGameObject object, Alignment alignment) {
		projectile.onCollision(object, alignment);
	}
	/**
	 * {@inheritDoc}
	 */
	public int getID() {
		return projectile.getID();
	}
	/**
	 * {@inheritDoc}
	 */
	public double getTheta() {
		return projectile.getTheta();
	}

}

package edu.chalmers.brawlbuddies.model.world;

import java.util.List;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.IWrapper;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.skills.IEffect;
import edu.chalmers.brawlbuddies.model.world.Movement.Alignment;

public class ProjectileWrapper implements IWrapper, IProjectile {
	private Projectile projectile;

	public ProjectileWrapper(Projectile proj) {
		this.projectile = proj;
		EventBus.getInstance().fireEvent(new EventBusEvent("createObject", this, null));
	}
	public ProjectileWrapper(Shape shape, Movement mov, float lifetime, int id,
			List<IEffect> effects) {
		this(new Projectile(shape, mov, lifetime, id, effects));
	}

	public int getTypeID() {
		return projectile.getTypeID();
	}

	public int getUniqeID() {
		return projectile.getID();
	}

	public Velocity getTotalVelocity() {

		return projectile.getTotalVelocity();
	}

	public Position getPosition() {

		return projectile.getPosition();
	}

	public void setPosition(Position pos) {
		projectile.setPosition(pos);
		EventBus eb = EventBus.getInstance();
		eb.fireEvent(new EventBusEvent("updateObject", this, null));

	}

	public Position getCenterPosition() {

		return projectile.getCenterPosition();
	}

	public void setCenterPosition(Position pos) {
		projectile.setCenterPosition(pos);
		EventBus eb = EventBus.getInstance();
		eb.fireEvent(new EventBusEvent("updateObject", this, null));

	}

	public Shape getShape() {
		return projectile.getShape();
	}

	public void setShape(Shape shape) {
		projectile.setShape(shape);

	}

	public Position update(int delta) {
		return projectile.update(delta);
	}

	public boolean isDestroyed() {
		if (projectile.isDestroyed()) {
			EventBus eb = EventBus.getInstance();
			eb.fireEvent(new EventBusEvent("removeObject", this, null));
		}
		return projectile.isDestroyed();
	}

	public void onCollision(IGameObject object, Alignment alignment) {
		projectile.onCollision(object, alignment);
	}

	public int getID() {
		return projectile.getID();
	}

	public double getTheta() {
		return projectile.getTheta();
	}

}

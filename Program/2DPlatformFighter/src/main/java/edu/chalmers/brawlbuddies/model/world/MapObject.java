package edu.chalmers.brawlbuddies.model.world;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.Movement.Alignment;

/**
 * A class to represent a stationary object on the map.
 * @author Patrik Haar
 * @version 1.0
 */
public class MapObject extends GameObject implements Impassible{

	/**
	 * Constructs a stationary MapObject with a Shape.
	 * @param shape The Shape to be used.
	 */
	public MapObject(Shape shape) {
		super(shape);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Position update(int delta) {
		return this.getCenterPosition();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTypeID() {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isDestroyed() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void onCollision(IGameObject object, Alignment alignment) {
		//Do nothing.
	}

}

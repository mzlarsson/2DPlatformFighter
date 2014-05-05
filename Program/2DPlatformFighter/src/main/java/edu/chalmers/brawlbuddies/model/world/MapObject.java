package edu.chalmers.brawlbuddies.model.world;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.model.Position;

/**
 * A class to represent a stationary object on the map.
 * @author Patrik Haar
 * @version 1.0
 */
public class MapObject extends GameObject {

	/**
	 * Constructs a stationary MapObject with a Shape.
	 * @param shape The Shape to be used.
	 */
	public MapObject(Shape shape) {
		super(shape);
	}
	
	@Override
	public Position update(int delta) {
		return this.getCenterPosition();
	}

	@Override
	public GameObject copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTypeID() {
		return 0;
	}

}

package edu.chalmers.brawlbuddies;

import org.newdawn.slick.geom.Vector2f;
/**
 * A class describing a position object for our game
 * @author David Gustafsson
 * @version 0.2
 */
public class Position extends Vector{
	/**
	 * Creates a new position with a x and y value
	 * @param x the x-value of the position
	 * @param y the y-value of the position
	 */
	public Position(float x, float y) {
		super(x, y);
	}
/**
 * Creates a new position from a vector
 * @param f a vector
 */
	public Position(Vector2f f){
		this(f.getX(), f.getY());
	}
	/**
	 * Returns a copy of this position
	 * @return a copy of this position
	 */
	public Position copy() {
		return new Position(x, y); 
	}
	/**
	 * Add a vector to this position and return the resulting position
	 */
	public Position add(Vector v){
		return (Position)super.add(v);
	}

}

package edu.chalmers.brawlbuddies;

import org.newdawn.slick.geom.Vector2f;
/**
 * A class that describes velocity object to our game
 * @author David Gustafsson
 * @version 0.2 
 */
public class Velocity extends Vector {
	/**
	 * Creates a new Velocity from a x and y value
	 * @param x the x value of the new Velocity
	 * @param y the y value of the new Velocity
	 */
	public Velocity(float x, float y) {
		super(x,y);
	}
	/**
	 * Creates a new Velocity from a vector
	 * @param v the vector to create a new velocity
	 */
	public Velocity(Vector2f v){
		this(v.getX(), v.getY());
	}
	/**
	 * Returns a copy of this velocity.
	 * @return the copy of this velocity
	 */
	public Velocity copy() {
		return new Velocity(x, y);
	}

	
	/**
	 * Adds these x and y value of this velocity with given x and y values.
	 * @param x the x value to add to this velocity
	 * @param y the y value to add to this velocity
	 * @return The resulting velocity
	 */
	public Velocity add(float x, float y) {
		return (Velocity)super.add(x,y);
	}
	
	/**
	 * Adds these x and y value of this velocity with given x and y values.
	 * @param x the x value to add to this velocity
	 * @param y the y value to add to this velocity
	 * @return The resulting velocity
	 */
	public Velocity add(Velocity v) {
		return (Velocity)super.add(v);
	}
	
	/**
	 * Multiplies the given Velocity with a float number.
	 * @return The resulting Velocity vector.
	 */
	public Velocity scale(float f) {
		return new Velocity(this.getX()*f, this.getY()*f);
	}
}

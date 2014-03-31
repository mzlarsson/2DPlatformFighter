package edu.chalmers.platformfighter;

import org.newdawn.slick.geom.Vector2f;
/**
 * A class that describes velocity object to our game
 * @author David Gustafsson
 *
 */
public class Velocity extends Vector2f {
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
	 * Add a vector to this velocity and returns the resulting velocity
	 * @param variableSpeed the velocity to add to this velocity
	 * @return the resulting velocity
	 */
	public Velocity add(Velocity variableSpeed) {
		Velocity v = new Velocity (this.add(variableSpeed));
		return v;
	}

	/**
	 * Add a x and y value to this velocity and returns the resulting velocity
	 * @param x the x value to add to this velocity
	 * @param y the y value to add to this velocity
	 * @return The resulting velocity
	 */
	public Velocity add(float x, float y) {
		return (Velocity)(super.add(new Vector2f(x,y)));
	}
	/**
	 * Increase our velocity with a given float value 
	 * @param f The value to scale this velocity by.
	 */
	public void increase(float f){
		this.set(f * x, f * y);
	}
	

}

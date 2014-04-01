package edu.chalmers.platformfighter;

import org.newdawn.slick.geom.Vector2f;
/**
 * A class that describes velocity object to our game
 * @author David Gustafsson
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
	 * Increase the x and y value of this velocity with given velocity.
	 * @param variableSpeed the velocity to add to this velocity
	 * @return the resulting velocity
	 */
	public void increase(Velocity variableSpeed) {
		super.add(variableSpeed);
	}

	/**
	 * Increase the x and y value of this velocity with given x and y values.
	 * @param x the x value to add to this velocity
	 * @param y the y value to add to this velocity
	 * @return The resulting velocity
	 */
	public void increase(float x, float y) {
		super.add(new Vector2f(x,y));
	}
	
	/**
	 * Adds these x and y value of this velocity with given x and y values.
	 * @param x the x value to add to this velocity
	 * @param y the y value to add to this velocity
	 * @return The resulting velocity
	 */
	public Velocity add(float x, float y) {
		return new Velocity(this.getX()+x, this.getY()+y);
	}
	
	/**
	 * Adds these x and y value of this velocity with given x and y values.
	 * @param x the x value to add to this velocity
	 * @param y the y value to add to this velocity
	 * @return The resulting velocity
	 */
	public Velocity add(Velocity v) {
		return this.add(v.getX(), v.getY());
	}
	
	/**
	 * Multiplies the given Velocity with a float number.
	 * @return The resulting Velocity vector.
	 */
	public Velocity scale(float f) {
		return new Velocity(this.getX()*f, this.getY()*f);
	}
}

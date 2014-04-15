package edu.chalmers.brawlbuddies.model;

import org.newdawn.slick.geom.Vector2f;
/**
 * An adapter class to vector2f to change implementation of some methods.
 * @author David Gustafsson
 * @version 0.2
 * @revised Matz Larsson
 * 
 */
public class Vector extends Vector2f {
	
	/**
	 * Initiates a vector with coordinates
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public Vector(float x, float y){
		super(x , y);
	}
	
	/**
	 * Returns a Vector which is a result of an addition of this vector and v.
	 * NOTE: This does not affect this vector.
	 * @param v The vector to add
	 * @return A Vector which is the result of an addition with this and v.
	 */
	public Vector add(Vector v){
		return add(v.getX(), v.getY());
	}

	/**
	 * Returns a Vector which is a result of an addition of this vector and the Vector (x, y).
	 * NOTE: This does not affect this vector.
	 * @param x The x-coordinate to add
	 * @param y The y-coordinate to add
	 * @return A Vector which is the result of an addition with this and the Vector (x, y).
	 */
	public Vector add(float x , float y){
		Vector v = 	this.copy();
		v.set(this.getX() + x ,this.getY() + y);
		return v;
	}
	
	/**
	 * Copies the current Vector
	 * @return A copy of this Vector
	 */
	public Vector copy(){
		return new Vector(this.getX(), this.getY());
	}
	
	/**
	 * Increases the current Vector with the Vector (x, y)
	 * @param x Addition to the x-coordinate
	 * @param y Addition to the y-coordinate
	 */
	public void increase(float x , float y){
		this.set(this.getX() + x, this.getY() + y);
	}
	
	/**
	 * Increases the current Vector with the Vector v
	 * @param v The Vector to increase this Vector with.
	 */
	public void increase(Vector v){
		this.increase(v.getX() , v.getY());
	}
	
	/**
	 * Decreases the current Vector with the Vector (x, y)
	 * @param x X-coordinate of removal Vector
	 * @param y Y-coordinate of removal Vector
	 */
	public void decrease(float x, float y){
		this.set(this.getX() - x, this.getY() - y);
	}
	
	/**
	 * Decreases the current Vector with the Vector v
	 * @param v The Vector to decrease this Vector with.
	 */
	public void decrease(Vector v){
		this.decrease(v.getX(), v.getY());
	}
	
	/**
	 * Returns the normalized version of this Vector.
	 * NOTE: This does not affect this Vector.
	 * @return A normalized Vector (same direction, length 1)
	 */
	public Vector getNormalized(){
		Vector2f v = this.getNormal();
		return new Vector(v.getX() , v.getY());		
	}
}

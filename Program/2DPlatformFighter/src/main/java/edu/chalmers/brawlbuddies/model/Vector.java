package edu.chalmers.brawlbuddies.model;

import org.newdawn.slick.geom.Vector2f;
/**
 * An adapter class to vector2f to change implementation of some methods.
 * @author David Gustafsson
 * @version 0.2
 * 
 */
public class Vector extends Vector2f {
	public Vector(float x, float y){
		super(x , y);
	}
	
	public Vector add(Vector v){
		return add(v.getX(), v.getY());
	}
	
	public Vector add(float x , float y){
	Vector v = 	this.copy();
	v.set(this.getX() + x ,this.getY() + y);
	return v;
	}
	/**
	 * Add x and y value to a Vector
	 * @param x
	 * @param y
	 */
	public Vector copy(){
		return new Vector(this.getX(), this.getY());
	}
	public void Increase(Float x , Float y){
		this.set(this.getX() + x, this.getY() + y);
	}
	/**
	 * Add a vector2f to a Vector
	 * @param v
	 */
	public void Increase(Vector v){
		this.Increase(v.getX() , v.getY());
	}
	public Vector getNormalized(){
		Vector2f v = this.getNormal();
		return new Vector(v.getX() , v.getY());		
	}
}

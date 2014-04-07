package edu.chalmers.brawlbuddies;

import org.newdawn.slick.geom.Vector2f;
/**
 * An adapter class to vector2f to change implementation of some methods.
 * @author David Gustafsson
 * 
 */
public class Vector extends Vector2f {
	public Vector(float x, float y){
		super(x , y);
	}
	
	public Vector add(Vector2f v){
		return add(v.getX(), v.getY());
	}
	public Vector add(float x , float y){
		// TODO check if satisfactory solution
		if(this instanceof Position){
			return new Position(this.getX() + x, this.getY() + y);
		}
		if(this instanceof Velocity){
			return new Velocity(this.getX() + x, this.getY() + y);
		}
		return new Vector(this.getX() + x, this.getY() + y);	
	}
	/**
	 * Add x and y value to a Vector
	 * @param x
	 * @param y
	 */
	public void Increase(Float x , Float y){
		this.set(this.getX() + x, this.getY() + y);
	}
	/**
	 * Add a vector2f to a Vector
	 * @param v
	 */
	public void Increase(Vector2f v){
		this.Increase(v.getX() , v.getY());
	}
}

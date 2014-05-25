package edu.chalmers.brawlbuddies.model;

/**
 * A class describing a position object for our game
 * @author David Gustafsson
 * @version 1.0
 */
public class Position extends Vector{

	private static final long serialVersionUID = 1L;

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
	 * @param f A vector
	 */
	public Position(Vector f){
		this(f.getX(), f.getY());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Position copy() {
		return new Position(x, y); 
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Position add(Vector v){
		return (Position)super.add(v);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Position add(float x, float y){
		return (Position)super.add(x, y);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Position scale(float scale){
		return (Position)super.scale(scale);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Position subtract(float x, float y){
		return (Position)super.subtract(x, y);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Position subtract(Vector v){
		return (Position)super.subtract(v);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString(){
		return "Position (" + this.getX() + " , " + this.getY() + ")";
	}

}

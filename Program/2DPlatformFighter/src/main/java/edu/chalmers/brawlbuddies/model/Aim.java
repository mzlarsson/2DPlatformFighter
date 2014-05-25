package edu.chalmers.brawlbuddies.model;

/**
 * A class describing a Players aim 
 * @author David Gustafsson
 * @revised Patrik Haar
 * @version 1.0
 *
 */
public class Aim extends Vector {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new aim
	 * @param x - The x component to assign
	 * @param y - The y component to assign 
	 */
	public Aim(float x , float y){
		super(x , y);
	}
	
	/**
	 * Creates a new aim based on the difference between two position 
	 * @param start - the start postion 
	 * @param end - the end postion
	 */
	public Aim(Position start, Position end) {
		this(end.getX() - start.getX(), end.getY() - start.getY());
	}
	
	/**
	 * Creates a new aim based on a direction
	 * @param dir - the direction that the aim shall correspond to
	 */
	public Aim(Direction dir) {
		this(dir.getX(), dir.getY());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Aim copy(){
		return new Aim(this.getX() , this.getY());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Aim add(Vector v){
		return (Aim)super.add(v);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Aim add(float x , float y){
		return (Aim)super.add(x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	public Aim getNormalized() {
		Vector v = super.getNormalized();
		return new Aim(v.getX(), v.getY());
	}

	/**
	 * {@inheritDoc}
	 */
	public Aim scale(float f) {
		return (Aim)super.scale(f);
	}

	/**
	 * {@inheritDoc}
	 */
	public Direction getDirection(){
		return Direction.getDirection(this.getTheta());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString(){
		return "Aim (" + this.getX() + " , " + this.getY() + ")" ;
	}
}

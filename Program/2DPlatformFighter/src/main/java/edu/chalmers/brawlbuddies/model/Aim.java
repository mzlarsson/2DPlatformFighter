package edu.chalmers.brawlbuddies.model;
/**
 * A class describing a Players aim 
 * @author David Gustafsson
 *
 */
//TODO Write more comments
//TODO See if more functionality is needed
public class Aim extends Vector {

	public Aim(Position start, Position end) {
		this(end.getX() - start.getX(), end.getY() - start.getY());
	}
	public Aim(float x , float y){
		super(x , y);
	}
	public Aim copy(){
		return new Aim(this.getX() , this.getY());
	}
	public Aim add(Vector v){
		return (Aim)super.add(v);
	}
	public Aim add(float x , float y){
		return (Aim)super.add(x, y);
	}
}

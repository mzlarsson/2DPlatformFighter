package edu.chalmers.brawlbuddies.model;

/**
 * A class describing a Players aim 
 * @author David Gustafsson
 * @revised Patrik Haar
 *
 */

public class Aim extends Vector {

	public Aim(Position start, Position end) {
		this(end.getX() - start.getX(), end.getY() - start.getY());
	}
	public Aim(Direction dir) {
		this(dir.getX(), dir.getY());
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
	public Aim getNormalized() {
		Vector v = super.getNormalized();
		return new Aim(v.getX(), v.getY());
	}
	public Aim scale(float f) {
		return (Aim)super.scale(f);
	}
}

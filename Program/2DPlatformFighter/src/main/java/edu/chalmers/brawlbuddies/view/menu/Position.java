package edu.chalmers.brawlbuddies.view.menu;

/**
 * Basic setup to represent a Position
 * @author Matz Larsson
 * @version 1.0
 *
 */

public class Position {
	
	private int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public Position copy(){
		return new Position(this.x, this.y);
	}
	/**
	 * {@inheritDoc}
	 */
	public String toString(){
		return "edu.chalmers.brawlbuddies.view.menu.Position ("+this.x+", "+this.y+")";
	}

}

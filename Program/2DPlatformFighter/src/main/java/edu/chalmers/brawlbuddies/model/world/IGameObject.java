package edu.chalmers.brawlbuddies.model.world;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
/**
 * A interface to describe game object 
 * @author Matz Larsson
 * @version 1.0
 *
 */
public interface IGameObject{
	
	/**
	 * Get the total velocity of the game object
	 * @return Velocity- the total velocity of the game object
	 */
	public Velocity getTotalVelocity();
	
	/**
	 * Get the position of the game object
	 * @return Position - the position of the game object
	 */
	public Position getPosition();
	
	/**
	 * Set the position of the game object
	 * @param pos - the new Position of the game object
	 */
	public void setPosition(Position pos);
	
	/**
	 * Get the position of the center of the game object
	 * @return Position - the posistion of the center of the game object
	 */
	public Position getCenterPosition();
	
	/**
	 * Set the position of the center of the game object
	 * @param pos - the new center position of the game object
	 */
	public void setCenterPosition(Position pos);
	
	/**
	 * Get the shape of the game object
	 * @return Shape - the shape of the game object
	 */
	public Shape getShape();
	
	/**
	 * Set the shap of the game object
	 * @param shape - the new shape of the game object
	 */
	public void setShape(Shape shape);
	
	/**
	 * Update the game object with the time between the last update in miliseconds
	 * @param delta - the time in miliseconds since the last update.
	 * @return Position- the new Position of the game object
	 */
	public Position update(int delta);
	
	/**
	 * Return true if the game object have been destroyed
	 * @return boolean - true if the game object have been destroyed
	 */
	public boolean isDestroyed();
	
	/**
	 * Collides the game object with another game object with a alignment.
	 * @param object - the colliding object
	 * @param alignment - the alignment of the collision
	 */
	public void onCollision(IGameObject object, Movement.Alignment alignment);
	
	/**
	 * Get the ID of the game object
	 * @return int - the ID of the game object
	 */
	public int getID();
	
	/**
	 * Get the type ID of the game object
	 * @return int - the type ID of the game object
	 */
	public int getTypeID();
	
}

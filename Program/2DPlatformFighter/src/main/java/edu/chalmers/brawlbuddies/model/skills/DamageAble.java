package edu.chalmers.brawlbuddies.model.skills;
/**
 *  A interface to describe a object that can be damaged
 * @author David Gustafsson
 *
 */
public interface DamageAble {
	/** 
	 * Damages the object with a amount
	 * @param a - the amount of damage the object will take
	 */
	public void takeDamage(float a);
	/**
	 * Get the ID of the Object
	 * @return the ID of the object
	 */
	public int getID();

}

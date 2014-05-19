package edu.chalmers.brawlbuddies.model.skills;
/**
 *  A interface to describe a object that can be healed
 * @author David Gustafsson
 *
 */
public interface HealAble {
	/**
	 *  Heal the object with a amount
	 * @param healAmount- the amount of healing the object will take
	 */
	public void heal(float healAmount);
	/**
	 *  Restore the health of the object
	 */
	public void restoreHealth();

	/**
	 * Get the ID of the Object
	 * @return the ID of the object
	 */
	public int getID();

	
}

package edu.chalmers.brawlbuddies.model.skills;
/**
 * A interface to be used if a object can take damage
 * @author David Gustafsson
 *
 */
public interface DamageAble {
	public void takeDamage(float a);
	public int getID();

}

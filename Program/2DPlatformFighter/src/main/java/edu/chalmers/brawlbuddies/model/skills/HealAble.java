package edu.chalmers.brawlbuddies.model.skills;
/**
 * A interface to be used if a object can be healed
 * @author David Gustafsson
 *
 */
public interface HealAble {
	
	public void heal(float a);
	public void restoreHealth();
	
}

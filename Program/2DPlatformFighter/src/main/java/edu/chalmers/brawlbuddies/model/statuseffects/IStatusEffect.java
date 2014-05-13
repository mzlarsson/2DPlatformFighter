package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.world.ICharacter;
/**
 * A interface describing a status effect 
 * @author David Gustafsson
 *
 */
public interface IStatusEffect {
	/**
	 * Updates the status effect with the character the status effect
	 * will effect and the time gone from the last update in milliseconds
	 * @param c - the character effected by the status effects
	 * @param delta - the time gone from the last update in milliseconds
	 */
	public void update(ICharacter c , float delta);
	
	/**
	 * Return true if the status effect is active return false if it's inactive
	 * @return boolean - true if active , false if inactive
	 */
	public boolean isActive();
	
	/**
	 * Get the duration left of the status effect
	 * @return float - the duration left of the status effect
	 */
	public float getDuration();
	/**
	 * Get a copy of the status effect
	 * @return a copy of the status effect
	 */
	public IStatusEffect copy();

}

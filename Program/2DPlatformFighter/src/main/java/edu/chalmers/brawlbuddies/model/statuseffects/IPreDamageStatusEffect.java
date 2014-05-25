package edu.chalmers.brawlbuddies.model.statuseffects;

/**
 * A interface to describe a status effect that will effect the amount of damage 
 * a character can take.
 * @author David Gustafsson
 * @version 1.0
 *
 */
public interface IPreDamageStatusEffect extends IStatusEffect, Comparable<IPreDamageStatusEffect> {
	/**
	 * Calculates the damage a character can take after the status effect will effect it.
	 * @param damage - the damage taken
	 * @return float - the resulting damage
	 */
	public float calculateDamage(float damage);
	/**
	 * Return the priority of the status effect
	 * @return int - the priority of the status effect
	 */
	public int getPriority();
}


package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.world.ICharacter;
/**
 * A class that describe a status effect that shield the character against damage
 * @author David Gustafsson
 *
 */
public class ShieldStatusEffect implements IPreDamageStatusEffect {
	private float shieldAmount;
	private int duration;
	private int priority;

	/**
	 * Creates a new ShieldStatusEffect
	 * @param duration
	 * @param priority
	 * @param shieldAmount
	 */
	public ShieldStatusEffect(int duration, int priority, float shieldAmount) {
	this.duration = duration;
	this.priority = priority;
	this.shieldAmount = shieldAmount;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void update(ICharacter c, float delta) {
		duration -= delta;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isActive() {
		return shieldAmount > 0 || duration > 0;
	}
	

	/**
	 * {@inheritDoc}
	 */
	public float getDuration() {
		return duration;
	}
	

	/**
	 * {@inheritDoc}
	 */
	public int getPriority(){
		return priority;
	}
	

	/**
	 * {@inheritDoc}
	 */
	public float calculateDamage(float damage) {
		float returnDamage = damage - shieldAmount < 0? 0 : damage - shieldAmount; 
		shieldAmount -= damage;
		return returnDamage;
	}
	

	/**
	 * {@inheritDoc}
	 */
	public int compareTo(IPreDamageStatusEffect effect) {
		return priority - effect.getPriority() ;
	}
	

	/**
	 * {@inheritDoc}
	 */
	public IStatusEffect copy() {
		return new ShieldStatusEffect(this.duration , this.priority , this.shieldAmount);
	}

	
}

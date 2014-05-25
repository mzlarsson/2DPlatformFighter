package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.world.ICharacter;
/**
 * A class that describe a status effect that shield the character against damage
 * @author David Gustafsson
 * @version 1.0
 *
 */
public class ShieldStatusEffect implements IPreDamageStatusEffect {
	private float shieldAmount;
	private int duration;
	private int priority;

	/**
	 * Creates a new ShieldStatusEffect with a duration, priority and shieldAmount.
	 * Duration is the time the effect is active
	 * The priority is a number that decides when the effect shall be called
	 * a low number in priority will make the effect be called first.
	 * The shieldAmount is the amount of damage the effect will absorbed before becoming
	 * inactive.
	 * @param duration - the duration of the effect
	 * @param priority - the priority of the effect
	 * @param shieldAmount - the amount of damage the effect will absorbed.
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
		return shieldAmount > 0 && duration > 0;
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
		float returningDamage = shieldAmount - damage >= 0? 0 : damage - shieldAmount; 
		shieldAmount = shieldAmount - damage <= 0? 0 : shieldAmount - damage;
		return returningDamage;
	}
	

	/**
	 * {@inheritDoc}
	 */
	public int compareTo(IPreDamageStatusEffect effect) {
		return priority - effect.getPriority();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public IStatusEffect copy() {
		return new ShieldStatusEffect(this.duration , this.priority , this.shieldAmount);
	}

	
}

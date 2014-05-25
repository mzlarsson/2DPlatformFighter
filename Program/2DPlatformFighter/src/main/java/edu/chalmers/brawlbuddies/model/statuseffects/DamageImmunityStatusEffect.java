package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.world.ICharacter;

/**
 * A class that describes a  status effect 
 * that will make the player the immune against damage.
 * @author David Gustafsson
 * @version 1.0
 */

public class DamageImmunityStatusEffect implements IPreDamageStatusEffect {
	private int duration;
	private int priority;
	/**
	 * Creates a new DamageImmunityStatusEffect with a duration and priority.
	 * The duration is the time the effect will last
	 * The priority is a number that decides when the effect shall be called
	 * a low number in priority will make the effect be called first.
	 * @param duration - the duration of the effect
	 * @param priority - the priority of the effect
	 */
	public DamageImmunityStatusEffect(int duration, int priority){
		this.duration = duration;
		this.priority = priority;
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
		return duration <= 0;
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
		return 0;
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
		return new DamageImmunityStatusEffect(this.duration, this.priority);
	}

}

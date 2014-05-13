package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.world.ICharacter;
/**
 * A class that describe a status effect that will damage a character over time.
 * @author David Gustafsson
 *
 */
public class DamageStatusEffect implements IStatusEffect {
	private int duration;
	private int intervall;
	private float damage;
	private int prevIndex;
	/**
	 * Creates a new DamageStatusEffect with duration , intervall and damage.
	 * The duration decides how long the status effect is active. 
	 * The intervall decides how long the time between the damage ticks is.
	 * The damage decides how much damage the effect will transfer with each tick of damage.
	 * @param duration- the duration of the status effect
	 * @param intervall - the time between damage ticks
	 * @param damage - the damage every damage ticks
	 * @throws IllegalArgumentException if intervall <= 0
	 */
	public DamageStatusEffect(int duration, int intervall, float damage) {
		if(intervall <= 0){
			throw new IllegalArgumentException();
		}
		this.duration = duration;
		this.intervall = intervall;
		this.damage = damage;
		this.prevIndex = duration / intervall;
		
	}
	/**
	 * {@inheritDoc}
	 */
	public boolean isActive() {
		return duration > 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void update(ICharacter c, float delta) {
		duration -= delta;
		while(prevIndex != duration/intervall && prevIndex != 0){
		c.takeDamage(damage);
		prevIndex -= 1;
		}
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
	public IStatusEffect copy() {
		return new DamageStatusEffect(this.duration, this.intervall, this.damage);
	}
}

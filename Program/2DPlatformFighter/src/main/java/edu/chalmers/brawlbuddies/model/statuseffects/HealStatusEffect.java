package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.world.ICharacter;
/**
 * A status effect that heals the character over a amount of time.
 * @author David Gustafsson
 * @version 1.0
 *
 */
public class HealStatusEffect implements IStatusEffect {
	private int duration;
	private int intervall;
	private float healAmount;
	private int prevTick;
	/**
	 * Creates a new heal status effect with a duration, intervall and heal.
	 * The duration is the time the effect will be active
	 * The intervall is the time between ticks of healing 
	 * The heal is the amount of healing the effect preform during each tick.
	 * @param duration - the duration of the effect
	 * @param intervall - the time between healing ticks
	 * @param heal - the amount of healing each healing tick
	 * @throws IllegalArgumentException if intervall <= 0
	 */
	public HealStatusEffect(int duration, int intervall, float heal) {
		if(intervall <= 0){
			throw new IllegalArgumentException();
		}
		this.duration = duration;
		this.intervall = intervall;
		this.healAmount = heal;
		this.prevTick = duration;
		
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
		while(prevTick - duration>= intervall && prevTick > 0){
		c.heal(healAmount);
		prevTick -= intervall;
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
		return new HealStatusEffect(this.duration, this.intervall, this.healAmount);
	}
}

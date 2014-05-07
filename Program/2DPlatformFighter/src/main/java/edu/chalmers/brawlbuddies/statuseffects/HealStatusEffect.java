package edu.chalmers.brawlbuddies.statuseffects;

import edu.chalmers.brawlbuddies.model.Skills.ICharacter;

public class HealStatusEffect implements IStatusEffect {
	private int duration;
	private int intervall;
	private float healAmount;
	private int prevIndex;
	/**
	 * Creates a new DamageStatusEffect
	 * @param duration
	 * @param intervall
	 * @param heal
	 * @throws IllegalArgumentException if intervall <= 0
	 */
	public HealStatusEffect(int duration, int intervall, float heal) {
		if(intervall <= 0){
			throw new IllegalArgumentException();
		}
		this.duration = duration;
		this.intervall = intervall;
		this.healAmount = heal;
		this.prevIndex = duration / intervall;
		
	}

	public boolean isActive() {
		return duration > 0;
	}

	public void update(ICharacter c, float delta) {
		duration -= delta;
		while(prevIndex != duration/intervall && prevIndex != 0){
		c.heal(healAmount);
		System.out.println("character takes damage");
		prevIndex -= 1;
		}
	}

	public float getDuration() {
		return duration;
	}

	public IStatusEffect copy() {
		return new HealStatusEffect(this.duration, this.intervall, this.healAmount);
	}
}

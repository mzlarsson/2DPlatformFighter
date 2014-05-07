package edu.chalmers.brawlbuddies.statuseffects;

import edu.chalmers.brawlbuddies.model.Skills.ICharacter;

public class DamageStatusEffect implements IStatusEffect {
	private int duration;
	private int intervall;
	private float damage;
	private int prevIndex;
	/**
	 * Creates a new DamageStatusEffect
	 * @param duration
	 * @param intervall
	 * @param damage
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

	public boolean isActive() {
		return duration > 0;
	}

	public void update(ICharacter c, float delta) {
		duration -= delta;
		while(prevIndex != duration/intervall && prevIndex != 0){
		c.takeDamage(damage);
		System.out.println("character takes damage");
		prevIndex -= 1;
		}
	}

	public float getDuration() {
		return duration;
	}

	public IStatusEffect copy() {
		return new DamageStatusEffect(this.duration, this.intervall, this.damage);
	}
}

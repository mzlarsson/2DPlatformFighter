package edu.chalmers.brawlbuddies.statuseffects;

import edu.chalmers.brawlbuddies.model.Skills.ICharacter;

public class ShieldStatusEffect implements IPreDamageStatusEffect {
	private float shieldAmount;
	private int duration;
	private int priority;

	public ShieldStatusEffect(int duration, int priority, float shieldAmount) {
	this.duration = duration;
	this.priority = priority;
	this.shieldAmount = shieldAmount;
	}

	public void update(ICharacter c, float delta) {
		duration -= delta;
	}
	public boolean isActive() {
		return shieldAmount > 0 || duration > 0;
	}

	public float getDuration() {
		return duration;
	}
	public int getPriority(){
		return priority;
	}
	public float calculateDamage(float damage) {
		float returnDamage = damage - shieldAmount < 0? 0 : damage - shieldAmount; 
		shieldAmount -= damage;
		return returnDamage;
	}

	public int compareTo(IPreDamageStatusEffect effect) {
		return priority - effect.getPriority() ;
	}

	public IStatusEffect copy() {
		return new ShieldStatusEffect(this.duration , this.priority , this.shieldAmount);
	}

	
}

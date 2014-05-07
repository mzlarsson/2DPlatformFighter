package edu.chalmers.brawlbuddies.statuseffects;

import edu.chalmers.brawlbuddies.model.world.ICharacter;


public class DamageImmunityStatusEffect implements IPreDamageStatusEffect {
	private int duration;
	private int priority;
	public DamageImmunityStatusEffect(int duration, int priority){
		this.duration = duration;
		this.priority = priority;
	}
	
	public void update(ICharacter c, float delta) {
		duration -= delta;
		
	}

	public boolean isActive() {
		return duration <= 0;
	}

	public float getDuration() {
		return duration;
	}
	public int getPriority(){
		return priority;
	}

	public float calculateDamage(float damage) {
		return 0;
	}

	public int compareTo(IPreDamageStatusEffect o) {
		return o.getPriority() - priority;
	}

	public IStatusEffect copy() {
		return new DamageImmunityStatusEffect(this.duration, this.priority);
	}

}

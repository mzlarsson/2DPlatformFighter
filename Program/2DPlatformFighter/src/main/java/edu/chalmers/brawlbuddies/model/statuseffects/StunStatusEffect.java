package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.statuseffects.StatusEffectList.Actions;
import edu.chalmers.brawlbuddies.model.world.ICharacter;

public class StunStatusEffect implements IPreActStatusEffect {
	int duration;
	
	public StunStatusEffect(int duration){
		this.duration = duration;
	}
	
	public void update(ICharacter c, float delta) {
	duration -= delta;
	}

	public boolean isActive() {
		return duration > 0;
	}

	public float getDuration() {
		return duration;
	}

	public IStatusEffect copy() {
		return new StunStatusEffect(this.duration);
	}

	public boolean canAct(Actions action) {
		return false;
	}

}

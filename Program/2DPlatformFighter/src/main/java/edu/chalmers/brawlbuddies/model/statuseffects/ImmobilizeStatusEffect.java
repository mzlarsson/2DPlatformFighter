package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.statuseffects.StatusEffectList.Action;
import edu.chalmers.brawlbuddies.model.world.ICharacter;

public class ImmobilizeStatusEffect implements IPreActStatusEffect {
	private int duration;
	
	public ImmobilizeStatusEffect(int duration){
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
		return new ImmobilizeStatusEffect(this.duration);
	}

	public boolean canAct(Action action) {
		if(action == Action.JUMP && action == Action.MOVE){
			return false;
		}
		return true;
	}

}

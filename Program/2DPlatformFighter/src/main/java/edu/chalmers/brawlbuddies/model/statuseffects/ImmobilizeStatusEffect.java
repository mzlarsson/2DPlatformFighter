package edu.chalmers.brawlbuddies.model.statuseffects;

import java.awt.Desktop.Action;

import edu.chalmers.brawlbuddies.model.statuseffects.StatusEffectList.Actions;
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

	public boolean canAct(Actions action) {
		if(action == Actions.JUMP && action == Actions.MOVE){
			return false;
		}
		return true;
	}

}

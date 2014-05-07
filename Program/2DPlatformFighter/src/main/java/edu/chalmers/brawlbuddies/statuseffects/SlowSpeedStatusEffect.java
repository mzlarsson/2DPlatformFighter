package edu.chalmers.brawlbuddies.statuseffects;

import edu.chalmers.brawlbuddies.model.Skills.ICharacter;

public class SlowSpeedStatusEffect implements IStatusEffect {
	private int duration;
	private float scaleAmount;
	private boolean active = false;

	public SlowSpeedStatusEffect(int duration, float scaleAmount) {
		this.duration = duration;
		this.scaleAmount = scaleAmount;
	}

	public boolean isActive() {
		return duration > 0;
	}

	public void update(ICharacter c, float delta) {
		duration -= delta;
		if (!active) {
			c.addScale(scaleAmount);
			active = true;
		}
		if (!isActive()) {
			c.removeScale(scaleAmount);
		}
	}

	public float getDuration() {
		return duration;
	}

	public IStatusEffect copy() {
		return new SlowSpeedStatusEffect(this.duration, this.scaleAmount);
	}

}

package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.world.ICharacter;

public class SlowSpeedStatusEffect implements IStatusEffect {
	private int duration;
	private float scaleAmount;
	private boolean active = false;
	private Velocity velocity = null;

	public SlowSpeedStatusEffect(int duration, float scaleAmount, Velocity velocity) {
		this.duration = duration;
		this.scaleAmount = scaleAmount;
		this.velocity = velocity;
	}

	public boolean isActive() {
		return duration > 0;
	}

	public void update(ICharacter c, float delta) {
		duration -= delta;
		if (!active) {
			c.addScale(scaleAmount);
			active = true;
			if(velocity != null){
				c.addSpeed(velocity);
			}
		}
		if (!isActive()) {
			if(velocity != null){
				c.removeSpeed(velocity);
			}
			c.removeScale(scaleAmount);
		}
	}

	public float getDuration() {
		return duration;
	}

	public IStatusEffect copy() {
		return new SlowSpeedStatusEffect(this.duration, this.scaleAmount, this.velocity);
	}

}

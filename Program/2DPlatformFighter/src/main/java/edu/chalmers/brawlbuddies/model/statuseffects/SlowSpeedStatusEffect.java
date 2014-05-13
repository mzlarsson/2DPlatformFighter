package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.world.ICharacter;
/**
 * A status effect that slow or speed up the target during a amount of time. 
 * @author David Gustafsson
 *
 */
public class SlowSpeedStatusEffect implements IStatusEffect {
	private int duration;
	private float scaleAmount;
	private boolean active = false;
	private Velocity velocity = null;
	
	/**
	 * Creates a new SlowSpeedStatusEffect with a duration , scaleAmount and velocity
	 * The duration is the amount of time the effect is active
	 * The scaleAmount is the amount that should be added to the scaling of characters speed
	 * The velocity is the static amount that should be added to the characters speed
	 * @param duration - the duration of the effect
	 * @param scaleAmount - the scaling that is to be added to the characters speed
	 * @param velocity - the speed that is to be added to the characters speed
	 */
	public SlowSpeedStatusEffect(int duration, float scaleAmount, Velocity velocity) {
		this.duration = duration;
		this.scaleAmount = scaleAmount;
		this.velocity = velocity;
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
		return new SlowSpeedStatusEffect(this.duration, this.scaleAmount, this.velocity);
	}

}

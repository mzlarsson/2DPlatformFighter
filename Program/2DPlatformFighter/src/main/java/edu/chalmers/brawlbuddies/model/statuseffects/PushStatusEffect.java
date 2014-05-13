package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.world.ICharacter;
/**
 * Creates a status effect that will push the character during a amount of time without gravity
 * @author David Gustafsson
 *
 */
public class PushStatusEffect implements IStatusEffect {
	private int duration;
	private Velocity velocity;
	private float scaleAmount;
	private boolean active = false;

	/**
	 * Creates a new PushStatusEffect with duration , scaleAmount and velocity. During the
	 * PushStatusEffect gravity will not effect the target.
	 * Duration decide the time the effect is active
	 * scaleAmount decide the magnitude of the push velocity if velocity is not present 
	 * velocity decide the push velcoity.
	 * @param duration- the duration of the effect. 
	 * @param scaleAmount - the magnitude of the push velocity if velocity is not present
	 * @param velocity - the push velocity
	 */
	
	public PushStatusEffect(int duration, float scaleAmount , Velocity velocity ){
		this.duration = duration;
		this.velocity = velocity;
		this.scaleAmount = scaleAmount;
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
	public float getDuration() {
		return duration;
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(ICharacter c, float delta) {
		duration -= delta;
		if(!active){
			if(velocity == null){
			Aim a = c.getAim();
			Velocity v = new Velocity(a.getX(), a.getY());
			v = v.getNormalized().scale(scaleAmount);
			c.push(v);
			}
			else{
				c.push(velocity);
			}
			active = true;
		}
		c.resetGravity();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public IStatusEffect copy() {
		return new PushStatusEffect(this.duration, this.scaleAmount, this.velocity);
	}
}

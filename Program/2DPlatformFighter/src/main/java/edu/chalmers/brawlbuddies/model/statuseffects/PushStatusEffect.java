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
	private Aim aim;
	private double aimOffset;
	private float power;
	private boolean active = false;
	private Velocity activeVelocity;

	/**
	 * Creates a new PushStatusEffect with duration , scaleAmount and velocity. During the
	 * PushStatusEffect gravity will not effect the target.
	 * Duration decide the time the effect is active
	 * scaleAmount decide the magnitude of the push velocity if velocity is not present 
	 * velocity decide the push velcoity.
	 * @param duration - the duration of the effect. 
	 * @param power - the magnitude of the push velocity if velocity is not present
	 * @param aim - the aim in which to push.
	 * @param aimOffset - the offset in degrees upwards.
	 */
	
	public PushStatusEffect(int duration, float power, Aim aim, double aimOffset ){
		this.duration = duration;
		this.aim = aim;
		this.aimOffset = aimOffset;
		this.power = power;
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
	 * Get the resulting aim from a character and the aimOffset
	 * @param ch - the Character the aim will be taken from
	 * @return the resulting aim
	 */
	private Aim getAim(ICharacter ch) {
		if (aim!=null) {
			Aim a = aim.copy();
			a.setTheta(a.getTheta()+ (ch.getAim().getX()<0 ? aimOffset : -aimOffset));
			return a;
		} else {
			Aim a = ch.getAim().copy();
			a.setTheta(a.getTheta()+ (a.getX()<0 ? aimOffset : -aimOffset));
			return a;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void update(ICharacter c, float delta) {
		if (isActive()) {
			duration -= delta;
			if(!active){
				Aim a = getAim(c);
				activeVelocity = new Velocity(a.getX(), a.getY());
				activeVelocity = activeVelocity.getNormalized().scale(power);
				c.addSpeed(activeVelocity);
				active = true;
			}
			c.resetGravity();
			
			if (duration<=0){
				c.removeSpeed(activeVelocity);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public IStatusEffect copy() {
		return new PushStatusEffect(this.duration, this.power, this.aim, this.aimOffset);
	}
}

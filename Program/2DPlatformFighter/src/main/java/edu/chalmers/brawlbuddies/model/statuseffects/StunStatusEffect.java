package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.statuseffects.StatusEffectList.Action;
import edu.chalmers.brawlbuddies.model.world.ICharacter;
/**
 * A class to describe a status effect that stun the character for a amount of time
 * @author David Gustafsson
 * @version 1.0
 *
 */
public class StunStatusEffect implements IPreActStatusEffect {
	int duration;
	
	/**
	 * Creates a new StunStatusEffect with a duration
	 * Duration is the time the status effect is active.
	 * @param duration - the duration of the effect
	 */
	public StunStatusEffect(int duration){
		this.duration = duration;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void update(ICharacter c, float delta) {
	duration -= delta;
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
	public IStatusEffect copy() {
		return new StunStatusEffect(this.duration);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean canAct(Action action) {
		return false;
	}

}

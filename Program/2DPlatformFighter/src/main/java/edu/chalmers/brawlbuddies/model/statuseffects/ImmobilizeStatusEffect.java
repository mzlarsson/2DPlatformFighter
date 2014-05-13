package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.statuseffects.StatusEffectList.Action;
import edu.chalmers.brawlbuddies.model.world.ICharacter;

/**
 * A status effect that will make the character unable to move during a duration of time
 * @author David Gustafsson
 *
 */
public class ImmobilizeStatusEffect implements IPreActStatusEffect {
	private int duration;
	
	/**
	 * Creates a new ImmobilizeStatusEffect with a duration.
	 * Duration the time the effect is active.
	 * @param duration
	 */
	public ImmobilizeStatusEffect(int duration){
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
		return new ImmobilizeStatusEffect(this.duration);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean canAct(Action action) {
		if(action == Action.JUMP || action == Action.MOVE){
			return false;
		}
		return true;
	}

}

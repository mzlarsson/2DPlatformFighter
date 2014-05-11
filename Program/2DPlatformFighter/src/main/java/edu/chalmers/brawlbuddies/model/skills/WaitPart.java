package edu.chalmers.brawlbuddies.model.skills;

import edu.chalmers.brawlbuddies.model.world.ICharacter;

/**
 * A SkillPart for delaying the casting of skills.
 * @author Patrik Haar
 * @version 1.0
 *
 */
public class WaitPart implements SkillPart {

	private int delayTotal;
	private int delayLeft;
	private boolean delayDone;
	
	/**
	 * Creates a new WaitPart with a amount of delay
	 * @param delay- the amount of delay
	 */
	public WaitPart(int delay) {
		this.delayTotal = delay;
		this.delayLeft = delay;
	}
	/**
	 * {@inheritDoc}
	 */
	public boolean activate(ICharacter c) {
		if (delayDone) {
			delayDone = false;
			delayLeft = delayTotal;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public int update(int delta) {
		delayLeft -= delta;
		if (delayLeft<=0) {
			delayDone = true;
			return -delayLeft;
		} else {
			return delta;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	public void setCreatorID(int id) {
	}

}

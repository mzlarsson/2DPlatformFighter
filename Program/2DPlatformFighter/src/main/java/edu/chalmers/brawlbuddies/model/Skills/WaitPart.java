package edu.chalmers.brawlbuddies.model.Skills;

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
	
	public WaitPart(int delay) {
		this.delayTotal = delay;
		this.delayLeft = delay;
	}
	
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
	
	public void setCreatorID(int id) {
	}

}

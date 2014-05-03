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
	
	public WaitPart(int delay) {
		this.delayTotal = delay;
		this.delayLeft = delay;
	}
	
	public boolean activate(ICharacter c, int delta) {
		delayLeft -= delta;
		if (delayLeft<=0) {
			delayLeft = delayTotal;
			return true;
		}
		return false;
	}

	public void setCreatorID(int id) {
	}

}

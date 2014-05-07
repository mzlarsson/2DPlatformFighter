package edu.chalmers.brawlbuddies.model.Skills;

import java.util.ArrayList;
import java.util.List;

import edu.chalmers.brawlbuddies.model.world.ICharacter;

public class Skill implements ISkill{
	
	private int typeID;
	private int cooldown;
	private int cooldownLeft = 0;
	private int ownerID;
	
	private List<SkillPart> skillParts = new ArrayList<SkillPart>();
	
	private boolean skillActive;
	private int currentSkillpart;
	private ICharacter currentCaster;
	
	public Skill(int cd, int typeID, int ownerID) {
		this.cooldown = cd;
		this.typeID = typeID;
		this.ownerID = ownerID;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void activate(ICharacter ch) {
		if (isReady()) {
			activate(ch, 1);
		}
	}
	
	private void activate(ICharacter ch, int delta) {
		if (!skillActive) {
			skillActive = true;
			currentCaster = ch;
			resetCooldown();
		}
		boolean aborted = false;
		for (int i=currentSkillpart; i<skillParts.size(); i++) {
			delta -= skillParts.get(i).update(delta);
			if (!skillParts.get(i).activate(ch)) {
				currentSkillpart = i;
				delta -= 3;
				aborted = true;
				break;
			}
		}
		if (!aborted) {
			currentSkillpart = 0;
			skillActive = false;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getTypeID() {
		return typeID;
	}
	
	/**
	 * Adds a SkillPart to this Skill.
	 * @param s The SkillPart to be added.
	 */
	public void addSkillPart(SkillPart s) {
		s.setCreatorID(ownerID);
		skillParts.add(s);
	}
	
	/**
	 * Checks if the skill is ready to use.
	 * @return <code>true</code> if skill is ready, <code>false</code> otherwise.
	 */
	public boolean isReady() {
		return cooldownLeft<=0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean update(int delta) {
		if (!skillActive) {
			cooldownLeft -= delta;
			if (isReady()){
				cooldownLeft = 0;
				return true;
			} else {
				return false;
			}
		} else {
			activate(currentCaster, delta);
			return false;
		}
	}
	
	/**
	 * Resets the cooldown to full.
	 */
	public void resetCooldown() {
		cooldownLeft = cooldown;
	}
}

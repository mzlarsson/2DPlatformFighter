package edu.chalmers.brawlbuddies.model.Skills;

import java.util.ArrayList;
import java.util.List;

public class Skill implements ISkill{
	
	private int cooldown;
	private int cooldownLeft = 0;
	private int ownerID;
	
	private List<SkillPart> skillParts = new ArrayList<SkillPart>();
	
	public Skill(int cd, int id) {
		this.cooldown = cd;
		this.ownerID = id;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void activate(ICharacter ch) {
		if (isReady()) {
			for (SkillPart s : skillParts) {
				s.activate(ch);
			}
			resetCooldown();
		}
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
	public boolean decreaseCooldown(int delta) {
		cooldownLeft -= delta;
		if (isReady()){
			cooldownLeft = 0;
			return true;
		} else {
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

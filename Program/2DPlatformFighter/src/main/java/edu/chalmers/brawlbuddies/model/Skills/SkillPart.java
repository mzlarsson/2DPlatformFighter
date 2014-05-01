package edu.chalmers.brawlbuddies.model.Skills;
/**
 * A interface describing a part of a skill.
 * @author David Gustafsson
 * @revised Patrik Haar
 * @version 0.1
 */
public interface SkillPart {
	/**
	 * Activate the SkillPart with the Casting Character as a argument
	 * @param Character
	 */
	public void activate(ICharacter c);
	public void setCreatorID(int id);
}

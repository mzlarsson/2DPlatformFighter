package edu.chalmers.brawlbuddies.model.Skills;

/**
 * An interface for describing a Character Skill.
 * @author Patrik Haar
 * @version 0.1
 */
public interface ISkill {
	/**
	 * Activate the skill with the Casting Character as a argument
	 * @param Character
	 */
	public void activate(ICharacter c);
	
	/**
	 * Updates the Skill.
	 * @param delta The time to deduct in milliseconds.
	 * @return <code>true</code> if skill is ready, <code>false</code> otherwise.
	 */
	public boolean update(int delta);
	
	/**
	 * Returns the type ID of the Skill.
	 * @return The Skills type ID.
	 */
	public int getTypeID();
}

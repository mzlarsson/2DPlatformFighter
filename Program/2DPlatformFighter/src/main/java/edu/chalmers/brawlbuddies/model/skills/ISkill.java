package edu.chalmers.brawlbuddies.model.skills;

import edu.chalmers.brawlbuddies.model.world.ICharacter;

/**
 * An interface for describing a Character Skill.
 * @author Patrik Haar
 * @version 1.0
 */
public interface ISkill {
	/**
	 * Activate the skill with the Casting Character as a argument
	 * @param Character
	 * @return true if the skill was successfully activated.
	 */
	public boolean activate(ICharacter c);
	
	/**
	 * Updates the Skill.
	 * @param delta The time to deduct in milliseconds.
	 * @return <code>true</code> if skill is ready, <code>false</code> otherwise.
	 */
	public boolean update(int delta);
	
	/**
	 * Returns the unique ID of the skill
	 * @return The unique ID of the skill
	 */
	public int getID();
	
	/**
	 * Returns the type ID of the Skill.
	 * @return The Skills type ID.
	 */
	public int getTypeID();
	
	/**
	 * Returns the name of the animation this skill prefers to use.
	 * @return The animation name of this skill.
	 */
	public String getAnimationName();
	
	/**
	 * Returns the id of the character that owns the skill
	 * @return The ID of the character that owns the skill
	 */
	public int getOwnerID();
	/**
	 * Returns the cooldown of the skill
	 * @return the cooldown of the skill in milliseconds
	 */
	public int getCooldown();
}

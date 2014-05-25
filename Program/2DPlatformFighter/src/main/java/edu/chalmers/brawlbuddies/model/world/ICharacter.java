package edu.chalmers.brawlbuddies.model.world;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.GameListener;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.skills.DamageAble;
import edu.chalmers.brawlbuddies.model.skills.HealAble;
import edu.chalmers.brawlbuddies.model.skills.PushAble;
import edu.chalmers.brawlbuddies.model.statuseffects.IStatusEffect;

/**
 * A interface to describe a Character
 * @author David Gustafsson
 * @revised Matz Larsson
 *
 */
public interface ICharacter extends IGameObject, HealAble, DamageAble, PushAble{
	/**
	 * Get the Aim of the Character
	 * @return Aim - the aim of the character
	 */
	public Aim getAim();
	/**
	 * Get the position where the character fire projectile from
	 * @return Position - the character projectile fire position
	 */
	public Position getProjFirePos();
	/**
	 * Move the character in a direction
	 * @param dir - the direction of the character movement
	 */
	public void move(Direction dir);
	/**
	 * Makes the character jump
	 */
	public void makeJump();
	/**
	 * Set the aim of the character with a aim position and a boolean describing if the position
	 * is relative to character
	 * @param aimPosition - the aim position of the character
	 * @param isRelative - boolean describing if the position is relative to the character
	 */
	public void setAim(Position aimPosition, boolean isRelative);
	
	/**
	 * Activates a character skill with a skill index
	 * @param skillIndex - the index of the skill
	 */
	public void activateSkill(int skillIndex);
	
	/**
	 * Reset the Character 
	 */
	public void reset();
		
	/**
	 * Get the direction of the Character
	 * @return Direction - the direction of the character
	 */
	public Direction getDirection();
	/**
	 * Return if the character is airborne
	 * @return boolean -  true if the character is airborne
	 */
	public boolean isInAir();
	
	/**
	 * Apply a status effect to the character
	 * @param copy
	 */
	public void applyStatusEffect(IStatusEffect copy);
	
	/**
	 * Increse the value to the scale of the character base speed with a float
	 * @param scale - the value to added to the scale of the character base speed
	 */
	public void addScale(float scale);
	/**
	 * Decrese the value of the scale of the character base speed with a float
	 * @param scale - the value to be removed from the scale of the character base speed
	 */
	public void removeScale(float scale);
	/**
	 * Restore the scale of the characters base speed
	 */
	public void restoreScale();
	/**
	 * Reset the gravity of the character
	 */
	public void resetGravity();
	/**
	 * Add a speed to the character
	 * @param velocity - the velocity to be added
	 */
	public void addSpeed(Velocity velocity);
	/**
	 * Remove a speed to the character
	 * @param velocity - the velocity to be removed
	 */
	public void removeSpeed(Velocity velocity);
	
	/**
	 * Adds the given GameListener as a listener to catch game events.
	 * @param gl The GameListener to handle the events this class throws.
	 */
	public void addGameListener(GameListener gl);
	
	/**
	 * Removes the given GameListener as a listener to this class.
	 * @param gl The GameListener to be removed.
	 */
	public void removeGameListener(GameListener gl);

}

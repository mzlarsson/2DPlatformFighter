package edu.chalmers.brawlbuddies.model.Skills;
import edu.chalmers.brawlbuddies.model.world.Character;
/**
 * A interface describing a Character skills
 * @author David Gustafsson
 *
 */
public interface Skill {
/**
 * Activate the skill with the Casting Character as a argument
 * @param Character
 */
public void activate(CharacterInterface c);
}

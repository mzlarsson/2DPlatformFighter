package edu.chalmers.brawlbuddies.util;

import edu.chalmers.brawlbuddies.model.Skills.Effect;
import edu.chalmers.brawlbuddies.model.world.GameObject;
import edu.chalmers.brawlbuddies.model.world.Projectile;
import edu.chalmers.brawlbuddies.model.world.Character;

public interface CharacterActionListener {
/**
 * Inform the listener that a projectile has been created
 * @param p
 */
public void characterActionPerformed(Projectile p);
/**
 * Inform the listener that a gameobject has been created
 * @param o
 */
public void characterActionPerformed(GameObject o);
/**
 * Inform the listener that a Effect should be placed on all Characters except one 
 * @param c
 * @param e
 */
//TODO Change Character c to CharacterInterface when it is possible
public void characterActionPerformed(Character c , Effect e);
/**
 * Inform the listener that a Effect should be placed on all Characters except one
 * @param e
 */
public void characterActionPerformed(Effect e);
}

package edu.chalmers.brawlbuddies.model.Skills;

import edu.chalmers.brawlbuddies.model.world.*;
import edu.chalmers.brawlbuddies.model.world.Character;

/**
 * Describes a damage effect
 * 
 * @author David Gustafsson
 * 
 */
public class DamageEffect implements Effect {
	private int creatorId;
	private float damage;

	/**
	 * Creates a Effect with a playerId describing the playerSkills or effect
	 * that belongs to the effect and damage describing the amount of damage a
	 * object takes when exposed to the effect.
	 * 
	 * @param creatorId
	 * @param damage
	 */
	public DamageEffect(float damage) {

		this.damage = damage;
	}
	public void setCreatorId(int creatorId){
		this.creatorId = creatorId;
		System.out.println("creatorId set for effect the id is " +  creatorId);
	}

	public void effect(GameObject o) {
		if (o instanceof CharacterInterface) {
			// The playerId is used so that a player can't damage
			// himself/herself
			if (!(((CharacterInterface) o).getId() == creatorId)) {
				((CharacterInterface) o).takeDamage(damage);
			}
		} else if (o instanceof DamageAble) {
			((DamageAble) o).takeDamage(damage);
		}

	}
}

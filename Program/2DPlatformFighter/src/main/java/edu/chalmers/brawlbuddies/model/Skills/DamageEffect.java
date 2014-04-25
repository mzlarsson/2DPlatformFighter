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
	private int playerId;
	private float damage;

	/**
	 * Creates a Effect with a playerId describing the playerSkills or effect
	 * that belongs to the effect and damage describing the amount of damage a
	 * object takes when exposed to the effect.
	 * 
	 * @param playerId
	 * @param damage
	 */
	public DamageEffect(float damage) {

		this.damage = damage;
	}
	public void setplayerId(int playerId){
		this.playerId = playerId;
	}

	public void effect(GameObject o) {
		if (o instanceof CharacterInterface) {
			// The playerId is used so that a player can't damage
			// himself/herself
			if (!(((CharacterInterface) o).getPlayerId() == playerId)) {
				((CharacterInterface) o).takeDamage(damage);
			}
		} else if (o instanceof DamageAble) {
			((DamageAble) o).takeDamage(damage);
		}

	}
}

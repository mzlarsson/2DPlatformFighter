package edu.chalmers.brawlbuddies.model.Skills;

import edu.chalmers.brawlbuddies.model.world.*;

/**
 * Describes a damage effect
 * 
 * @author David Gustafsson
 * @revised Matz Larsson
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
	public void setCreatorID(int creatorId){
		this.creatorId = creatorId;
	}

	public boolean effect(IGameObject sender, IGameObject reciever) {
		if (reciever instanceof DamageAble) {
			if (reciever.getID() != creatorId) {
				((DamageAble)reciever).takeDamage(damage);
				return true;
			}
		}
		return false;
	}
}

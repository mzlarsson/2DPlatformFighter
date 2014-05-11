package edu.chalmers.brawlbuddies.model.skills;

import edu.chalmers.brawlbuddies.model.world.*;

/**
 * A class describing a damage effect
 * @author David Gustafsson
 * @revised Matz Larsson
 * 
 */
public class DamageEffect implements IEffect {
	private int creatorID;
	private float damage;

	/**
	 * Creates a new DamageEffect that will damage a object when exposed to the effect.
	 * 
	 * A DamageEffect will not effect a target with the same ID as its creatorID 
	 * or a target that is'nt a instance of DamageAble.
	 * @param damage- the amount of damage that exposed objects will take
	 */
	public DamageEffect(float damage) {
		this.damage = damage;
	}
	/**
	 * {@inheritDoc}
	 */
	public void setCreatorID(int creatorID){
		this.creatorID = creatorID;
	}
	/**
	 * {@inheritDoc}
	 */
	public boolean effect(IGameObject sender, IGameObject reciever) {
		if (reciever instanceof DamageAble) {
			if (reciever.getID() != creatorID) {
				((DamageAble)reciever).takeDamage(damage);
				return true;
			}
		}
		return false;
	}
}

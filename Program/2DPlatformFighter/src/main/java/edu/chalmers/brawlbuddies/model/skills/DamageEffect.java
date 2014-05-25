package edu.chalmers.brawlbuddies.model.skills;

import edu.chalmers.brawlbuddies.model.world.*;

/**
 * A class describing a damage effect
 * @author David Gustafsson
 * @revised Matz Larsson
 * @version 1.0
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
	 * @throws IllegalArgumentException if damage is below zero
	 */
	public DamageEffect(float damage) {
		if( damage < 0){
			throw new IllegalArgumentException("damage can't be below zero");
		}
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
	public String toString(){
		return "DamageEffect:" + " damage = " + damage + " creatorID = " + creatorID;  
	}
}

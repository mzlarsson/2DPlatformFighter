package edu.chalmers.brawlbuddies.model.skills;

import edu.chalmers.brawlbuddies.model.world.IGameObject;

/**
 * A class describing a healing effect 
 * @author David Gustafsson
 * @revised Matz Larsson
 * @version 1.0
 *
 */
public class HealEffect implements IEffect {
	private int creatorID;
	private float healAmount;
	
	/**
	 * Creates a new Heal Effect that will heal a object when exposed to the effect.
	 * 
	 * A HealEffect will not heal a object that is'nt a instance of HealAble
	 * @param healAmount- the amount the Effect will heal
	 * @throws IllegalArgumentException if healAmount is below zero
	 */
	public HealEffect(float healAmount) {
		if(healAmount < 0){
			throw new IllegalArgumentException("heal cant be below zero");
		}
		this.healAmount = healAmount;
	}
	/**
	 * {@inheritDoc}
	 */
	public boolean effect(IGameObject sender, IGameObject reciever) {
		if(sender == null){
			if(reciever instanceof HealAble){
				((HealAble) reciever).heal(healAmount);
				return true;
				} else {
					return false;
			}
		}
		else{
			if( reciever instanceof HealAble){
				if(reciever.getID() != creatorID){
				((HealAble) reciever).heal(healAmount);
				}
			}
			return false;
		}
		
	}
	/**
	 * {@inheritDoc}
	 */
	public void setCreatorID(int id) {
		this.creatorID = id;
	}
	public String toString(){
		return "HealEffect" + " heal = " + healAmount;
	}

}

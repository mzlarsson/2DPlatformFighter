package edu.chalmers.brawlbuddies.model.Skills;

import edu.chalmers.brawlbuddies.model.world.IGameObject;

/**
 * A class describing a healing effect 
 * @author David Gustafsson
 * @revised Matz Larsson
 *
 */

public class HealEffect implements Effect {
	private int creatorID;
	private float healAmount;
	
	public HealEffect(float a) {
		this.healAmount = a;
	}
	
	public boolean effect(IGameObject sender, IGameObject reciever) {
		if(reciever instanceof HealAble){
			((HealAble) reciever).heal(healAmount);
			return true;
		} else {
			return false;
		}
	}

	public void setCreatorID(int id) {
		this.creatorID = id;
	}

}

package edu.chalmers.brawlbuddies.model.Skills;

import edu.chalmers.brawlbuddies.model.world.GameObject;
/**
 * A class describing a healing effect 
 * @author David Gustafsson
 *
 */
public class HealEffect implements Effect {
	private float healAmount;
	public HealEffect(float a) {
	this.healAmount = a;
	}
	
	public boolean effect(GameObject o) {
		if(o instanceof HealAble){
			((HealAble) o).heal(healAmount);
			return true;
		} else {
			return false;
		}
	}

}

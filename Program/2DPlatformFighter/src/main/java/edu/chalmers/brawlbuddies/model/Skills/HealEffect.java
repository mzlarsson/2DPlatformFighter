package edu.chalmers.brawlbuddies.model.Skills;

import edu.chalmers.brawlbuddies.model.world.Character;
import edu.chalmers.brawlbuddies.model.world.GameObject;
/**
 * A class describing a healing effect 
 * @author David Gustafsson
 *
 */
public class HealEffect implements Effect {
	private float healAmount;
	protected HealEffect(float a) {
	this.healAmount = a;
	}
	
	public void effect(GameObject o) {
		if(o instanceof HealAble){
		((HealAble) o).heal(healAmount);
		}
	}

}

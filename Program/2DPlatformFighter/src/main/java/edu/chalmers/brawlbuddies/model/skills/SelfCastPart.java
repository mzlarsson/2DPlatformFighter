package edu.chalmers.brawlbuddies.model.skills;

import java.util.List;

import edu.chalmers.brawlbuddies.model.world.GameObject;
import edu.chalmers.brawlbuddies.model.world.ICharacter;

/**
 * Describes a self casting SkillPart.
 * @author David Gustafsson
 * @revised Patrik Haar
 * @version 1.0
 *
 */
public class SelfCastPart implements SkillPart {
	private List<IEffect> effects;
	
	/**
	 * Creates a new SelfCastPart with a list of given Effects
	 * @param effects- a list of Effects
	 */
	public SelfCastPart(List<IEffect> effects) {
		this.effects = effects;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean activate(ICharacter c) {
		if (c instanceof GameObject) {
			for (int i = 0; i < effects.size(); i++) {
				effects.get(i).effect(null ,((GameObject) c));
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public int update(int delta) {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setCreatorID(int id) {
	
	}
	
	public String toString(){
		return "Selfcast"; 
	}

}

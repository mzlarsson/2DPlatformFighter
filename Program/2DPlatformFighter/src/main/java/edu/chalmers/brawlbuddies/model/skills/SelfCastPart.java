package edu.chalmers.brawlbuddies.model.skills;

import java.util.List;

import edu.chalmers.brawlbuddies.model.world.GameObject;
import edu.chalmers.brawlbuddies.model.world.ICharacter;

/**
 * Describes a self casting SkillPart.
 * @author David Gustafsson
 * @revised Patrik Haar
 * @version 0.1
 *
 */
public class SelfCastPart implements SkillPart {
	private List<IEffect> effects;
	
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

	public void setCreatorID(int id) {
		// TODO Auto-generated method stub
		
	}

}

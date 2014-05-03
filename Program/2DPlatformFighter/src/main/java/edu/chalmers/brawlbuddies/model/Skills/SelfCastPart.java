package edu.chalmers.brawlbuddies.model.Skills;

import java.util.List;

import edu.chalmers.brawlbuddies.model.world.GameObject;

/**
 * Describes a self casting SkillPart.
 * @author David Gustafsson
 * @revised Patrik Haar
 * @version 0.1
 *
 */
public class SelfCastPart implements SkillPart {
	private List<Effect> effects;
	
	public SelfCastPart(List<Effect> effects) {
		this.effects = effects;
	}
	
	public boolean activate(ICharacter c, int delta) {
		if (c instanceof GameObject) {
			for (int i = 0; i < effects.size(); i++) {
				effects.get(i).effect(null ,((GameObject) c));
			}
		}
		return true;
	}

	public void setCreatorID(int id) {
		// TODO Auto-generated method stub
		
	}

}

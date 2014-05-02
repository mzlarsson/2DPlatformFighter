package edu.chalmers.brawlbuddies.model.Skills;

import java.util.List;

import edu.chalmers.brawlbuddies.model.world.GameObject;

public class SelfCastSkill implements SkillPart {
	private List<Effect> effects;
	
	public SelfCastSkill(List<Effect> effects) {
		this.effects = effects;
	}
	
	public void activate(ICharacter c) {
		if (c instanceof GameObject) {
			for (int i = 0; i < effects.size(); i++) {
				effects.get(i).effect(null ,((GameObject) c));
			}
		}
	}

	public void setCreatorID(int id) {
		// TODO Auto-generated method stub
		
	}

}

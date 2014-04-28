package edu.chalmers.brawlbuddies.model.Skills;

import java.util.List;

import edu.chalmers.brawlbuddies.model.world.GameObject;

public class SelfCastSkill implements Skill {
	private float cooldownCount = 0;
	private float cooldownTime;
	private List<Effect> effects;
	
	public SelfCastSkill(float cooldownTime, List<Effect> effects) {
		this.effects = effects;
		this.cooldownTime = cooldownTime;
	}

	public void activate(CharacterInterface c) {
		if (c instanceof GameObject) {
			for (int i = 0; i < effects.size(); i++) {
				effects.get(i).effect(((GameObject) c));
			}
		}
	}

}

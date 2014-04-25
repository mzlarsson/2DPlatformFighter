package edu.chalmers.brawlbuddies.model.Skills;

import edu.chalmers.brawlbuddies.model.world.GameObject;

public class SelfCastSkill implements Skill {
	private float cooldownCount = 0;
	private float cooldownTime;
	private Effect effect;
	
	public SelfCastSkill(float cooldownTime, Effect effect){
		this.effect = effect;
		this.cooldownTime = cooldownTime;
	}
	
	public void activate(CharacterInterface c) {
		if( c instanceof GameObject){
		effect.effect(((GameObject) c));
		}
	}

}

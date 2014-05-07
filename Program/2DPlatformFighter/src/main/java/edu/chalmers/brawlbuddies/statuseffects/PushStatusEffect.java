package edu.chalmers.brawlbuddies.statuseffects;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.Skills.ICharacter;

public class PushStatusEffect implements IStatusEffect {
	private int duration;
	private Velocity velocity;
	private float scaleAmount;
	private boolean active = false;
	
	public PushStatusEffect(int duration, float scaleAmount , Velocity velocitiy ){
		this.duration = duration;
		this.velocity = velocitiy;
		this.scaleAmount = scaleAmount;
	}
	public boolean isActive() {
		return duration > 0;
	}
	public float getDuration() {
		return duration;
	}
	public void update(ICharacter c, float delta) {
		duration -= delta;
		if(!active){
			if(velocity == null){
			Aim a = c.getAim();
			Velocity v = new Velocity(a.getX(), a.getY());
			v = v.getNormalized().scale(scaleAmount);
			c.push(v);
			}
			else{
				c.push(velocity);
			}
			active = true;
		}
		c.resetGravity();
	}
	public IStatusEffect copy() {
		return new PushStatusEffect(this.duration, this.scaleAmount, this.velocity);
	}
}

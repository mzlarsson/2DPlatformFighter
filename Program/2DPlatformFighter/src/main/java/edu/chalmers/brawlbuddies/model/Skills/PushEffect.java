package edu.chalmers.brawlbuddies.model.Skills;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.world.GameObject;

public class PushEffect implements Effect {
	private Velocity velocity = null;
	private int creatorId;
	private float pushOffset;
	private float power;

	public PushEffect(float power, Velocity v, float pushOffset) {
		this.velocity = v;
		this.power = power;
		this.pushOffset = pushOffset;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public boolean effect(GameObject sender, GameObject reciever) {
		if (reciever instanceof PushAble) {
			if (sender == null) {
				if (reciever instanceof ICharacter) {
					if (velocity == null) {
						Aim a = ((ICharacter)reciever).getAim().getNormalized();
						Velocity v = new Velocity(a.getX(), a.y);
						v = v.scale(power);
						v.setTheta(v.getTheta() + (v.getX()<0 ? pushOffset : -pushOffset));
						((PushAble) reciever).push(v);						
					} else {
						((PushAble) reciever).push(velocity.getNormalized().scale(power));
					}
				}
			} else {
				if(!(reciever instanceof ICharacter && ((ICharacter) reciever).getID() == creatorId)){ //TODO When ID is implemented fix this
					if (velocity == null) {
						Velocity v = sender.getMovement().getTotalVelocity().getNormalized();
						v = v.scale(power);
						v.setTheta(v.getTheta() + (v.getX()<0 ? pushOffset: -pushOffset));
						((PushAble) reciever).push(v);
					} else {
						((PushAble) reciever).push(velocity.getNormalized().scale(power));
					}
				} else {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}

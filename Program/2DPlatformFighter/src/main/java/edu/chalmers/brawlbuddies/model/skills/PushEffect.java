package edu.chalmers.brawlbuddies.model.skills;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.world.ICharacter;
import edu.chalmers.brawlbuddies.model.world.IGameObject;

/**
 * A class describing a push effect 
 * @author David Gustafsson
 * @revised Matz Larsson
 *
 */

public class PushEffect implements IEffect {
	private Velocity velocity = null;
	private int creatorId;
	private float pushOffset;
	private float power;

	public PushEffect(float power, Velocity v, float pushOffset) {
		this.velocity = v;
		this.power = power;
		this.pushOffset = pushOffset;
	}

	public void setCreatorID(int creatorID) {
		this.creatorId = creatorID;
	}

	public boolean effect(IGameObject sender, IGameObject reciever) {
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
				if(!(reciever instanceof ICharacter && ((ICharacter)reciever).getID() == creatorId)){
					if (velocity == null) {
						Velocity v = sender.getTotalVelocity().getNormalized();
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

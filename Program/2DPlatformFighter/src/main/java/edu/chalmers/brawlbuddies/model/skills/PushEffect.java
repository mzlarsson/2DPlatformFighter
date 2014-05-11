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
	private float aimOffset;
	private float power;
	
	/**
	 * Creates a new Push Effect that will push a object when exposed to the effect. 
	 * 
	 * If velocity is null the exposed object will be pushed with a velocity of the scale of power 
	 * and the direction of the velocity from sending object with the offset of aimOffset. 
	 * 
	 * If velocity is'nt null the exposed object will be pushed with a velocity of the scale of power
	 * and the direction of velocity
	 * 
	 * A Push Effect will not effect a target with the same ID as its creatorID 
	 * or a target that is'nt a instance of PushAble.
	 * @param power - the magnitude of the push
	 * @param v - the velocity of the push
	 * @param aimOffset - the aim offset that will be added if no velocity is set.
	 */
	public PushEffect(float power, Velocity v, float aimOffset) {
		this.velocity = v;
		this.power = power;
		this.aimOffset = aimOffset;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setCreatorID(int creatorID) {
		this.creatorId = creatorID;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean effect(IGameObject sender, IGameObject reciever) {
		if (reciever instanceof PushAble) {
			if (sender == null) {
				if (reciever instanceof ICharacter) {
					if (velocity == null) {
						Aim a = ((ICharacter)reciever).getAim().getNormalized();
						Velocity v = new Velocity(a.getX(), a.y);
						v = v.scale(power);
						v.setTheta(v.getTheta() + (v.getX()<0 ? aimOffset : -aimOffset));
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
						v.setTheta(v.getTheta() + (v.getX()<0 ? aimOffset: -aimOffset));
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

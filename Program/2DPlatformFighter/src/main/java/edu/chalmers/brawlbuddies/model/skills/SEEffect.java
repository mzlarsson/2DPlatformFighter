package edu.chalmers.brawlbuddies.model.skills;

import edu.chalmers.brawlbuddies.model.statuseffects.IStatusEffect;
import edu.chalmers.brawlbuddies.model.world.ICharacter;
import edu.chalmers.brawlbuddies.model.world.IGameObject;
/**
 *  A class describing a StatusEffect applying Effect
 * @author David Gustafsson
 *
 */
public class SEEffect implements IEffect{
	private IStatusEffect statusEffect;
	private int creatorID;
	
	/**
	 * Creates a new SEEffect that will apply status effect to object when exposed to the effect. 
	 *
	 * A SEEffect will not effect a target with the same ID as its creatorID 
	 * or a target that is'nt a instance of ICharacter.
	 * @param statusEffect- the status effect that will be applied.
	 */
	public SEEffect(IStatusEffect statusEffect){
		this.statusEffect = statusEffect;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean effect(IGameObject sender, IGameObject reciever) {
		if (reciever instanceof ICharacter) {
			if (reciever.getID() != creatorID) {
				((ICharacter)reciever).applyStatusEffect(statusEffect.copy());
				return true;
			}
		}
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setCreatorID(int iD) {
		creatorID = iD;		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString(){
		return "SEEffect" + " statuseffect = " + statusEffect + " creatorID = " + creatorID;
	}
}

package edu.chalmers.brawlbuddies.model.skills;

import edu.chalmers.brawlbuddies.model.statuseffects.IStatusEffect;
import edu.chalmers.brawlbuddies.model.world.ICharacter;
import edu.chalmers.brawlbuddies.model.world.IGameObject;

public class SEEffect implements IEffect{
	private IStatusEffect statusEffect;
	private int creatorID;
	public SEEffect(IStatusEffect statusEffect){
		this.statusEffect = statusEffect;
	}
	public boolean effect(IGameObject sender, IGameObject reciever) {
		if (reciever instanceof ICharacter) {
			if (reciever.getID() != creatorID) {
				((ICharacter)reciever).applyStatusEffect(statusEffect.copy());
				return true;
			}
		}
		return false;
	}

	public void setCreatorID(int iD) {
		creatorID = iD;		
	}

}

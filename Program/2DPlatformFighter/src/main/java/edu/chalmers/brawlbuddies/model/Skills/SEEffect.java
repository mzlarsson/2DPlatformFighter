package edu.chalmers.brawlbuddies.model.Skills;

import edu.chalmers.brawlbuddies.model.world.GameObject;
import edu.chalmers.brawlbuddies.statuseffects.IStatusEffect;

public class SEEffect implements Effect{
	private IStatusEffect statusEffect;
	private int creatorID;
	public SEEffect(IStatusEffect statusEffect){
		this.statusEffect = statusEffect;
	}
	public boolean effect(GameObject sender, GameObject reciever) {
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

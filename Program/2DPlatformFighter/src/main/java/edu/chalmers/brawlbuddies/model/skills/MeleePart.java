package edu.chalmers.brawlbuddies.model.skills;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.world.Creator;
import edu.chalmers.brawlbuddies.model.world.ICharacter;
import edu.chalmers.brawlbuddies.model.world.Melee;
import edu.chalmers.brawlbuddies.model.world.MeleeCreator;

public class MeleePart implements SkillPart {
	private MeleeCreator hitter;
	private Aim aim;
	private float aimOffset;
	
	
	public MeleePart(MeleeCreator hitter , Aim aim , float aimOffset){
		this.hitter = hitter;
		this.aim = aim;
		this.aimOffset = aimOffset;
	}
	
	public boolean activate(ICharacter c) {
		Melee m = hitter.fire(c.getCenterPosition(), getAim(c));
		Creator.getInstance().fireEvent(m);
		return true;
	}

	private Aim getAim(ICharacter ch) {
		if (aim!=null) {
			Aim a = aim.copy();
			a.setTheta(a.getTheta()+ (a.getX()<0 ? aimOffset : -aimOffset));
			return a;
		} else {
			Aim a = ch.getAim().copy();
			a.setTheta(a.getTheta()+ (a.getX()<0 ? aimOffset : -aimOffset));
			return a;
		}
	}
	
	public int update(int delta) {
		return 0;
	}

	public void setCreatorID(int id) {
		hitter.setCreatorID(id);
	}

}

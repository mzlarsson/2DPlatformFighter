package edu.chalmers.brawlbuddies.view;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.skills.ISkill;
import edu.chalmers.brawlbuddies.model.skills.Skill;
import edu.chalmers.brawlbuddies.model.world.ICharacter;


public class SkillWrapper implements IWrapper, ISkill {

	private Skill skill;
	public SkillWrapper(int cd, int id, int typeID, int ownerID, String animation){
		skill= new Skill(cd, id, typeID, ownerID, animation);
	}
	public void activate(ICharacter c) {
		skill.activate(c);
		EventBus eb = EventBus.getInstance();
		eb.fireEvent(new EventBusEvent("updateObject",c, this));
	}

	public int getTypeID() {
		return skill.getTypeID();
	}

	public int getUniqeID() {
		return skill.getID();
	}

	public boolean update(int delta) {
		return skill.update(delta);
	}

	public String getAnimationName() {
		return skill.getAnimationName();
	}
	public int getID() {
		return skill.getID();
	}
	public int getOwnerID(){
		return skill.getOwnerId();
	}
}

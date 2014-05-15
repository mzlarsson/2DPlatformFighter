package edu.chalmers.brawlbuddies.view;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.skills.ISkill;
import edu.chalmers.brawlbuddies.model.skills.Skill;
import edu.chalmers.brawlbuddies.model.world.ICharacter;


public class SkillWrapper implements IWrapper, ISkill {

	private Skill skill;
	
	public SkillWrapper(Skill skill) {
		this.skill = skill;
		EventBus.getInstance().fireEvent(new EventBusEvent("createObject", this, null));
	}
	
	public SkillWrapper(int cd, int id, int typeID, int ownerID, String animation){
		this(new Skill(cd, id, typeID, ownerID, animation));
	}
	
	public boolean activate(ICharacter c) {
		if (skill.activate(c)) {
			EventBus.getInstance().fireEvent(new EventBusEvent("skillUsed",c.getID(), this));
			return true;
		}
		return false;
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
		return skill.getOwnerID();
	}
}

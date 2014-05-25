package edu.chalmers.brawlbuddies.model.skills;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.IWrapper;
import edu.chalmers.brawlbuddies.model.world.ICharacter;

/**
 * A wrapper class for skill
 * @author Lisa Lipkin
 * @revised Patrik Haar
 * @version 1.0
 *
 */
public class SkillWrapper implements IWrapper, ISkill {

	private Skill skill;
	/**
	 * Creates a new SkillWrapper with a skill 
	 * @param skill
	 */
	public SkillWrapper(Skill skill) {
		this.skill = skill;
		EventBus.getInstance().fireEvent(new EventBusEvent("createObject", this, null));
	}
	/**
	 * Creates a new SkillWrapper with a skill with a cooldown, ID , typeID, ownerID and 
	 * animation
	 * @param cd
	 * @param id
	 * @param typeID
	 * @param ownerID
	 * @param animation
	 */
	public SkillWrapper(int cd, int id, int typeID, int ownerID, String animation){
		this(new Skill(cd, id, typeID, ownerID, animation));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean activate(ICharacter c) {
		if (skill.activate(c)) {
			EventBus.getInstance().fireEvent(new EventBusEvent("skillUsed",c.getID(), this));
			return true;
		}
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getTypeID() {
		return skill.getTypeID();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public int getUniqeID() {
		return skill.getID();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public boolean update(int delta) {
		return skill.update(delta);
	}
	

	/**
	 * {@inheritDoc}
	 */
	public String getAnimationName() {
		return skill.getAnimationName();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getID() {
		return skill.getID();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public int getOwnerID(){
		return skill.getOwnerID();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public int getCooldown() {
		return skill.getCooldown();
	}
}

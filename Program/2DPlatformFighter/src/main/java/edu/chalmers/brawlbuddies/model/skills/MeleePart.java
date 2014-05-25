package edu.chalmers.brawlbuddies.model.skills;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.world.Creator;
import edu.chalmers.brawlbuddies.model.world.ICharacter;
import edu.chalmers.brawlbuddies.model.world.Melee;
import edu.chalmers.brawlbuddies.model.world.MeleeCreator;
/**
 * Describe a melee creating Skillpart.
 * @author David Gustafsson
 * @version 1.0
 *
 */
public class MeleePart implements SkillPart {
	private MeleeCreator hitter;
	private Aim aim;
	private float aimOffset;
	
	/**
	 * Creates a new MeleePart with a MeeleCreator, aim and aimOffset
	 * @param hitter- a MeeleCreator that creates and activate the Meele object	
	 * @param aim - the aim from which the object will be fired from.
	 * @param aimOffset - the offset that will be added to the aim.
	 */
	public MeleePart(MeleeCreator hitter , Aim aim , float aimOffset){
		this.hitter = hitter;
		this.aim = aim;
		this.aimOffset = aimOffset;
	}
	/**
	 * {@inheritDoc}
	 */
	public boolean activate(ICharacter c) {
		Melee m = hitter.fire(c.getCenterPosition(), getAim(c));
		Creator.getInstance().fireEvent(m);
		return true;
	}
	/**
	 * Get the aim of a character with aimOffset added to it.
	 * @param ch - the Character that the aim is taken from
	 * @return the resulting Aim
	 */
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
	/**
	 * {@inheritDoc}
	 */
	public int update(int delta) {
		return 0;
	}
	/**
	 * {@inheritDoc}
	 */
	public void setCreatorID(int id) {
		hitter.setCreatorID(id);
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public String toString(){
		return "MeleePart: " +" hitter:" + hitter.toString() + " aim = " + aim + " aimOffset= " + aimOffset  ;
	}

}

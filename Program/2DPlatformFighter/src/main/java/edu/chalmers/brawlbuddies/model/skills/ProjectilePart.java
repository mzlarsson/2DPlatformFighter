package edu.chalmers.brawlbuddies.model.skills;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.world.Creator;
import edu.chalmers.brawlbuddies.model.world.ICharacter;
import edu.chalmers.brawlbuddies.model.world.IProjectile;
import edu.chalmers.brawlbuddies.model.world.IProjectileCreator;
/**
 * Describes a projectile creating SkillPart.
 * @author David Gustafsson
 * @revised Patrik Haar
 * @version 1.0
 */
public class ProjectilePart implements SkillPart {
	private IProjectileCreator shooter;
	private Aim aim;
	private float aimOffset;
	/**
	 * Creates a new Projectilepart with a projectilecreator, aim and aimOffset;
	 * @param pc- the ProjectileCreator that will create and fire the projectile.
	 * @param aim - the aim from which the object will be fired from.
	 * @param aimOffset - the offset that will be added to the aim.
	 */
	public ProjectilePart(IProjectileCreator pc, Aim aim, float aimOffset) {
		this.shooter = pc;
		this.aim = aim;
		this.aimOffset = aimOffset;
	}
	/**
	 * Get the resulting aim from a character and the aimOffset
	 * @param ch - the Character the aim will be taken from
	 * @return the resulting aim
	 */
	
	private Aim getAim(ICharacter ch) {
		if (aim!=null) {
			Aim a = aim.copy();
			a.setTheta(a.getTheta()+ (ch.getAim().getX()<0 ? aimOffset : -aimOffset));
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
	public boolean activate(ICharacter ch) {
		IProjectile p = shooter.fire(ch.getProjFirePos(), getAim(ch));
		Creator.getInstance().fireEvent(p);
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setCreatorID(int a){
		shooter.setCreatorID(a);
	}
	/**
	 * {@inheritDoc}
	 */
	public String toString(){
		return "ProjectilePart:" +  " shooter= " + shooter + " aim= " + aim + " aimOffset= " + aimOffset;
	}
}

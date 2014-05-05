package edu.chalmers.brawlbuddies.model.Skills;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.world.Projectile;
import edu.chalmers.brawlbuddies.model.world.ProjectileCreator;
/**
 * Describes a projectile creating SkillPart.
 * @author David Gustafsson
 * @revised Patrik Haar
 * @version 0.1
 */
public class ProjectilePart implements SkillPart {
	private ProjectileCreator shooter;
	private Aim aim;
	private float aimOffset;
	
	public void setCreatorID(int a){
		shooter.setCreatorID(a);
	}

	public ProjectilePart(ProjectileCreator pc, Aim aim, float aimOffset) {
		this.shooter = pc;
		this.aim = aim;
		this.aimOffset = aimOffset;
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
		// Create a projectile with the character and the projectileCreator
		Projectile p = shooter.fire(ch.getCenterPosition(), getAim(ch));
		// Tell the character to update the projectile to the listener
		ch.updateProjectile(p); //FIXME Character should not have anything with updating projectiles.
		return true;
	}
}

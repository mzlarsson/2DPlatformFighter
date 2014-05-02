package edu.chalmers.brawlbuddies.model.Skills;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.world.Projectile;
import edu.chalmers.brawlbuddies.model.world.ProjectileCreator;
/**
 * Describes a projectile creating skill for testing purposes
 * @author David Gustafsson
 * @revised Patrik Haar
 * @version 0.1
 */
public class ProjectileSkill implements SkillPart {
	private ProjectileCreator shooter;
	private Aim aim;
	private float aimOffset;
	
	public void setCreatorID(int a){
		shooter.setCreatorID(a);
	}

	public ProjectileSkill(ProjectileCreator pc, Aim aim, float aimOffset) {
		this.shooter = pc;
		this.aim = aim;
		this.aimOffset = aimOffset;
	}
	
	public Aim getAim(ICharacter ch) {
		if (aim!=null) {
			return aim;
		} else {
			Aim a = ch.getAim().copy();
			a.setTheta(a.getTheta()+ (a.getX()<0 ? aimOffset : -aimOffset));
			return a;
		}
	}
	
	public void activate(ICharacter ch) {
		// Create a projectile with the character and the projectileCreator
		Projectile p = shooter.fire(ch.getCenterPosition(), getAim(ch));
		// Tell the character to update the projectile to the listener
		ch.updateProjectile(p); //FIXME Character should not have anything with updating projectiles.
	}
}

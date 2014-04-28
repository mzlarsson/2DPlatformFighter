package edu.chalmers.brawlbuddies.model.Skills;

import org.newdawn.slick.geom.Shape;
import edu.chalmers.brawlbuddies.model.world.Character;

import edu.chalmers.brawlbuddies.model.world.Projectile;
import edu.chalmers.brawlbuddies.model.world.ProjectileCreator;
/**
 * Describes a projectile creating skill for testing purposes
 * @author David Gustafsson
 *
 */
public class ProjectileSkill implements Skill {
	private ProjectileCreator shooter;
	private float cooldownCount = 0;
	private float cooldownTime;
	
	public void setCreatorId(int a){
		shooter.setCreatorId(a);
	}

	public ProjectileSkill(Shape shape, float speed , float lifetime, float cooldownTime, Effect[] effects) {
		shooter = new ProjectileCreator(shape , speed , lifetime, effects);
		this.cooldownTime = cooldownTime;
	}
	public void resetTime(){
		cooldownCount = cooldownTime;
	}
	public void subtractTime(float a){
		if( cooldownCount - a >= 0){
		cooldownCount -= a;
		}
		else{
			cooldownCount = 0;
		}
	}
	public void activate(CharacterInterface c) {
		if( cooldownCount == 0){
		// Create a projectile with the character and the projectileCreator
		Projectile p = shooter.fire(c.getCenterPosition(), c.getAim());
		// Tell the character to update the projectile to the listener
		c.updateProjectile(p);
		}
	}
}

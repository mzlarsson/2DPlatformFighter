package edu.chalmers.brawlbuddies.model.Skills;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.Projectile;

/**
 * A interface for Character
 * @author David Gustafsson
 *
 */
public interface ICharacter extends HealAble, DamageAble, PushAble {
	public Position getCenterPosition();
	public Aim getAim();
	public void updateProjectile(Projectile p);

}

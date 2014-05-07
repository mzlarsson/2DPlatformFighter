package edu.chalmers.brawlbuddies.model.Skills;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.Projectile;
import edu.chalmers.brawlbuddies.statuseffects.IStatusEffect;

/**
 * A interface for Character
 * @author David Gustafsson
 *
 */
public interface ICharacter extends HealAble, DamageAble, PushAble {
	public Position getCenterPosition();
	public Aim getAim();
	public void updateProjectile(Projectile p);
	public void applyStatusEffect(IStatusEffect copy);
	public void addScale(float scale);
	public void removeScale(float scale);
	public void restoreScale();
	public void resetGravity();

}

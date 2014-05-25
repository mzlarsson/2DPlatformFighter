package edu.chalmers.brawlbuddies.model.world;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Position;
/**
 * A interface to describe a IProjectileCreator
 * @author Patrik Haar
 *
 */
public interface IProjectileCreator {
	/**
	 * Set the creator ID of the projectilecreator
	 * @param id - the creator ID 
	 */
	public void setCreatorID(int id);
	/**
	 * Fire a projectile at a position and with a aim.
	 * @param pos - the fire position
	 * @param aim - the aim of the projectile
	 * @return IProjectile - the fired projectile
	 */
	public IProjectile fire(Position pos, Aim aim);
}

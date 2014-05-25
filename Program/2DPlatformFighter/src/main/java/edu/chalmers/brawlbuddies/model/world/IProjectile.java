package edu.chalmers.brawlbuddies.model.world;
/**
 * A interface to describe a Projectile
 * @author Patrik Haar
 * @version 1.0
 *
 */
public interface IProjectile extends IGameObject{
	
	/**
	 * Returns the angle of this projectile
	 * @return double - The angle of this projectile
	 */
	public double getTheta();
	
}

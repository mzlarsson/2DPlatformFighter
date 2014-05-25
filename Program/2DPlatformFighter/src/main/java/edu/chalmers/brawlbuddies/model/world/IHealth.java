package edu.chalmers.brawlbuddies.model.world;
/**
 * A interface to describe Health objects
 * @author Lisa Lipkin
 *
 */
public interface IHealth {
	
	/**
	 * Increase current health with a healAmount.
	 * If currentHealth + healAmount is bigger than 
	 * maximum health. current health is set to maximum health.
	 * 
	 * @param healAmount- the amount to increase current health
	 */
	public void heal(float healAmount);
	
	/**
	 * Decrease current health with damage if sum is lesser than zero
	 * Current health is set to zero
	 * @param damage - the decrease of health
	 */
	public void takeDamage(float damage);
	
	/**
	 * Set current health to maximum health
	 */
	public void restoreHealth();
	
	/**
	 * Set current health. If the given variable is invalid the closest value
	 * is calculated.
	 * @param a The new health. Should be in the interval 0<= a <= maxHp
	 */
	public void setHealth(float health);
	
	/**
	 * Set the maximum value of health
	 * @param healthAmount - the new maximum value of health
	 */
	public void setMaxHealth(float health);
	/**
	 * Get the current value of health
	 * @return float - the current value of health
	 */
	public float getHealth();
	/**
	 * Get the maximum value of health
	 * @return float - the maximum value of health
	 */
	public float getMaxHealth();
	/**
	 * Get the amount of missing health
	 * @return float - the amount of missing health
	 */
	public float getMissingHealth();
	/**
	 * Return true if health is zero
	 * @return boolean - true if health is zero
	 */
	public boolean isDead();
	/**
	 * Get the ID of the health object
	 * @return int - the ID of the health object
	 */
	public int getID();
	
}

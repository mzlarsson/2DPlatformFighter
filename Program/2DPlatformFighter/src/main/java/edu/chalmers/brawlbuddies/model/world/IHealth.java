package edu.chalmers.brawlbuddies.model.world;

public interface IHealth {

	public void heal(float healAmount);
	public void takeDamage(float damage);
	
	public void restoreHealth();
	public void setHealth(float health);
	public void setMaxHealth(float health);
	
	public float getHealth();
	public float getMaxHealth();
	public float getMissingHealth();
	public boolean isDead();
	
}

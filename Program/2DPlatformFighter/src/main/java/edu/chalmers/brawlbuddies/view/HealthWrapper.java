package edu.chalmers.brawlbuddies.view;

import edu.chalmers.brawlbuddies.model.world.Health;
import edu.chalmers.brawlbuddies.model.world.IHealth;

public class HealthWrapper implements IWrapper, IHealth {

	private IHealth health;

	public HealthWrapper(Health health){
		this.health=health;
	}
	
	public HealthWrapper(float max, float curr, int id){
		this(new Health(max, curr, id));
		
	}

	public HealthWrapper(float max, int id) {
		this(max, max, id);
	}

	public void heal(float healAmount) {
		health.heal(healAmount);

	}

	public void takeDamage(float damage) {
		health.takeDamage(damage);

	}

	public void restoreHealth() {
		health.restoreHealth();

	}

	public void setHealth(float health) {
		this.health.setHealth(health);

	}

	public void setMaxHealth(float health) {
		this.health.setMaxHealth(health);

	}

	public float getHealth() {
		return health.getHealth();
	}

	public float getMaxHealth() {
		return health.getMaxHealth();
	}

	public float getMissingHealth() {
		return health.getMissingHealth();
	}

	public boolean isDead() {
		return health.isDead();
	}

	public int getTypeID() {
		return -1;
	}

	public int getUniqeID() {
		return getID();
	}

	public int getID() {
		return health.getID();
	}

}

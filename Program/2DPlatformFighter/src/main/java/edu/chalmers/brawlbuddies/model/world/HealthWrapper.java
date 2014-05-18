package edu.chalmers.brawlbuddies.model.world;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.IWrapper;

public class HealthWrapper implements IWrapper, IHealth {

	private IHealth health;

	public HealthWrapper(Health health){
		this.health=health;
		EventBus.getInstance().fireEvent(new EventBusEvent("createObject", this, null));
	}
	
	public HealthWrapper(float max, float curr, int id){
		this(new Health(max, curr, id));
		
	}

	public HealthWrapper(float max, int id) {
		this(max, max, id);
	}

	public void heal(float healAmount) {
		health.heal(healAmount);
		EventBus.getInstance().fireEvent(new EventBusEvent("updateObject", this, null));
	}

	public void takeDamage(float damage) {
		health.takeDamage(damage);
		EventBus.getInstance().fireEvent(new EventBusEvent("updateObject", this, null));
	}

	public void restoreHealth() {
		health.restoreHealth();
		EventBus.getInstance().fireEvent(new EventBusEvent("updateObject", this, null));

	}

	public void setHealth(float health) {
		this.health.setHealth(health);
		EventBus.getInstance().fireEvent(new EventBusEvent("updateObject", this, null));

	}

	public void setMaxHealth(float health) {
		this.health.setMaxHealth(health);
		EventBus.getInstance().fireEvent(new EventBusEvent("updateObject", this, null));

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

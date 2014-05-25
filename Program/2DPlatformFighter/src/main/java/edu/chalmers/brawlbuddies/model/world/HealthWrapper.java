package edu.chalmers.brawlbuddies.model.world;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.IWrapper;
/**
 
 * A wrapper class for Health that send events to the view when relevant changes occurs.
 * @author Lisa Lipkin
 * @version 1.0
 *
 */
public class HealthWrapper implements IWrapper, IHealth {

	private IHealth health;
	
	/**
	 * Creates a new HealthWrapper with health.
	 * @param health - the health that wrapper contains
	 */
	public HealthWrapper(Health health){
		this.health=health;
		EventBus.getInstance().fireEvent(new EventBusEvent("createObject", this, null));
	}
	/**
	 * Creates a new HealthWraper with a health object based on
	 * maxhealth, currenthealth and ID.
	 * @param max - the maximum health of the health object
	 * @param curr - the current health of the health object
	 * @param id - the ID of the health object
	 */
	public HealthWrapper(float max, float curr, int id){
		this(new Health(max, curr, id));
		
	}
	
	/**
	 * Creates a new HealthWrapper with a health object based on maxhealth and ID
	 * @param max - the maximum health of the health object
	 * @param id - the ID of the health object
	 */
	public HealthWrapper(float max, int id) {
		this(max, max, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void heal(float healAmount) {
		health.heal(healAmount);
		EventBus.getInstance().fireEvent(new EventBusEvent("updateObject", this, null));
	}
	

	/**
	 * {@inheritDoc}
	 */
	public void takeDamage(float damage) {
		health.takeDamage(damage);
		EventBus.getInstance().fireEvent(new EventBusEvent("updateObject", this, null));
	}
	

	/**
	 * {@inheritDoc}
	 */
	public void restoreHealth() {
		health.restoreHealth();
		EventBus.getInstance().fireEvent(new EventBusEvent("updateObject", this, null));

	}
	

	/**
	 * {@inheritDoc}
	 */
	public void setHealth(float health) {
		this.health.setHealth(health);
		EventBus.getInstance().fireEvent(new EventBusEvent("updateObject", this, null));

	}
	

	/**
	 * {@inheritDoc}
	 */
	public void setMaxHealth(float health) {
		this.health.setMaxHealth(health);
		EventBus.getInstance().fireEvent(new EventBusEvent("updateObject", this, null));

	}


	/**
	 * {@inheritDoc}
	 */
	public float getHealth() {
		return health.getHealth();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public float getMaxHealth() {
		return health.getMaxHealth();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public float getMissingHealth() {
		return health.getMissingHealth();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public boolean isDead() {
		return health.isDead();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public int getTypeID() {
		return -1;
	}
	

	/**
	 * {@inheritDoc}
	 */
	public int getUniqeID() {
		return getID();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public int getID() {
		return health.getID();
	}

}

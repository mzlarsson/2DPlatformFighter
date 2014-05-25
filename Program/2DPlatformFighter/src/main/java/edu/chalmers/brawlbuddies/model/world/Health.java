package edu.chalmers.brawlbuddies.model.world;

/**
 * A class describing health for a character
 * 
 * @author David Gustafsson
 * @revised Patrik Haar
 * @revised Matz Larsson
 * @version 1.0
 */

public class Health implements IHealth{

	private float maxHp;
	private float currentHp;
	private int ownerID;

	/**
	 * Creates a Health object with a max and current health value.
	 * @param max - The maximum health value.
	 * @param curr - The current health value.
	 */
	public Health(float max, float curr, int id) {
		this.maxHp = max;
		this.currentHp = curr;
		ownerID=id;
	}
	
	/**
	 * Creates a Health object with a maximum health amount and a ID.
	 * @param max -  The maximum health value.
	 * @param id - the ID of the health object
	 */
	public Health(float max, int id) {
		this(max, max, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void takeDamage(float damage) {
		this.setHealth(currentHp-damage);
	};

	/**
	 * Damage health with a percentage based on current Health.
	 * Damage cannot exceed maximumdamage or be lower than minimumdamage.
	 * 
	 * @param percentage - the percentage of current health that will be taken as damage
	 * @param minimumdamage - the minimum amount of damage that can be taken
	 * @param maximumdamage - the maximum amount of damage that can be taken
	 */
	public void takeDamageCurrentHealth(float percentage, float minimumdamage,
			float maximumdamage) {
		if ((currentHp / 100) * percentage >= maximumdamage
				&& maximumdamage > 0) {
			takeDamage(maximumdamage);
		} else if ((currentHp / 100) * percentage >= minimumdamage
				&& minimumdamage > 0) {
			takeDamage(minimumdamage);
		} else {
			takeDamage(currentHp / 100 * percentage);
		}
	}

	/**
	 * Damage health with a percentage based on missing health. The damage cannot 
	 * exceed maximumdamage or be lower than minimumdamage.
	 * 
	 * @param percentage - the percentage of missing health that will be taken as damage
	 * @param minimumdamage - the minimum amount of damage that can be taken
	 * @param maximumdamage - the maximum amount of damage that can be taken
	 */
	public void takeDamageMissingHealth(float percentage, float minimumdamage,
			float maximumdamage) {
		if ((maxHp - currentHp / 100) * percentage >= maximumdamage
				&& maximumdamage > 0) {
			takeDamage(maximumdamage);
		} else if ((maxHp - currentHp / 100) * percentage >= minimumdamage
				&& minimumdamage > 0) {
			takeDamage(minimumdamage);
		} else {
			takeDamage(maxHp - currentHp / 100 * percentage);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void heal(float healAmount) {
		this.setHealth(currentHp+healAmount);
	}

	/**
	 * Heal health with percentage based on missing health. 
	 * Heal cannot exceed maxhealing or 
	 * be lower than minimumhealing.
	 * 
	 * @param percentage -  the percentage of missing health to be healed
	 * @param maxhealing - the minimum healing amount
	 * @param minimumhealing - the maximum healing amount
	 */
	public void healPercentageofMissing(float percentage, float maxhealing,
			float minimumhealing) {
		if ((maxHp - currentHp / 100) * percentage >= maxhealing
				&& maxhealing > 0) {
			heal(maxhealing);
		} else if ((maxHp - currentHp / 100) * percentage >= minimumhealing
				&& minimumhealing > 0) {
			heal(minimumhealing);
		} else {
			heal(maxHp - currentHp / 100 * percentage);
		}
	}

	/**
	 * Heal health a percentage based on maximum health
	 * The heal cannot exceed maxhealing or be lower than minimum healing
	 * 
	 * @param percentage -  the percentage of maximum health to be healed
	 * @param maxhealing - the minimum healing amount
	 * @param minimumhealing - the maximum healing amount
	 */public void healPercetageofMaximum(float percentage, float maxhealing,
			float minimumhealing) {
		if ((maxHp / 100) * percentage >= maxhealing && maxhealing > 0) {
			heal(maxhealing);
		} else if ((maxHp / 100) * percentage >= minimumhealing
				&& minimumhealing > 0) {
			heal(minimumhealing);
		} else {
			heal(maxHp / 100 * percentage);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public float getHealth() {
		return currentHp;
	}

	/**
	 * {@inheritDoc}
	 */
	public void restoreHealth() {
		this.setHealth(maxHp);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isDead(){
		return getHealth() == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setMaxHealth(float a) {
		maxHp = a;
	}

	/**
 	* {@inheritDoc}
 	*/
	public void setHealth(float a) {
		currentHp = Math.min(Math.max(0, a), maxHp);
	}

	/**
	 * {@inheritDoc}
	 */
	public float getMissingHealth() {
		return getMaxHealth()-getHealth();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public float getMaxHealth() {
		return maxHp;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getID(){
		return ownerID;
	}
}
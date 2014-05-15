package edu.chalmers.brawlbuddies.model.world;

//TODO check if percentage heal and damage is needed.

/**
 * A class describing health for a character
 * 
 * @author David Gustafsson
 * @revised Patrik Haar
 * @revised Matz Larsson
 */

public class Health implements IHealth{

	private float maxHp;
	private float currentHp;
	private int ownerID;

	/**
	 * Creates a Health object with a max and current health value.
	 * @param max The maximum health value.
	 * @param curr The current health value.
	 */
	public Health(float max, float curr, int id) {
		this.maxHp = max;
		this.currentHp = curr;
		ownerID=id;
	}
	
	/**
	 * Creates a Health object with a max value.
	 * @param max The maximum health value.
	 */
	public Health(float max, int id) {
		this(max, max, id);
	}
	
	/**
	 * Decrease currentHp with float a if sum is lesser than zero currentHp is
	 * set to zero
	 * 
	 * @param damage
	 */
	public void takeDamage(float damage) {
		this.setHealth(currentHp-damage);
	};

	/**
	 * Decrease current health with a percentage based on current Health if the
	 * result is lesser than minimum damage current health take minimum damage
	 * if result is larger than maximum damage current health take maximum
	 * damage
	 * 
	 * @param percentage
	 * @param minimumdamage
	 * @param maximumdamage
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
	 * Decrease currentHp with a percentage based on missing health if the
	 * result is lesser than minimumdamage currentHp take minimumdamage if
	 * result is larger than maximumdamage currentHp take maximumdamage
	 * 
	 * @param percentage
	 * @param minimumdamage
	 * @param maximumdamage
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
	 * Increase currentHp with a float if currentHp + a is bigger than maxHp,
	 * currentHp is set to maxHp
	 * 
	 * @param a
	 */
	public void heal(float a) {
		this.setHealth(currentHp+a);
	}

	/**
	 * Heals a character a percentage based on missing health
	 * 
	 * @param percentage
	 * @param maxhealing
	 * @param minimumhealing
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
	 * Heals a character a percentage based on maximum health
	 * 
	 * @param percentage
	 * @param maxhealing
	 * @param minimumhealing
	 */
	public void healPercetageofMaximum(float percentage, float maxhealing,
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
	 * Get current health
	 * 
	 * @return
	 */
	public float getHealth() {
		return currentHp;
	}

	/**
	 * Set current health to maximum health
	 */
	public void restoreHealth() {
		this.setHealth(maxHp);
	}
	public boolean isDead(){
		return getHealth() == 0;
	}

	/**
	 * Set max health
	 * @param a
	 */
	public void setMaxHealth(float a) {
		maxHp = a;
	}

	/**
	 * Set current health. If the given variable is invalid the closest value
	 * is calculated.
	 * @param a The new health. Should be in the interval 0<= a <= maxHp
	 */
	public void setHealth(float a) {
		currentHp = Math.min(Math.max(0, a), maxHp);
	}

	/**
	 * Get missing health
	 * @return missing health
	 */
	public float getMissingHealth() {
		return getMaxHealth()-getHealth();
	}

	public float getMaxHealth() {
		return maxHp;
	}
	public int getID(){
		return ownerID;
	}
}
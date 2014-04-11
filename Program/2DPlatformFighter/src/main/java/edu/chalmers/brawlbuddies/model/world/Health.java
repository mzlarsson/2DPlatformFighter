package edu.chalmers.brawlbuddies.model.world;

import java.io.ObjectStreamException;


import com.thoughtworks.xstream.annotations.XStreamAlias;

//TODO check if percentage heal and damage is needed.
@XStreamAlias("health")
/**
 * A class describing health for a character
 * @author David Gustafsson
 *
 */
public class Health {
	@XStreamAlias("value")
	private float maxHp;
	private float currentHp;
	/**
	 * Decrease currentHp with float a if sum is lesser than zero currentHp is set to zero
	 * @param damage
	 */
	public void takeDamage(float damage) {
		if (currentHp - damage <= 0) {
			currentHp = 0;
		} else {
			currentHp -= damage;
		}
	};
	/**
	 * Decrease current health with a percentage based on current Health if the result is lesser than minimum damage
	 * current health take minimum damage if result is larger than maximum damage current health take maximum damage
	 *  
	 * @param percentage
	 * @param minimumdamage
	 * @param maximumdamage
	 */
	public void takeDamageCurrentHealth(float percentage, float minimumdamage,
			float maximumdamage) {
		if ((currentHp / 100) * percentage >= maximumdamage && maximumdamage > 0) {
			takeDamage(maximumdamage);
		} else if((currentHp / 100) * percentage >= minimumdamage && minimumdamage > 0) {
			takeDamage(minimumdamage);
			}
		else{
			takeDamage(currentHp / 100 * percentage);
		}
	}
	/**
	 * Decrease currentHp with a percentage based on missing health if the result is lesser than minimumdamage
	 * currentHp take minimumdamage if result is larger than maximumdamage currentHp take maximumdamage
	 *  
	 * @param percentage
	 * @param minimumdamage
	 * @param maximumdamage
	 */
	public void takeDamageMissingHealth(float percentage, float minimumdamage, float maximumdamage){
		if ((maxHp - currentHp / 100) * percentage >= maximumdamage && maximumdamage > 0) {
			takeDamage(maximumdamage);
		} else if((maxHp - currentHp / 100) * percentage >= minimumdamage && minimumdamage > 0) {
			takeDamage(minimumdamage);
			}
		else{
			takeDamage(maxHp - currentHp / 100 * percentage);
		}
	}
	/**
	 * Increase currentHp with a float if currentHp + a is bigger than maxHp, currentHp is set to maxHp 
	 * @param a
	 */
	public void heal(float a){
		if( currentHp + a > maxHp){
			currentHp = maxHp;
		}else{
			currentHp += a;
		}
	}
	/**
	 * Heals a character a percentage based on missing health 
	 * @param percentage
	 * @param maxhealing
	 * @param minimumhealing
	 */
	public void healPercentageofMissing(float percentage , float maxhealing, float minimumhealing){
		if ((maxHp - currentHp / 100) * percentage >= maxhealing && maxhealing > 0) {
			heal(maxhealing);
		} else if((maxHp - currentHp / 100) * percentage >= minimumhealing && minimumhealing > 0) {
			heal(minimumhealing);
			}
		else{
			heal(maxHp - currentHp / 100 * percentage);
		}
	}
	/**
	 * Heals a character a percentage based on maximum health 
	 * @param percentage
	 * @param maxhealing
	 * @param minimumhealing
	 */
	public void healPercetageofMaximum(float percentage , float maxhealing, float minimumhealing){
		if ((maxHp/ 100) * percentage >= maxhealing && maxhealing > 0) {
			heal(maxhealing);
		} else if((maxHp/ 100) * percentage >= minimumhealing && minimumhealing > 0) {
			heal(minimumhealing);
			}
		else{
			heal(maxHp/ 100 * percentage);
		}
	}
	/**
	 * Get current health
	 * @return
	 */
	public float getHp(){
		return currentHp;
	}
	/**
	 * Set current health to maximum health
	 */
	public void restoreHp(){
		currentHp = maxHp;
	}
	private Object readResolve() throws ObjectStreamException {
		this.currentHp = maxHp;
		return this;
	}
}

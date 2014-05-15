package edu.chalmers.brawlbuddies.view;

import edu.chalmers.brawlbuddies.model.world.IHealth;

public class HealthWrapper implements IWrapper, IHealth {
	
	public HealthWrapper(float max, float curr){
		public Health(float max, float curr);
		
	}
public Health(float max){
	this(max, max);
}

	public void heal(float healAmount) {
		// TODO Auto-generated method stub

	}

	public void takeDamage(float damage) {
		// TODO Auto-generated method stub

	}

	public void restoreHealth() {
		// TODO Auto-generated method stub

	}

	public void setHealth(float health) {
		// TODO Auto-generated method stub

	}

	public void setMaxHealth(float health) {
		// TODO Auto-generated method stub

	}

	public float getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getMaxHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getMissingHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getTypeID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getUniqeID() {
		// TODO Auto-generated method stub
		return 0;
	}

}

package edu.chalmers.brawlbuddies.view;

import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.IWrapper;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.HealthWrapper;
import edu.chalmers.brawlbuddies.model.world.IHealth;

/**
 * A class to represent the healthbar int the view
 * 
 * @author Lisa
 * 
 */
public class HealthImage implements IDrawable {
	private Position position;
	private int barLength = 167;
	private float maxHealth;
	private float currentHealth;
	private int id;
	private Rectangle bar = new Rectangle(0, 0, barLength, 18);

	/**
	 * creates the health bar
	 * 
	 * @param health
	 *            an IHealth object to access the neccesary information
	 */
	public HealthImage(IHealth health) {
		id = health.getID();
		maxHealth = health.getMaxHealth();
		currentHealth = maxHealth;
		bar.setWidth(barLength);
	}

	/**
	 * returns the uniqe id of the health bar. that is the id of the owner to
	 * the health bar
	 */
	public Integer getUniqeID() {
		return id;
	}

	/**
	 * sets the health bar upper left corner position to p
	 * 
	 * @param p
	 *            the position p
	 */
	public void setPosition(Position p) {
		position = p;
		bar.setLocation(p.getX(), p.getY());
	}

	/**
	 * {@inheritDoc} renders the health bar
	 */
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.red);
		g.fill(bar);
		g.setColor(Color.yellow);
		g.drawString(
				Float.toString(currentHealth),
				position.getX()
						+ ((barLength - g.getFont().getWidth(
								Float.toString(currentHealth))) / 2),
				position.getY());
		g.setColor(Color.black);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(IWrapper obj1, IWrapper obj2) {
		HealthWrapper wrapper = (HealthWrapper) obj1;
		currentHealth = wrapper.getHealth();
		bar.setWidth(barLength * (wrapper.getHealth() / maxHealth));
	}

}

package edu.chalmers.brawlbuddies.view;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import edu.chalmers.brawlbuddies.model.IWrapper;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.ProjectileWrapper;
/**
 * A class to describe the graphical representation of a projectile
 * @author Lisa Lipkin
 *
 */
public class ProjectileImage implements IDrawable {
	private Animation animation;
	private Position centerPos;
	private double angle;
	private int id;
	/**
	 * Create a new ProjectileImage with a aim and ID
	 * @param aim
	 * @param id
	 */
	public ProjectileImage(Animation aim, int id) {
		animation = aim.copy();
		animation.start();
		this.id=id;
	}
	/**
	 * {@inheritDoc}
	 */
	public void render(GameContainer gc, Graphics g) {
		g.rotate(centerPos.getX(), centerPos.getY(), (float) angle);
		animation.draw(centerPos.getX()-animation.getWidth()/2, centerPos.getY()-animation.getHeight()/2);
		g.rotate(centerPos.getX(), centerPos.getY(), (float) -angle);
	}
	/**
	 * {@inheritDoc}
	 */
	public void update(IWrapper obj1, IWrapper obj2) {
		ProjectileWrapper projectile = (ProjectileWrapper) obj1;
		centerPos = projectile.getCenterPosition();
		angle = projectile.getTheta()+90;

	}
	/**
	 * {@inheritDoc}
	 */
	public Integer getUniqeID() {
		return id;
	}

}

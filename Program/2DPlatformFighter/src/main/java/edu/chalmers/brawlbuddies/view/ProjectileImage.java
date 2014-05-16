package edu.chalmers.brawlbuddies.view;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import edu.chalmers.brawlbuddies.model.IWrapper;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.ProjectileWrapper;

public class ProjectileImage implements IDrawable {
	private Animation animation;
	private Position centerPos;
	private double angle;
	private int id;
	
	public ProjectileImage(Animation anim, int id) {
		animation = anim.copy();
		animation.start();
		this.id=id;
	}

	public void render(GameContainer gc, Graphics g) {
		g.rotate(centerPos.getX(), centerPos.getY(), (float) angle);
		animation.draw(centerPos.getX()-animation.getWidth()/2, centerPos.getY()-animation.getHeight()/2);
		g.rotate(centerPos.getX(), centerPos.getY(), (float) -angle);
	}

	public void update(IWrapper obj1, IWrapper obj2) {
		ProjectileWrapper projectile = (ProjectileWrapper) obj1;
		centerPos = projectile.getCenterPosition();
		angle = projectile.getTheta()+90;

	}

	public Integer getUniqeID() {
		return id;
	}

}

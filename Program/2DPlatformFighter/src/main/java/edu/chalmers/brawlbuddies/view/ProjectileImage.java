package edu.chalmers.brawlbuddies.view;

import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.services.factories.AnimationMapFactory;

public class ProjectileImage implements IDrawable{
	private Map<String, Animation> mapAnimation;
	private Animation animation;
	private Position position;
	private double angle;

	public ProjectileImage(ProjectileWrapper projectile) {
		position = projectile.getCenterPosition();
		mapAnimation = AnimationMapFactory.create(projectile.getTypeID());
		angle = projectile.getTheta();
		animation = mapAnimation.get("idle");
		animation.start();
	}

	public void render(GameContainer gc, Graphics g) {
		g.rotate(position.getX(), position.getY(), (float) angle);
		animation.draw(position.getX(), position.getY());
	}

	public void update(IWrapper obj1, IWrapper obj2) {
		ProjectileWrapper projectile = (ProjectileWrapper) obj1;
		position = projectile.getCenterPosition();
		angle = projectile.getTheta();
		
	}

}

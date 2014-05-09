package edu.chalmers.brawlbuddies.view;

import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.services.factories.AnimationMapFactory;

public class ProjectileImage implements IDrawable {
	private Map<String, Animation> mapAnimation;
	private Animation animation;
	private Position position;
	private Position centerPos;
	private double angle;

	public ProjectileImage(ProjectileWrapper projectile) {
		position = projectile.getCenterPosition();
		mapAnimation = AnimationMapFactory.create(projectile.getTypeID());
		angle = projectile.getTheta();
		animation = mapAnimation.get("idle");
		animation.start();
	}

	public void render(GameContainer gc, Graphics g) {
		g.rotate(centerPos.getX(), centerPos.getY(), (float) angle);
		animation.draw(position.getX(), position.getY());
		g.rotate(centerPos.getX(), centerPos.getY(), (float) -angle);
	}

	public void update(IWrapper obj1, IWrapper obj2) {
		ProjectileWrapper projectile = (ProjectileWrapper) obj1;
		position = projectile.getPosition();
		centerPos = projectile.getCenterPosition();
		angle = projectile.getTheta()+90;

	}

}

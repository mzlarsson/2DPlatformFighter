package edu.chalmers.brawlbuddies.view;

import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.IWrapper;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.skills.SkillWrapper;

public class SkillImage implements IDrawable {
	private Position position = new Position(0, 0);
	private Map<String, Animation> mapAnimation;
	private Animation animation;
	private int ownerId;
	private int id;

	private int delta;
	private long lastFrame;

	private int cooldown = 5000;
	private int cooldownScale = 0;
	private float skillHeight = 30;
	private Color color = new Color(128, 128, 128, 150);
	private Rectangle cooldownRect = new Rectangle(0, 0, 30, 0);

	public SkillImage(SkillWrapper skill) {
		ownerId = skill.getOwnerID();
		id = skill.getID();
		mapAnimation = AnimationMapFactory.create(skill.getTypeID());
		animation = mapAnimation.get("idle");
		animation.start();
		cooldown=skill.getCooldown();
	}

	public void render(GameContainer gc, Graphics g) {
		if (lastFrame > 0) {
			delta = (int) (gc.getTime() - lastFrame);
		}
		lastFrame = gc.getTime();

		animation.draw(position.getX(), position.getY());
		setCooldownScale(cooldownScale - delta);
		setCooldownRectangle();
		renderCooldown(g);
	}

	private void renderCooldown(Graphics g) {
		g.setColor(color);
		g.fill(cooldownRect);
		g.setColor(Color.black);

	}

	public void place(Position p) {
		position = p;
		cooldownRect.setLocation(p.getX(), p.getY() + 30);
	}

	public void update(IWrapper obj1, IWrapper obj2) {
		SkillWrapper skill = (SkillWrapper) obj1;
		setCooldownScale(cooldown);
	}

	private void setCooldownRectangle() {
		cooldownRect.setHeight(-skillHeight * (cooldownScale / (float)cooldown));
	}

	private void setCooldownScale(int i) {
		if (i <= cooldown && i >= 0) {
			cooldownScale = i;
		}

	}

	public int getID() {
		return ownerId;
	}

	public Integer getUniqeID() {
		return id;
	}

}

package edu.chalmers.brawlbuddies.view;

import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.IWrapper;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.skills.SkillWrapper;

public class SkillImage implements IDrawable {
	private Position position = new Position(0, 0);
	private Map<String, Animation> mapAnimation;
	private Animation animation;
	private int ownerId;
	private int id;

	private int cooldown = 5000;
	private int cooldownScale = 0;
	private float skillHeight = 30;
	private Color color = new Color(80, 80, 80, 150);
	private Rectangle cooldownRect = new Rectangle(0, 0, 30, 0);

	public SkillImage(SkillWrapper skill) {
		ownerId = skill.getOwnerID();
		id = skill.getID();
		mapAnimation = AnimationMapFactory.create(skill.getTypeID());
		animation = mapAnimation.get("idle");
		animation.start();
		cooldown=skill.getCooldown();
	}

	public void update(int delta) {
		setCooldownScale(cooldownScale - delta);
		setCooldownRectangle();
	}
	
	public void render(GameContainer gc, Graphics g) {
		animation.draw(position.getX(), position.getY(), 30, 30);
		renderCooldown(g);
	}

	private void renderCooldown(Graphics g) {
		if (cooldownScale>0) {
			g.setColor(color);
			g.fill(cooldownRect);
			g.setColor(Color.yellow);
			g.drawString(cooldownScale/1000 + "." + (cooldownScale%1000)/100, position.getX(), position.getY()+5);
			g.setColor(Color.black);
		}
	}

	public void place(Position p) {
		position = p;
		cooldownRect.setLocation(p.getX(), p.getY() + 30);
	}

	public void update(IWrapper obj1, IWrapper obj2) {
		setCooldownScale(cooldown);
	}

	private void setCooldownRectangle() {
		cooldownRect.setHeight(-skillHeight * (cooldownScale / (float)cooldown));
	}

	private void setCooldownScale(int i) {
		cooldownScale = i;
	}

	public int getID() {
		return ownerId;
	}

	public Integer getUniqeID() {
		return id;
	}

}

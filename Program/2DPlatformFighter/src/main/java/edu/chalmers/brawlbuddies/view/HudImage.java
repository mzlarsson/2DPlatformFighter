package edu.chalmers.brawlbuddies.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.Health;

public class HudImage implements IDrawable {
	private int hudID;
	private static int hudIndex = 0;
	private int hudLength = 274;
	private int hudHeight = 99;

	private Animation animation;
	private Map<String, Animation> mapAnimation;

	private HealthImage healthBar;
	private Image characterIcon;
	private Map<Integer, SkillImage> skills = new HashMap<Integer, SkillImage>();

	private Position upperLeftCornerPosition;
	private Position characterImagePosition;
	private ArrayList<Position> skillPosition = new ArrayList<Position>();
	private Position healthBarPosition;

	public HudImage(IWrapper wrapper) {
		System.out.println("hud created");
		if (wrapper.getClass() == SkillWrapper.class) {
			SkillWrapper skll = (SkillWrapper) wrapper;
			hudID = skll.getOwnerID();
		} else if (wrapper.getClass() == HealthWrapper.class) {
			HealthWrapper health = (HealthWrapper) wrapper;
			hudID = health.getID();

		}
		mapAnimation = AnimationMapFactory.create(40);
		animation = mapAnimation.get("idle");
		animation.start();
		// TODO set characterIcon due to charactertypeid
		try {
			characterIcon = new Image(80, 80);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		upperLeftCornerPosition = new Position(hudIndex, 0);
		healthBarPosition = new Position(upperLeftCornerPosition.getX() + 99,
				16);
		skillPosition.add(new Position(upperLeftCornerPosition.getX() + 100,
				upperLeftCornerPosition.getY() + 60));
		skillPosition.add(new Position(upperLeftCornerPosition.getX() + 140,
				upperLeftCornerPosition.getY() + 60));
		skillPosition.add(new Position(upperLeftCornerPosition.getX() + 180,
				upperLeftCornerPosition.getY() + 60));
		skillPosition.add(new Position(upperLeftCornerPosition.getX() + 220,
				upperLeftCornerPosition.getY() + 60));

		hudIndex = hudIndex + 300;
	}

	/**
	 * returns the currently correct position to place a skill on.
	 * 
	 * @return the position to place the next skill
	 */
	private Position getSkillPosition() {
		System.out.println(skills.size() - 1);
		return skillPosition.get(skills.size() - 1);
	}

	private Position getHealthBarPosition() {
		return healthBarPosition;
	}

	public void addHealthBar(HealthImage health) {
		healthBar = health;
		healthBar.setPosition(getHealthBarPosition());
	}

	/**
	 * add a skill to the skill list and give it the correct position.
	 * 
	 * @param skill
	 *            the skill to be added.
	 */
	public void addSkill(SkillImage skill) {
		skills.put(skill.getUniqeID(), skill);
		skills.get(skill.getUniqeID()).place(getSkillPosition());

	}

	public void render(GameContainer gc, Graphics g) {
		animation.draw(upperLeftCornerPosition.getX(),
				upperLeftCornerPosition.getY());
		characterIcon.draw(upperLeftCornerPosition.getX() + 10,
				upperLeftCornerPosition.getY() + 10);

		for (Map.Entry<Integer, SkillImage> entry : skills.entrySet()) {
			entry.getValue().render(gc, g);
		}
		healthBar.render(gc, g);
	}

	public void update(IWrapper obj1, IWrapper obj2) {
		System.out.println("update hud");
		System.out.println(obj1.getClass());
		if (obj1.getClass() == HealthWrapper.class) {
			healthBar.update(obj1, obj2);
		}
		if (obj1.getClass() == SkillWrapper.class) {
			skills.get(obj1.getUniqeID()).update(obj1, obj2);
		}
		if (obj1.getClass() == CharacterWrapper.class) {
			// TODO fix life
		}

	}

	public Integer getUniqeID() {
		return hudID;
	}

}

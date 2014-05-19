package edu.chalmers.brawlbuddies.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import edu.chalmers.brawlbuddies.model.IWrapper;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.skills.SkillWrapper;
import edu.chalmers.brawlbuddies.model.world.CharacterWrapper;
import edu.chalmers.brawlbuddies.model.world.HealthWrapper;

/**
 * a class to represent and draw the heads up display of a character in game.
 * 
 * @author Lisa
 * 
 */
public class HudImage implements IDrawable {
	// goal oriented variables
	public static boolean lifeLimit = false;
	public int lives = 0;
	// id oriented variables
	private int hudID;
	private static int hudIndex = 0;
	private int hudNr = 0;
	// objects that the hud contains
	private Map<String, Animation> mapAnimation;
	private Map<Integer, SkillImage> skills;
	private Animation animation;
	private HealthImage healthBar;
	private Animation icon;
	// positions for the hud and all its objects
	private Position upperLeftCornerPosition;
	private ArrayList<Position> skillPosition;
	private Position healthBarPosition;

	/**
	 * constructor for the hud. gives values to instance variables.
	 * 
	 * @param wrapper
	 *            a character wrapper containing the neccecary id
	 */
	public HudImage(IWrapper wrapper) {
		CharacterWrapper charac = (CharacterWrapper) wrapper;

		// set correct animations to objects
		mapAnimation = AnimationMapFactory.create(40);
		skills = new HashMap<Integer, SkillImage>();
		animation = mapAnimation.get("idle");
		animation.start();
		Map<String, Animation> characterIcon = AnimationMapFactory
				.create(charac.getTypeID());
		icon = characterIcon.get("icon");

		// set positions
		skillPosition = new ArrayList<Position>();
		upperLeftCornerPosition = new Position(0, 0);
		upperLeftCornerPosition = new Position(hudIndex, 0);
		healthBarPosition = new Position(upperLeftCornerPosition.getX() + 99,
				16);
		skillPosition.add(new Position(upperLeftCornerPosition.getX() + 110,
				upperLeftCornerPosition.getY() + 60));
		skillPosition.add(new Position(upperLeftCornerPosition.getX() + 150,
				upperLeftCornerPosition.getY() + 60));
		skillPosition.add(new Position(upperLeftCornerPosition.getX() + 190,
				upperLeftCornerPosition.getY() + 60));
		skillPosition.add(new Position(upperLeftCornerPosition.getX() + 230,
				upperLeftCornerPosition.getY() + 60));

		// handle identification
		hudID = charac.getUniqeID();
		hudNr = hudIndex;
		hudIndex = hudIndex + 300;
	}

	/**
	 * returns the currently correct position to place a skill on.
	 * 
	 * @return the position to place the next skill
	 */
	private Position getSkillPosition() {
		return skillPosition.get(skills.size() - 1);
	}

	private Position getHealthBarPosition() {
		return healthBarPosition;
	}

	public Integer getUniqeID() {
		return hudID;
	}

	/**
	 * sets the starting amount of lives for the hud
	 * 
	 * @param l
	 *            the amount of lives to start the game with
	 */
	public void setLives(int l) {
		if (lifeLimit) {
			lives = l;
		}

	}

	public void update(int delta) {
		for (Map.Entry<Integer, SkillImage> entry : skills.entrySet()) {
			entry.getValue().update(delta);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void render(GameContainer gc, Graphics g) {
		animation.draw(upperLeftCornerPosition.getX(),
				upperLeftCornerPosition.getY());
		icon.draw(upperLeftCornerPosition.getX() + 10,
				upperLeftCornerPosition.getY() + 10);

		for (Map.Entry<Integer, SkillImage> entry : skills.entrySet()) {
			entry.getValue().render(gc, g);
		}
		healthBar.render(gc, g);
		renderLives(g);
	}

	/**
	 * renhers the life information
	 * 
	 * @param g
	 *            the slick graphics object
	 */
	private void renderLives(Graphics g) {
		if (lifeLimit) {
			g.setColor(Color.green);
		} else {
			g.setColor(Color.red);
		}
		g.drawString(Integer.toString(lives), 20 + hudNr, 100);
		g.setColor(Color.black);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(IWrapper obj1, IWrapper obj2) {
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

	/**
	 * handles the lifes on the event of a character's death
	 */
	public void characterDied() {
		if (lifeLimit) {
			lives = lives - 1;
		} else {
			lives++;
		}

	}

	/**
	 * handles the hud on game over
	 */
	public void gameOver() {
		hudIndex = 0;
		lifeLimit = false;
		skillPosition.clear();
	}

}

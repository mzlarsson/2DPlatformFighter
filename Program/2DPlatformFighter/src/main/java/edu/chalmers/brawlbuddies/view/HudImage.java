package edu.chalmers.brawlbuddies.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.IWrapper;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.skills.SkillWrapper;
import edu.chalmers.brawlbuddies.model.world.CharacterWrapper;
import edu.chalmers.brawlbuddies.model.world.Health;
import edu.chalmers.brawlbuddies.model.world.HealthWrapper;

public class HudImage implements IDrawable {
	public static boolean lifeLimit=false;
	public int lives=0;

	private int hudID;
	private static int hudIndex = 0;
	private int hudNr = 0;
	private int hudLength = 274;
	private int hudHeight = 99;

	private Animation animation;
	private Map<String, Animation> mapAnimation;

	
	
	private HealthImage healthBar;
	private Animation icon;
	private Map<Integer, SkillImage> skills = new HashMap<Integer, SkillImage>();

	private Position upperLeftCornerPosition;
	private Position characterImagePosition;
	private ArrayList<Position> skillPosition = new ArrayList<Position>();
	private Position healthBarPosition;

	public HudImage(IWrapper wrapper) {
		CharacterWrapper charac = (CharacterWrapper) wrapper;
		
		hudID = charac.getUniqeID();
		
		mapAnimation = AnimationMapFactory.create(40);
		animation = mapAnimation.get("idle");
		animation.start();
		
		//set character image depending on character
		Map<String, Animation> characterIcon = AnimationMapFactory.create(charac.getTypeID());
		icon = characterIcon.get("icon");
		
		
		upperLeftCornerPosition = new Position(0, 0);
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
		icon.draw(upperLeftCornerPosition.getX() + 10,
		upperLeftCornerPosition.getY() + 10);

		for (Map.Entry<Integer, SkillImage> entry : skills.entrySet()) {
			entry.getValue().render(gc, g);
		}
		healthBar.render(gc, g);
		
		
		
		renderLives(g);
	}

	private void renderLives(Graphics g) {
		if(lifeLimit){
			g.setColor(Color.green);
		}else{
			g.setColor(Color.red);
		}

		g.drawString(Integer.toString(lives), 20+ hudNr, 100);
		
		g.setColor(Color.black);
	}

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

	public Integer getUniqeID() {
		return hudID;
	}

	public void characterDied() {
		if(lifeLimit){
			lives = lives - 1;
		}else{
			lives++;
		}

	}

	public void gameOver() {
		hudIndex = 0;
		lifeLimit= false;
		skillPosition.clear();
	}
	
	public void setLives(int l){
		if(lifeLimit){
			lives = l;
		}
		
	}

}

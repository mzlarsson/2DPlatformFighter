package edu.chalmers.brawlbuddies.view;

import java.util.ArrayList;
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
	private static int hudIndex=0;
	private int hudLength = 274;
	private int hudHeight = 99;
	
	private Animation animation;
	private Map<String, Animation> mapAnimation;
	
	private Image characterIcon;
	private ArrayList<SkillImage> list = new ArrayList<SkillImage>();
	private Position upperLeftCornerPosition; 
	private Position characterImagePosition;
	private ArrayList<Position> skillPosition= new ArrayList<Position>();
	
	public HudImage(IWrapper skill){
		SkillWrapper skll = (SkillWrapper)skill;
		hudID= skll.getOwnerID();
		mapAnimation = AnimationMapFactory.create(40);
		animation = mapAnimation.get("idle");
		animation.start();
		upperLeftCornerPosition= new Position(hudIndex, 0);
		//TODO set characterIcon due to charactertypeid
		try {
			characterIcon = new Image(80,80);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		skillPosition.add(new Position(upperLeftCornerPosition.getX() + 100, upperLeftCornerPosition.getY()+60));
		skillPosition.add(new Position(upperLeftCornerPosition.getX() + 140, upperLeftCornerPosition.getY()+60));
		skillPosition.add(new Position(upperLeftCornerPosition.getX() + 180, upperLeftCornerPosition.getY()+60));
		skillPosition.add(new Position(upperLeftCornerPosition.getX() + 220, upperLeftCornerPosition.getY()+60));
		
		
		hudIndex = hudIndex + 300;
	}
	
	/**
	 * returns the currently correct position to place a skill on. 
	 * @return the position to place the next skill
	 */
	private Position getSkillPosition(){
		return skillPosition.get(list.size()-1);
	}
	
	/**
	 * add a skill to the skill list and give it the correct position.
	 * @param skill the skill to be added. 
	 */
	public void addSkill(SkillImage skill){
		list.add(skill);
		list.get(list.size()-1).place(getSkillPosition());
		
	}

	public void render(GameContainer gc, Graphics g) {
		animation.draw(upperLeftCornerPosition.getX(), upperLeftCornerPosition.getY());
		characterIcon.draw(upperLeftCornerPosition.getX()+10, upperLeftCornerPosition.getY()+ 10);
		for(SkillImage skill: list){
			skill.render(gc, g);
		}
	}

	public void update(IWrapper obj) {
		if(obj.getClass()== Health.class){
		//TODO fix health settings
		//life?
		}
		if(obj.getClass() == SkillWrapper.class){
		//TODO fix skillUse
		}
		if(obj.getClass()== CharacterWrapper.class){
			//TODO fix life
		}

	}


	public void update(IWrapper obj1, IWrapper obj2) {
		// TODO Auto-generated method stub
		
	}

}

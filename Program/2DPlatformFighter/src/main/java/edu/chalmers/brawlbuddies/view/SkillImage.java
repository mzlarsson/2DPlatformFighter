package edu.chalmers.brawlbuddies.view;

import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.services.factories.AnimationMapFactory;

public class SkillImage implements IDrawable {
	private Position position= new Position(0,0);
	private Map<String, Animation> mapAnimation;
	private Animation animation;
	private int ID = 90;
	
	public SkillImage(SkillWrapper skill){
		ID=skill.getOwnerID();
		mapAnimation = AnimationMapFactory.create(skill.getTypeID());
		animation = mapAnimation.get("idle");
		animation.start();
	}
	
	public void render(GameContainer gc, Graphics g) {
		animation.draw(position.getX(), position.getY());

	}
	public void place(Position p){
		position = p;
	}

	public void update(IWrapper obj1, IWrapper obj2) {
		// TODO Auto-generated method stu
		SkillWrapper skill = (SkillWrapper) obj1;
	
	}
	public int getID(){
		return ID;
	}

}

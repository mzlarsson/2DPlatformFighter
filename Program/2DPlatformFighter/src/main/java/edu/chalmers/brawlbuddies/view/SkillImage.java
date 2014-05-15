package edu.chalmers.brawlbuddies.view;

import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.Position;

public class SkillImage implements IDrawable {
	private Position position= new Position(0,0);
	private Map<String, Animation> mapAnimation;
	private Animation animation;
	private int ownerId;
	private int id;
	
	public SkillImage(SkillWrapper skill){
		ownerId=skill.getOwnerID();
		id = skill.getID();
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
		SkillWrapper skill = (SkillWrapper) obj1;
		//set skillinmage cooldown
	
	}
	public int getID(){
		return ownerId;
	}

	public Integer getUniqeID() {
		return id;
	}

}

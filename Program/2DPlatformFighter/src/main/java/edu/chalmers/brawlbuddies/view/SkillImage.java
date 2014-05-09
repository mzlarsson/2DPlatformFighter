package edu.chalmers.brawlbuddies.view;

import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.services.factories.AnimationMapFactory;

public class SkillImage implements IDrawable {
	private Position position;
	private Map<String, Animation> mapAnimation;
	private Animation animation;
	
	public SkillImage(SkillWrapper skill){
		mapAnimation = AnimationMapFactory.create(skill.getTypeID());
		//TODO set animation
	}
	
	public void render(GameContainer gc, Graphics g) {
		animation.draw();

	}

	public void update(IWrapper obj1, IWrapper obj2) {
		// TODO Auto-generated method stub
		
	}

}

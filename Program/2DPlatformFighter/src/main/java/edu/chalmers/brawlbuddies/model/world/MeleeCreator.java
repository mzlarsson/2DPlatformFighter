package edu.chalmers.brawlbuddies.model.world;

import java.util.List;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.skills.Effect;
import edu.chalmers.brawlbuddies.util.SlickUtil;

public class MeleeCreator {
	private List<Effect> effects;
	private int typeID;
	private Shape shape;
	private int creatorID = 0;
	public MeleeCreator(List<Effect> effects, Shape shape , int id) {
		this.effects = effects;
		this.shape = shape;
		this.typeID  = id;
	}
	public void setCreatorID(int a) {
		this.creatorID = a;
		if (effects != null) {
			for (int i = 0; i < effects.size(); i++) {
				effects.get(i).setCreatorID(a);
			}
		}
	}
	public Melee fire(ICharacter character){
		Position pos = character.getAim().getX() > 0? (character.getCenterPosition().subtract(this.shape.getWidth() / 2, 0)) 
				: character.getCenterPosition().add(this.shape.getWidth(), 0);
		return this.fire(character.getCenterPosition(), character.getAim());
	}
	public Melee fire(Position pos, Aim aim){
		Shape tmp = SlickUtil.copy(shape);
		tmp.setCenterX(pos.getX());
		tmp.setCenterY(pos.getY());
		return new Melee(this.shape , new Movement(new Velocity(aim, 1), true) , this.typeID, effects);
	}
	
	
	
	
}

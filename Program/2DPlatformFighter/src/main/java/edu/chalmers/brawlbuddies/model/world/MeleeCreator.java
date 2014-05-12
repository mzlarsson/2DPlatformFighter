package edu.chalmers.brawlbuddies.model.world;

import java.util.List;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.skills.IEffect;
import edu.chalmers.brawlbuddies.util.SlickUtil;

public class MeleeCreator {
	private List<IEffect> effects;
	private int typeID;
	private Shape shape;
	private int creatorID = 0;
	
	public MeleeCreator(List<IEffect> effects, Shape shape , int id) {
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
		return this.fire(character.getCenterPosition(), character.getAim());
	}
	
	public Melee fire(Position pos, Aim aim){
		Position newPos = aim.getX() > 0? (pos.add(this.shape.getWidth() / 2, 0)) 
				: pos.subtract(this.shape.getWidth(), 0);
		
		Shape tmp = SlickUtil.copy(shape);
		tmp.setCenterX(newPos.getX());
		tmp.setCenterY(newPos.getY());
		
		System.out.println(pos+"  "+newPos);
		return new Melee(tmp, new Movement(new Velocity(aim, 1), true) , this.typeID, effects);
	}
	
	
	
	
}

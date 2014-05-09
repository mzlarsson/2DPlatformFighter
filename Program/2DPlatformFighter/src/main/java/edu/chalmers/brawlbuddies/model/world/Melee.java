package edu.chalmers.brawlbuddies.model.world;

import java.util.List;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.skills.IEffect;
import edu.chalmers.brawlbuddies.model.world.Movement.Alignment;

public class Melee extends GameObject {
	private boolean destroyed = false;
	private List<IEffect> effects;
	private int typeID;
	
	public Melee(Shape shape, Movement move, int id , List<IEffect> effects){
		super(move, shape);
		this.effects = effects;
		this.typeID = id;
		
		
	}
	public boolean isDestroyed() {
		return false; 
	}

	public void onCollision(IGameObject object, Alignment alignment) {
		if(object instanceof Impassible){
			this.destroyed = true;
		}else{
			for(int i = 0 ; i < effects.size(); i++){
				effects.get(i).effect(this, object);
			}
		}
	}

	@Override
	public Position update(int delta) {
		destroyed = true;
		return null;
	}

	@Override
	public int getTypeID() {
		return typeID;
	}

}

package edu.chalmers.brawlbuddies.model.world;

import java.util.List;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.skills.IEffect;
import edu.chalmers.brawlbuddies.model.world.Movement.Alignment;
/**
 * A class to describe a Melee object
 * @author David Gustafsson
 * @version 1.0
 * 
 */
public class Melee extends GameObject {
	private boolean destroyed = false;
	private List<IEffect> effects;
	private int typeID;
	
	/**
	 * Creates a new Melee object with a shape, movement, ID and a list of effects.
	 * @param shape - the shape of the Melee hitbox
	 * @param move - the movement of the Melee
	 * @param id - the ID of the Melee 
	 * @param effects - the effects of the Melee
	 */
	public Melee(Shape shape, Movement move, int id , List<IEffect> effects){
		super(move, shape);
		this.effects = effects;
		this.typeID = id;
		
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isDestroyed() {
		return this.destroyed; 
	}
	

	/**
	 * {@inheritDoc}
	 */
	public void onCollision(IGameObject object, Alignment alignment){
		if(object != null){
			if(object instanceof Impassible){
				this.destroyed = true;
			}else{
				for(int i = 0 ; i < effects.size(); i++){
					effects.get(i).effect(this, object);
				}
			}
		}
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Position update(int delta) {
		this.destroyed = true;
		return this.getCenterPosition();
	}

	@Override
	public int getTypeID() {
		return typeID;
	}

}

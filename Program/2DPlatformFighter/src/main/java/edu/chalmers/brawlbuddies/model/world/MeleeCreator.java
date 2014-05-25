package edu.chalmers.brawlbuddies.model.world;

import java.util.List;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.skills.IEffect;
import edu.chalmers.brawlbuddies.util.SlickUtil;
/**
 * A class to hold and fire a specific Melee object
 * @author David Gustafsson
 *
 */
public class MeleeCreator {
	private List<IEffect> effects;
	private int typeID;
	private Shape shape;
	
	/**
	 * Creates a new MeleeCreator with a list of effects, a shape and a ID
	 * @param effects - the effects for fired Melee objects
	 * @param shape - the shape of the fired Melee objects
	 * @param id - the type ID of fired Melee object
	 */
	public MeleeCreator(List<IEffect> effects, Shape shape , int id) {
		this.effects = effects;
		this.shape = shape;
		this.typeID  = id;
	}
	/**
	 * Set the creatorID for the fired melee effects 
	 * @param id - the creatorID for the fired melee effects
	 */
	public void setCreatorID(int id) {
		if (effects != null) {
			for (int i = 0; i < effects.size(); i++) {
				effects.get(i).setCreatorID(id);
			}
		}
	}
	/**
	 * Creates a new Melee object with given data
	 * @param character - the character that fires it
	 * @return Melee - the fired Melee object
	 */
	public Melee fire(ICharacter character){
		return this.fire(character.getCenterPosition(), character.getAim());
	}
	
	/**
	 * Creates a new Melee object with a give position and a given aim
	 * @param pos - the given position
	 * @param aim - the given aim
	 * @return Melee - the new Melee object
	 */
	public Melee fire(Position pos, Aim aim){
		Position newPos = aim.getX() > 0? pos : pos.subtract(this.shape.getWidth(), 0);
		Shape tmp = SlickUtil.copy(shape);
		tmp.setX(newPos.getX());
		tmp.setCenterY(newPos.getY());
		return new Melee(tmp, new Movement(new Velocity(aim, 1), true) , this.typeID, effects);
	}
}

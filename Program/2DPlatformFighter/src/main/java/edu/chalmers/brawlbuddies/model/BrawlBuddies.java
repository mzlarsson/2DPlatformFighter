package edu.chalmers.brawlbuddies.model;

import edu.chalmers.brawlbuddies.model.world.GameMap;
import edu.chalmers.brawlbuddies.model.world.ICharacter;
import edu.chalmers.brawlbuddies.model.world.IGameObject;
import edu.chalmers.brawlbuddies.model.world.World;

/**
 * Holds the logic for the game.
 * 
 * @author Patrik Haar
 * @version 0.4
 * @revised Matz Larsson
 */
public class BrawlBuddies{

	private World world;
	
	public BrawlBuddies(){
		this(new World(new GameMap()));
	}

	public BrawlBuddies(World world) {
		this.world = world;
	}
	
	public int addCharacter(String name){
		return this.world.addCharacter(name);
	}
	
	/**
	 * Moves the character with given ID in the given direction.
	 * @param characterID The ID of the character to move
	 * @param dir The Direction to move the character.
	 */
	public void move(int characterID, Direction dir) {
		IGameObject object = world.getObjectById(characterID);
		if(object instanceof ICharacter){
			((ICharacter)object).move(dir);
		}
	}

	/**
	 * Make the given character jump.
	 * @param player The ID of the character to jump
	 */
	public void jump(int characterID) {
		IGameObject object = world.getObjectById(characterID);
		if(object instanceof ICharacter){
			((ICharacter)object).makeJump();
		}
	}
	
	/**
	 * Activates a skill of the character with the given characterID
	 * @param characterID The ID of the wanted character
	 * @param skillIndex The index of the skill
	 */
	public void activateSkill(int characterID, int skillIndex){
		IGameObject object = world.getObjectById(characterID);
		if(object instanceof ICharacter){
			((ICharacter)object).activateSkill(skillIndex);
		}
	}
	
	/**
	 * Sets the aim of the character with the given ID
	 * @param characterID The ID of the character
	 * @param aimPosition The position to aim at
	 * @param isRelative If the position is given relatively
	 */
	public void setAim(int characterID, Position aimPosition, boolean isRelative){
		IGameObject object = world.getObjectById(characterID);
		if(object instanceof ICharacter){
			((ICharacter)object).setAim(aimPosition, isRelative);
		}
	}

	/**
	 * Updates all the objects of the world
	 * @param delta Time since last update in milliseconds.
	 */
	public void update(int delta) {
		world.update(delta);
	}
}

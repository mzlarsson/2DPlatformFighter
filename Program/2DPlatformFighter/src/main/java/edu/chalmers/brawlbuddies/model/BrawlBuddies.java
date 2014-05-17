package edu.chalmers.brawlbuddies.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class BrawlBuddies implements IBrawlBuddies{

	private World world;
	private List<IGoal> goals;
	
	public BrawlBuddies(){
		this(new World(new GameMap()));
	}

	/**
	 * Creates the BrawlBuddies game with some rules.
	 * @param world The World the game will be using.
	 * @param timeLimit Time limit of the game in milliseconds, if lower than 1 it will not be used.
	 * If higher the game will end after the given time has passed.
	 * @param lifeLimit Life limit of the game, if lower than 1 it will not be used.
	 * 
	 */
	public BrawlBuddies(World world) {
		this.world = world;
		this.goals = new ArrayList<IGoal>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addGoal(IGoal goal) {
		goals.add(goal);
		for (IGameObject ch : this.world.getObjectsByType(ICharacter.class)) {
			((ICharacter)ch).addGameListener(goal);
		}
	}
	
	/**
	 * Returns all the IDs of the character in the order they were created
	 * @return IDs of all the character sorted by creation time
	 */
	public int[] getCharacterIDs(){
		List<IGameObject> characters = world.getObjectsByType(ICharacter.class);
		int[] id = new int[characters.size()];
		for(int i = 0; i<characters.size(); i++){
			id[i] = characters.get(i).getID();
		}
		
		return id;
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
		for (IGoal goal : goals) {
			goal.update(delta);
		}
		world.update(delta);
	}


}

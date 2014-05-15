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
public class BrawlBuddies implements IBrawlBuddies, GameListener{

	private World world;
	private List<GameListener> listeners;
	
	private boolean timeLimit;
	private int time;
	
	private boolean lifeLimit;
	private Map<Integer,Integer> lives;
	
	public BrawlBuddies(){
		this(new World(new GameMap()), 0, 0);
	}

	/**
	 * Creates the BrawlBuddies game with some rules.
	 * @param world The World the game will be using.
	 * @param timeLimit Time limit of the game in milliseconds, if lower than 1 it will not be used.
	 * If higher the game will end after the given time has passed.
	 * @param lifeLimit Life limit of the game, if lower than 1 it will not be used.
	 * 
	 */
	public BrawlBuddies(World world, int timeLimit, int lifeLimit) {
		this.world = world;
		this.listeners = new ArrayList<GameListener>();
		for (IGameObject ch : this.world.getObjectsByType(ICharacter.class)) {
			((ICharacter)ch).addGameListener(this);
		}
		if (timeLimit>0) {
			this.timeLimit = true;
			this.time = timeLimit;
		}
		if (lifeLimit>0) {
			this.lifeLimit = true;
			this.lives = new HashMap<Integer,Integer>();
			for(int playerID : getCharacterIDs()) {
				this.lives.put(playerID, lifeLimit);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addGameListener(GameListener gl) {
		listeners.add(gl);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void removeGameListener(GameListener gl) {
		listeners.remove(gl);
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
	 * If the game is on a timer it is reduced.
	 * @param delta The time to be subtracted in milliseconds.
	 * @return <code>true</code> if there is time left on the clock. <code>false</code> if not.
	 */
	private boolean decreaseGameTime(int delta) {
		if (timeLimit) {
			this.time -= delta;
			return this.time>0;
		}
		return true;
	}

	/**
	 * Will call the gameOver() method on all the listening classes.
	 */
	public void gameOver() {
		for(GameListener gl : listeners) {
			gl.gameOver();
		}
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public void playerKilled(int playerID) {
		if (lifeLimit) {
			lives.put(playerID, lives.get(playerID)-1);
			int pplAlive = 0;
			for(int i : lives.values()) {
				if (i>=0) {
					pplAlive += 1;
				}
			}
			if (pplAlive < 2) {
				gameOver();
			}
		}
	}
	
	/**
	 * Updates all the objects of the world
	 * @param delta Time since last update in milliseconds.
	 */
	public void update(int delta) {
		if (decreaseGameTime(delta)) {
			world.update(delta);
		} else {
			gameOver();
		}
	}


}

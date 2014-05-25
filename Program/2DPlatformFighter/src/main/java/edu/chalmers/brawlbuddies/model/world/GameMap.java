package edu.chalmers.brawlbuddies.model.world;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.model.Position;

/**
 * A class for representing a TileMap with spawn positions.
 * @author Patrik Haar
 * @version 0.1
 */
public class GameMap {

	private TiledMap map;
	private Position[] spawn;
	
	/**
	 * Create a new a GameMap
	 */
	public GameMap() {
		try {
			this.map = new TiledMap(Constants.TILEMAPS + "basic16Map.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.spawn = new Position[2];
		this.spawn[0] = new Position(200,200);
		this.spawn[1] = new Position(1400,200);
	}
	
	/**
	 * Creates a new GameMap with a map and spawn posititions to the characters
	 * @param map - the map of the GameMap
	 * @param spawns - the spawn positions
	 */
	public GameMap(TiledMap map, Position[] spawns) {
		this.map = map;
		this.spawn = spawns;
	}
	
	/**
	 * Get the TiledMap.
	 * @return The TileMap.
	 */
	public TiledMap getMap(){
		return map;
	}
	
	/**
	 * The number of spawn positions available.
	 * @return The spawn position count.
	 */
	public int getSpawnCount() {
		return spawn.length;
	}
	
	/**
	 * Get the spawn point with the given index.
	 * @param i The index of the spawn position.
	 * @return The Position of the of the spawn.
	 */
	public Position getSpawn(int i) {
		return spawn[i];
	}
	
	/**
	 * Get a random spawn position.
	 * @return A random spawn Position.
	 */
	public Position getRandomSpawn() {
		int index = (int) (Math.random()*spawn.length);
		return spawn[index];
	}

}

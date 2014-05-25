package edu.chalmers.brawlbuddies.model.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;
/**
 * A class to contain all the data of the world
 * @author Matz Larsson
 * @version 1.0
 *
 */
public class WorldData {
	
	private Map<Integer, IGameObject> objects;
	private GameMap gameMap = null;
	/**
	 * Creates a new world data object
	 */
	public WorldData() {
		objects = new TreeMap<Integer, IGameObject>();
	}
	/**
	 * Add a new game object to world
	 * @param object - the game object to be added
	 */
	public void add(IGameObject object){
		this.objects.put(object.getID(), object);
	}
	/**
	 * Set the map of the world
	 * @param gameMap - the new map to be used
	 */
	public void setMap(GameMap gameMap){
		//Reset old map
		for(Integer i : this.objects.keySet()){
			if(this.objects.get(i) instanceof MapObject){
				this.objects.remove(i);
			}
		}
		
		this.gameMap = gameMap;
		if(gameMap != null){
			TiledMap map = gameMap.getMap();
			Rectangle tmp = null;
			for (int i = 0; i < map.getObjectGroupCount(); i++) {
				for (int j = 0; j < map.getObjectCount(i); j++) {
					tmp = new Rectangle(map.getObjectX(i, j), map.getObjectY(i, j),
										map.getObjectWidth(i, j), map.getObjectHeight(i, j));
					this.add(new MapObject(tmp));
				}
			}
		}
	}
	public GameMap getGameMap(){
		return this.gameMap;
	}
	public void remove(IGameObject object){
		this.remove(object.getID());
	}
	
	public void remove(int objectID){
		this.objects.remove(objectID);
	}
	
	public Collection<IGameObject> getAllObjects(){
		return this.objects.values();
	}
	
	public IGameObject getObjectById(int objectID){
		return this.objects.get(objectID);
	}
	
	@SuppressWarnings("rawtypes")
	public List<IGameObject> getObjectsByType(Class c){
		return this.getObjectsByType(c, true);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<IGameObject> getObjectsByType(Class c, boolean match){
		List<IGameObject> objects = new ArrayList<IGameObject>();
		for(IGameObject obj : this.objects.values()){
			if((c.isAssignableFrom(obj.getClass())) == match){
				objects.add(obj);
			}
		}
		
		return objects;
	}

}

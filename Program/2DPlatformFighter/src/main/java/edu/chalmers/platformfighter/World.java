package edu.chalmers.platformfighter;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Class for holding all the data of the current game world.
 * It is also responsible for the calculation of collisions between objects in this world.
 * 
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class World {

	private Player[] players;
	private List<GameObject> objects;
	private TiledMap map;
	
	/**
	 * Creates a new game world for a given player set with a given game map as model.
	 * @param players The players to join the game
	 * @param map The map to fetch data from
	 */
	public World(Player[] players, GameMap map){
		this.players = players;
		this.objects = new ArrayList<GameObject>();
		this.map = map.getMap();
	}
	
	/**
	 * Sets up the world by copying all data from the map.
	 * @param map The GameMap to fetch data from
	 */
	private void setup(GameMap map){
		List<GameObject> obj = map.getAllObjects();
		for(int i = 0; i<obj.size(); i++){
			this.objects.add(obj.get(i).copy());
		}
	}
	
	/**
	 * Checks whether the given GameObject is in a valid position.
	 * @param obj The GameObject to test.
	 * @return <code>true</code> if object is allowed at its current position, <code>false</code> otherwise.
	 */
	public boolean isValid(GameObject obj){
		return getCollisionObject(obj) != null;
	}
	
	/**
	 * Calculates the best position to place a GameObject on depending on surroundings.
	 * @param go The GameObject to fix positioning with
	 * @param old The last previous known position
	 * @return The best position for the GO depending on surroundings.
	 */
	//FIXME temporary solution. This will not always work.
	public Position getValidPosition(GameObject obj, Position old){
		GameObject collider = getCollisionObject(obj);
		if(collider==null)return old.copy();
		Position tmp = new Position(obj.getX(), obj.getY());
		GameObject gCopy = obj.copy();
		
		gCopy.setCenterPosition(tmp.getX(), old.getY());
		if(!(collider.getShape().intersects(obj.getShape()) || collider.getShape().contains(obj.getShape()))){
			return new Position(tmp.getX(), old.getY());
		}else{
			gCopy.setCenterPosition(old.getX(), tmp.getY());
			if(!(collider.getShape().intersects(obj.getShape()) || collider.getShape().contains(obj.getShape()))){
				return new Position(old.getX(), tmp.getY());
			}else{
				return old.copy();
			}
		}
	}

	/**
	 * Calculates what object the given GameObject collides with.
	 * @param obj The GameObject to check.
	 * @return <code>null</code> if no collision detected, otherwise the object it collides with.
	 */
	private GameObject getCollisionObject(GameObject obj){
		for(int i = 0; i<objects.size(); i++){
			if(obj != objects.get(i) && collides(obj, objects.get(i))){
				return objects.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Checks whether two GameObjects collides in any matter.
	 * @param go0 The first GameObject
	 * @param go1 The second GameObject
	 * @return <code>true</code> if they collides in any matter, <code>false</code> otherwise.
	 */
	private boolean collides(GameObject go0, GameObject go1){
		Shape s0 = go0.getShape(), s1 = go1.getShape();
		return (s0.intersects(s1) || s0.contains(s1));
	}
	public TiledMap getMap(){
		return map;
	}
	

}

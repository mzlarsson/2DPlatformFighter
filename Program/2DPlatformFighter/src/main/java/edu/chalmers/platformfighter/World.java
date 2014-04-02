package edu.chalmers.platformfighter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Class for holding all the data of the current game world. It is also
 * responsible for the calculation of collisions between objects in this world.
 * 
 * @author Matz Larsson
 * @version 0.2
 * @revision by Lisa Lipkin
 * 
 */

public class World {

	private Player[] players;
	private List<GameObject> objects;
	private Shape[][] mapObjects;
	private TiledMap map;
	private int numberXTiles;
	private int numberYTiles;

	/**
	 * Creates a new game world for a given player set with a given game map as
	 * model.
	 * 
	 * @param players
	 *            The players to join the game
	 * @param map
	 *            The map to fetch data from
	 */
	public World(Player[] players, GameMap map) {
		this.players = players;
		this.objects = new ArrayList<GameObject>();
		this.map = map.getMap();
		this.numberXTiles = map.getMap().getWidth();
		this.numberYTiles = map.getMap().getHeight();
		setup();
	}

	/**
	 * Sets up the world by copying all Shapes from the different layers on the map.
	 */
	private void setup(){
		mapObjects = new Shape[map.getObjectGroupCount()][];
		for (int i=0; i<mapObjects.length ; i++) {
			mapObjects[i] = new Shape[map.getObjectCount(i)];
			for (int j=0; j<mapObjects[i].length; j++) {
				mapObjects[i][j] = new Rectangle(map.getObjectX(i, j)
												,map.getObjectY(i, j)
												,map.getObjectWidth(i, j)
												,map.getObjectHeight(i, j));
			}
		}
	}

	/**
	 * Calculates the best position to place the moving object when colliding
	 * with environmental tiles.
	 * 
	 * @param obj
	 *            the moving GameObject
	 * @param old
	 *            the old position of obj
	 * @return the best calculated position that is valid
	 */

	public Position getValidTilePosition(GameObject obj, Position old) {
		Position tmp = new Position(obj.getCenterX(), obj.getCenterY());

		if ((!isTileValid(new Position(old.getX(), tmp.getY())) && (old.getY()<tmp.getY()))) {
			obj.setMovementState(new Walking(obj, new Velocity(0,1000)));
		}
		if (!isTileValid(tmp)) {
			Position tmp2 = new Position(tmp.getX(), old.getY());
			if (isTileValid(tmp2)) {
				return tmp2;
			} else {
				Position tmp3 = new Position(old.getX(), tmp.getY());
				if (isTileValid(tmp3)) {
					return tmp3;
				} else {
					return old;
				}
			}

		} else {
			return tmp;
		}

	}

	/**
	 * Checks if the tile of the position on the environmentlayer is a valid
	 * tile to position the object.
	 * 
	 * @param p
	 *            position of a GameObject to be checed
	 * @return true if the tile of the position on the environmentlayer is 0,
	 *         that is, empty.
	 */
	public boolean isTileValid(Position p) {
		if (getPositionTileID(p) == 0) {
			
		}
		return getPositionTileID(p) == 0;
	}

	/**
	 * Calculates the TileId of the position
	 * 
	 * @param p
	 *            position of a GameObject
	 * @return the TileId of the positions tile
	 */
	private int getPositionTileID(Position p) {
		int x = getTilePositionX(p.getX());
		int y = getTilePositionY(p.getY());
		if (x < 0 || y < 0) {
			return -1;
		} else {
			return map.getTileId(x, y, map.getLayerIndex("environment"));
		}
	}

	/**
	 * calculates the position, that is, the tile number from left to right.
	 * 
	 * @param x
	 *            the x value from a position
	 * @return the tile number on the x-axis
	 */
	private int getTilePositionX(float x) {
		int tilePosition = (int) x / map.getTileWidth();
		if (0 > tilePosition || tilePosition > numberXTiles - 1) {
			return -1;
		} else {
			return tilePosition;
		}

	}

	/**
	 * calculates the tile number on the y-axis
	 * 
	 * @param y
	 *            the y value from a position
	 * @return the tile number on the y-axis
	 */
	private int getTilePositionY(float y) {
		int tilePosition = (int) y / map.getTileHeight();
		if (0 > tilePosition || tilePosition > numberYTiles - 1) {
			return -1;
		} else {
			return tilePosition;
		}
	}
	
	
	
	
	
	
	/**
	 * Checks whether the given GameObject is in a valid position.
	 * @param obj The GameObject to test.
	 * @return <code>true</code> if object is allowed at its current position,
	 *         <code>false</code> otherwise.
	 */
	public boolean isValid(GameObject obj) {
		return getCollisionShape(obj) == null;
	}

	/**
	 * Checks whether the given GameObject is in a valid position,
	 * which means not colliding with any objects in current layer.
	 * @param obj The GameObject to test.
	 * @param groupID The groupID to check for collisions in
	 * @return <code>true</code> if object is allowed at its current position,
	 *         <code>false</code> otherwise.
	 */
	public boolean isValid(GameObject obj, int groupID) {
		return getCollisionShape(obj, groupID) == null;
	}

	/**
	 * Calculates the best position to place a GameObject on depending on
	 * surroundings. Uses all layers.
	 * @param go The GameObject to fix positioning with
	 * @param old The last previous known position
	 * @return The best position for the GO depending on surroundings.
	 */
	public Position getValidPosition(GameObject obj, Position old) {
		return getValidPosition(obj, old, -1);
	}

	/**
	 * Calculates the best position to place a GameObject on depending on
	 * surroundings.
	 * @param go The GameObject to fix positioning with
	 * @param old The last previous known position
	 * @param groupID The groupID of the objects to check. 0 <= groupID < mapObjects.length
	 * @return The best position for the GO depending on surroundings.
	 */
	public Position getValidPosition(GameObject obj, Position old, int groupID){
		Shape collider = (groupID>=0 && groupID<mapObjects.length?
							getCollisionShape(obj, groupID):getCollisionShape(obj));
		if (collider == null){
			return obj.getPosition();
		}
		
		Position tmp = obj.getCenterPosition();
		GameObject gCopy = obj.copy();

		gCopy.setCenterPosition(tmp.getX(), old.getY());
		if (!(collider.intersects(gCopy.getShape()) || collider.contains(gCopy.getShape()))) {
			return new Position(tmp.getX(), old.getY());
		} else {
			gCopy.setCenterPosition(old.getX(), tmp.getY());
			if (!(collider.intersects(gCopy.getShape()) || collider.contains(gCopy.getShape()))) {
				return new Position(old.getX(), tmp.getY());
			} else {
				return old.copy();
			}
		}
	}

	/**
	 * Calculates what Shape the shape of the given GameObject collides with.
	 * If colliding with several items, the one with the lowest groupID will be returned.
	 * @param obj The GameObject to check.
	 * @return <code>null</code> if no collision detected, otherwise the object it collides with.
	 */
	private Shape getCollisionShape(GameObject obj){
		return getCollisionShape(obj.getShape());
	}

	/**
	 * Calculates what Shape the shape of the given GameObject collides with.
	 * If colliding with several items, the one with the lowest objectID will be returned.
	 * @param obj The GameObject to check.
	 * @param groupID The groupID to check collisions in
	 * @return <code>null</code> if no collision detected, otherwise the object it collides with.
	 */
	private Shape getCollisionShape(GameObject obj, int groupID){
		return getCollisionShape(obj.getShape(), groupID);
	}

	/**
	 * Calculates what Shape the given Shape collides with. If colliding with several items,
	 * the one with the lowest groupID will be returned.
	 * @param shape The Shape to check.
	 * @return <code>null</code> if no collision detected, otherwise the object it collides with.
	 */
	private Shape getCollisionShape(Shape shape){
		Shape tmp = null;
		for(int i = 0; i<mapObjects.length; i++){
			tmp = getCollisionShape(shape, i);
			if(tmp != null){
				return tmp;
			}
		}
		
		return null;
	}
	
	/**
	 * Calculates what Shape the given Shape collides with. If colliding with several items,
	 * the one with the lowest objectID will be returned.
	 * @param shape The Shape to check.
	 * @param groupID The groupID to check collisions in
	 * @return <code>null</code> if no collision detected, otherwise the object it collides with.
	 */
	private Shape getCollisionShape(Shape shape, int groupID) {
		if(groupID>=0 && groupID<mapObjects.length){
			for (int i = 0; i < mapObjects[groupID].length; i++) {
				if (collides(shape, mapObjects[groupID][i])) {
					return mapObjects[groupID][i];
				}
			}
		}
		return null;
	}

	/**
	 * Checks whether two Shapes collides in any matter.
	 * @param go0 The first Shape
	 * @param go1 The second Shape
	 * @return <code>true</code> if they collides in any matter,
	 *         <code>false</code> otherwise.
	 */
	private boolean collides(Shape s1, Shape s2) {
		return (s1.intersects(s2) || s1.contains(s2));
	}

	public TiledMap getMap() {
		return map;
	}

}

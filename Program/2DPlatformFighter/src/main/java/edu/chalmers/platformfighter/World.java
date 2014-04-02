package edu.chalmers.platformfighter;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Point;
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
	}

	/**
	 * Sets up the world by copying all data from the map.
	 * 
	 * @param map
	 *            The GameMap to fetch data from
	 */
	private void setup(GameMap map) {
		List<GameObject> obj = map.getAllObjects();
		for (int i = 0; i < obj.size(); i++) {
			this.objects.add(obj.get(i).copy());
		}
	}

	/**
	 * Checks whether the given GameObject is in a valid position.
	 * 
	 * @param obj
	 *            The GameObject to test.
	 * @return <code>true</code> if object is allowed at its current position,
	 *         <code>false</code> otherwise.
	 */
	public boolean isValid(GameObject obj) {

		return getCollisionObject(obj) != null;
	}

	/**
	 * Calculates the best position to place a GameObject on depending on
	 * surroundings.
	 * 
	 * @param go
	 *            The GameObject to fix positioning with
	 * @param old
	 *            The last previous known position
	 * @return The best position for the GO depending on surroundings.
	 */
	// FIXME temporary solution. This will not always work.
	public Position getValidPosition(GameObject obj, Position old) {
		GameObject collider = getCollisionObject(obj);
		if (collider == null)
			return old.copy();
		Position tmp = new Position(obj.getX(), obj.getY());
		GameObject gCopy = obj.copy();

		gCopy.setCenterPosition(tmp.getX(), old.getY());
		if (!(collider.getShape().intersects(obj.getShape()) || collider
				.getShape().contains(obj.getShape()))) {
			return new Position(tmp.getX(), old.getY());
		} else {
			gCopy.setCenterPosition(old.getX(), tmp.getY());
			if (!(collider.getShape().intersects(obj.getShape()) || collider
					.getShape().contains(obj.getShape()))) {
				return new Position(old.getX(), tmp.getY());
			} else {
				return old.copy();
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
	 * Calculates what object the given GameObject collides with.
	 * 
	 * @param obj
	 *            The GameObject to check.
	 * @return <code>null</code> if no collision detected, otherwise the object
	 *         it collides with.
	 */
	private GameObject getCollisionObject(GameObject obj) {
		for (int i = 0; i < objects.size(); i++) {
			if (obj != objects.get(i) && collides(obj, objects.get(i))) {
				return objects.get(i);
			}
		}
		return null;
	}

	/**
	 * Checks whether two GameObjects collides in any matter.
	 * 
	 * @param go0
	 *            The first GameObject
	 * @param go1
	 *            The second GameObject
	 * @return <code>true</code> if they collides in any matter,
	 *         <code>false</code> otherwise.
	 */
	private boolean collides(GameObject go0, GameObject go1) {
		Shape s0 = go0.getShape(), s1 = go1.getShape();
		return (s0.intersects(s1) || s0.contains(s1));
	}

	public TiledMap getMap() {
		return map;
	}

}

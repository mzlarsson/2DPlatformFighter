package edu.chalmers.brawlbuddies.model.world;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

import edu.chalmers.brawlbuddies.controller.Player;
import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.util.SlickUtil;

/**
 * Class for holding all the data of the current game world. It is also
 * responsible for the calculation of collisions between objects in this world.
 * 
 * @author Matz Larsson
 * @version 0.2
 * @revisied Lisa Lipkin
 * @revisied Patrik Haar
 */

public class World {
	// Used to set playerId
	private int nbrOfPlayers = 0;
	
	private Player[] players;
	private List<GameObject> objects;
	private Shape[][] mapObjects;
	private List<Projectile> projectiles = new ArrayList<Projectile>();
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
		//TODO Temporary solution with player to set playerId to characters
		for(int i = 0 ; i < players.length; i++){
			nbrOfPlayers += 1;
			players[i].getCharacter().setPlayerId(nbrOfPlayers);
		}
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
	 *            The moving GameObject
	 * @param newPos
	 *            The new position of obj
	 * @return The best calculated position that is valid
	 */
	public Position getValidTilePosition(GameObject obj, Position newPos) {
		Position newGoodPos = newPos.copy();
		// Determines which way the object is moving to decide which corners to use.
		boolean movingLeft = obj.getCenterX()>newPos.getX();
		boolean movingUp = obj.getCenterY()>newPos.getY();
		int oldXTile = getTilePositionX( movingLeft ? obj.getShape().getMinX() : obj.getShape().getMaxX() );
		int newXTile = getTilePositionX( newPos.getX()+ (movingLeft ? -obj.getShape().getWidth()/2 - 1: obj.getShape().getWidth()/2 + 1) );
		int oldYTile = getTilePositionY( movingUp ? obj.getShape().getMinY() : obj.getShape().getMaxY() );
		int newYTile = getTilePositionY( newPos.getY()+ (movingUp ? -obj.getShape().getHeight()/2 - 1 :obj.getShape().getHeight()/2 + 1) );
		int xdiff = newXTile<0 ? 1 : Math.abs(newXTile-oldXTile);
		int ydiff = newYTile<0 ? 1 : Math.abs(newYTile-oldYTile);
		
		// Checks if object has entered a new tile.
		if (xdiff!=0 || ydiff!=0) {
			Direction movedir = Direction.getDirection(newXTile-oldXTile, newYTile-oldYTile);
			Shape shape = SlickUtil.copy(obj.getShape());
			boolean xFound = xdiff==0;
			boolean yFound = ydiff==0;
			for (int i=0; i<Math.max(xdiff, ydiff); i++) {
				if (!isNextTileValid(shape, movedir)) {
					if (!xFound && !isNextTileValid(shape, movedir.getXDirection())) {
						xFound = true;
						// If obj collides after just one tile it uses it's old x-value to prevent shimmering edges.
						if (i==0) {
							newGoodPos.set(obj.getCenterX(), newGoodPos.getY());
						// Keeps the Y-pos and sets the X-pos to just before the collision.
						} else {
							newGoodPos.set(movedir.getX()<0? shape.getCenterX()+shape.getWidth()/2 - shape.getCenterX()%map.getTileWidth()
									: shape.getCenterX()-shape.getWidth()/2 + (map.getTileWidth()-shape.getCenterX()%map.getTileWidth() - 1)
									, newGoodPos.getY());
						}
						movedir = movedir.getYDirection();
						obj.onCollision(null, Movement.Alignment.HORIZONTAL);
					}
					if (!yFound && !isNextTileValid(shape, movedir.getYDirection())) {
						yFound = true;
						// If obj collides after just one tile it uses it's old x-value to prevent shimmering edges.
						if (i==0) {
							newGoodPos.set(newGoodPos.getX(), obj.getCenterY());
						// Keeps the X-pos and sets the Y-pos to just before the collision.
						} else {
							newGoodPos.set(newGoodPos.getX()
									, movedir.getY()<0? shape.getCenterY()+shape.getHeight()/2 - shape.getCenterY()%map.getTileHeight() - map.getTileHeight()
									: shape.getCenterY()-shape.getHeight()/2 + (map.getTileHeight()-shape.getCenterY()%map.getTileHeight()) + map.getTileHeight() - 1);
						}
						movedir = movedir.getXDirection();
						obj.onCollision(null, Movement.Alignment.VERTICAL);
					}
				}
				// If no collision occurred the testing shape will move one tile on the x-axis.
				if (!xFound) {
					shape.setCenterX(shape.getCenterX() + map.getTileWidth()*movedir.getX());
					xFound = i==xdiff;
				}
				// If no collision occurred the testing shape will move one tile on the y-axis.
				if (!yFound) {
					shape.setCenterY(shape.getCenterY() + map.getTileHeight()*movedir.getY());
					yFound = i==ydiff;
				}
				// On collision on both x- and y- axis the loop is aborted and the calculated values are used.
				if (xFound && yFound) {
					break;
				}
			}
		}
		return newGoodPos;
	}
	
	/**
	 * Checks if the Shape is occupying valid tiles.
	 * @param shape The Shape to test.
	 * @return true if position is valid.
	 */
	private boolean isTileValid(Shape shape) {
		int minX = getTilePositionX(shape.getMinX());
		int maxX = getTilePositionX(shape.getMaxX());
		int minY = getTilePositionY(shape.getMinY());
		int maxY = getTilePositionY(shape.getMaxY());
		for (int i=minX; i<=maxX; i++ ) {
			for (int j=minY; j<=maxY; j++ ) {
				if (!isTileValid(i,j)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Checks if the tiles adjacent to the shape in the given direction is valid.
	 * @param shape The moving Shape.
	 * @param dir The Direction the Shape is moving.
	 * @return true if all the tiles adjacent to the Shape is valid in the given direction.
	 */
	private boolean isNextTileValid(Shape shape, Direction dir) {
		boolean xmov = dir.getX()!=0;
		boolean ymov = dir.getY()!=0;
		// Checks if the next x-tiles are valid.
		if (xmov) {
			int height = getTilePositionY(shape.getMaxY()) - getTilePositionY(shape.getMinY()) + 1;
			int x = getTilePositionX(dir.getX()<0?shape.getMinX():shape.getMaxX()) + dir.getX();
			int y = getTilePositionY(shape.getMinY());
			for (int i=0; i<height; i++) {
				if (!isTileValid(x, y+i)) {
					return false;
				}
			}
		}
		// Checks if the next y-tiles are valid.
		if (ymov) {
			int width = getTilePositionX(shape.getMaxX()) - getTilePositionX(shape.getMinX()) + 1;
			int x = getTilePositionX(shape.getMinX());
			int y = getTilePositionY(dir.getY()<0?shape.getMinY():shape.getMaxY()) + dir.getY();
			for (int i=0; i<width; i++) {
				if (!isTileValid(x+i, y)) {
					return false;
				}
			}
		}
		// Checks the missed tile on diagonal movement.
		if (xmov&&ymov) {
			int x = getTilePositionX(dir.getX()<0?shape.getMinX():shape.getMaxX()) + dir.getX();
			int y = getTilePositionY(dir.getY()<0?shape.getMinY():shape.getMaxY()) + dir.getY();
			if (!isTileValid(x, y)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if the tile of the position on the environmentlayer is a valid
	 * tile to position the object.
	 * 
	 * @param p
	 *            position of a GameObject to be checked
	 * @return true if the tile of the position on the environmentlayer is 0,
	 *         that is, empty.
	 */
	public boolean isTileValid(Position p) {
		return getPositionTileID(p) == 0;
	}

	/**
	 * Checks if the tile of the coordinate on the environmentlayer is a valid
	 * tile for objects.
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @return true if the tile of the position on the environmentlayer is 0,
	 *         that is, empty.
	 */
	public boolean isTileValid(int x, int y) {
		return getTileID(x,y) == 0;
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
		return getTileID(x,y);
	}

	/**
	 * Returns the Tile ID of the Tile on the given coordinate.
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @return The Tile ID if valid, -1 if out of bounds.
	 */
	private int getTileID(int x, int y) {
		if (x < 0 || y < 0 || x > numberXTiles-1 || y > numberYTiles-1) {
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
		return getCollisionShapes(obj).isEmpty();
	}

	/**
	 * Checks whether the given GameObject is in a valid position,
	 * which means not colliding with any objects in current layer.
	 * @param obj The GameObject to test.
	 * @param groupID The groupID to check for collisions in
	 * @return <code>true</code> if object is allowed at its current position,
	 *         <code>false</code> otherwise.
	 */
	public boolean isValid(GameObject obj, int groupID){
		return getCollisionShapes(obj, groupID).isEmpty();
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
		List<Shape> colliders = (groupID>=0 && groupID<mapObjects.length?
							getCollisionShapes(obj, groupID):getCollisionShapes(obj));
		
		//No colliders?
		if (colliders.isEmpty()){
			return obj.getCenterPosition();
		}
		
		//Copy gameobject position and gameobject
		Position tmp = obj.getCenterPosition().copy();
		GameObject gCopy = obj.copy();

		//Check movement only along x-axis
		gCopy.setCenterPosition(tmp.getX(), old.getY());
		if (!collides(gCopy.getShape(), colliders)){
			obj.onCollision(null, Movement.Alignment.VERTICAL);
			return new Position(tmp.getX(), old.getY());
		} else {
			//Check movement only along y-axis
			gCopy.setCenterPosition(old.getX(), tmp.getY());
			if (!collides(gCopy.getShape(), colliders)){
				obj.onCollision(null, Movement.Alignment.HORIZONTAL);
				return new Position(old.getX(), tmp.getY());
			} else{
				obj.onCollision(null, Movement.Alignment.BOTH);
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
	private List<Shape> getCollisionShapes(GameObject obj){
		return getCollisionShapes(obj.getShape());
	}

	/**
	 * Calculates what Shape the shape of the given GameObject collides with.
	 * If colliding with several items, the one with the lowest objectID will be returned.
	 * @param obj The GameObject to check.
	 * @param groupID The groupID to check collisions in
	 * @return <code>null</code> if no collision detected, otherwise the object it collides with.
	 */
	private List<Shape> getCollisionShapes(GameObject obj, int groupID){
		return getCollisionShapes(obj.getShape(), groupID);
	}

	/**
	 * Calculates what Shape the given Shape collides with. If colliding with several items,
	 * the one with the lowest groupID will be returned.
	 * @param shape The Shape to check.
	 * @return <code>null</code> if no collision detected, otherwise the object it collides with.
	 */
	private List<Shape> getCollisionShapes(Shape shape){
		List<Shape> collisions = new ArrayList<Shape>();
		for(int i = 0; i<mapObjects.length; i++){
			collisions.addAll(getCollisionShapes(shape, i));
		}
		
		return collisions;
	}
	
	/**
	 * Calculates what Shape the given Shape collides with. If colliding with several items,
	 * the one with the lowest objectID will be returned.
	 * @param shape The Shape to check.
	 * @param groupID The groupID to check collisions in
	 * @return <code>null</code> if no collision detected, otherwise the object it collides with.
	 */
	private List<Shape> getCollisionShapes(Shape shape, int groupID) {
		List<Shape> collisions = new ArrayList<Shape>();
		if(groupID>=0 && groupID<mapObjects.length){
			for (int i = 0; i < mapObjects[groupID].length; i++) {
				if (collides(shape, mapObjects[groupID][i])){
					collisions.add(mapObjects[groupID][i]);
				}
			}
		}
		
		return collisions;
	}

	/**
	 * Checks whether two Shapes collides in any matter.
	 * @param go0 The first Shape
	 * @param go1 The second Shape
	 * @return <code>true</code> if they collides in any matter,
	 *         <code>false</code> otherwise.
	 */
	private boolean collides(Shape s1, List<Shape> shapes) {
		for(int i = 0; i<shapes.size(); i++){
			if(collides(s1, shapes.get(i))){
				return true;
			}
		}
		return false;
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

package edu.chalmers.brawlbuddies.model.world;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.world.Movement.Alignment;
import edu.chalmers.brawlbuddies.util.SlickUtil;

/**
 * Class for holding all the data of the current game world. It is also
 * responsible for the calculation of collisions between objects in this world.
 * 
 * @author Matz Larsson
 * @version 0.4
 * @revised Lisa Lipkin
 * @revised Patrik Haar
 */

public class World implements CreatorListener{

	private WorldData data;
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
	public World(GameMap map) {
		//Set up world data
		this.data = new WorldData();
		this.data.setMap(map);
		Creator.getInstance().addListener(this);
		
		//Set up own variables
		this.map = map.getMap();
		this.numberXTiles = map.getMap().getWidth();
		this.numberYTiles = map.getMap().getHeight();
	}
	
	public int addCharacter(String name){
		ICharacter character = CharacterFactory.create(name, 200, 200);
		this.add(character);
		return character.getID();
	}
	/**
	 * Adds a gameobject to the World
	 */
	public void add(IGameObject obj){
		this.data.add(obj);
	}
	
	/**
	 * Remove a Gameobject from the world
	 * @param obj - the gameobject to be removed
	 */
	public void remove(IGameObject obj){
		this.data.remove(obj);
	}
	
	/**
	 * Get a gameobject by its ID from World
	 * @param objectID - the ID of the object
	 * @return IGameObject - the gameobject with the ID
	 */
	public IGameObject getObjectById(int objectID){
		return this.data.getObjectById(objectID);
	}
	
	@SuppressWarnings("rawtypes")
	/**
	 * Get a list of object from world that only includes a class
	 * @param type - the class of the objects
	 * @return List<IGameObject> - a list of game objects
	 */
	public List<IGameObject> getObjectsByType(Class type){
		return this.data.getObjectsByType(type);
	}
	
	@SuppressWarnings("rawtypes")
	/**
	 * Get a list of object from world that only includes or excludes a particular class 
	 * depending on match
	 * @param type - the class that shall be included or excluded
	 * @param match - true if the list shall only include a particular class
	 * @return List<IGameObject> - a list of game objects
	 */
	public List<IGameObject> getObjectsByType(Class type, boolean match){
		return this.data.getObjectsByType(type, match);
	}

	/**
	 * Returns the current map
	 * @return The current map
	 */
	public TiledMap getMap() {
		return this.map;
	}

	/**
	 * Calculates the best position to place the moving object when colliding
	 * with environmental tiles.
	 * 
	 * @param obj The moving GameObject
	 * @param newPos The new center position of obj
	 * @return The best calculated position that is valid
	 */
	public Position getValidTilePosition(IGameObject obj, Position newPos) {
		if(newPos == null){
			return obj.getCenterPosition();
		}
		
		Position newGoodPos = newPos.copy();
		// Determines which way the object is moving to decide which corners to
		// use.
		Position centerPos = obj.getCenterPosition();
		boolean movingLeft = centerPos.getX() > newPos.getX();
		boolean movingUp = centerPos.getY() > newPos.getY();
		int oldXTile = getTilePositionX(movingLeft ? obj.getShape().getMinX()
				: obj.getShape().getMaxX());
		int newXTile = getTilePositionX(newPos.getX()
				+ (movingLeft ? -obj.getShape().getWidth() / 2 - 1 : obj
						.getShape().getWidth() / 2 + 1));
		int oldYTile = getTilePositionY(movingUp ? obj.getShape().getMinY()
				: obj.getShape().getMaxY());
		int newYTile = getTilePositionY(newPos.getY()
				+ (movingUp ? -obj.getShape().getHeight() / 2 - 1 : obj
						.getShape().getHeight() / 2 + 1));
		int xdiff = newXTile<0&&movingLeft ? -numberXTiles : newXTile<0&&!movingLeft ? numberXTiles : newXTile - oldXTile;
		int ydiff = newYTile<0&&movingUp ? -numberYTiles : newYTile<0&&!movingUp ? numberYTiles : newYTile - oldYTile;

		// Checks if object has entered a new tile.
		if (xdiff != 0 || ydiff != 0) {
			Direction movedir = Direction.getDirection(xdiff, ydiff);
			xdiff = Math.abs(xdiff);
			ydiff = Math.abs(ydiff);
			Shape shape = SlickUtil.copy(obj.getShape());
			boolean xFound = xdiff == 0;
			boolean yFound = ydiff == 0;
			for (int i = 0; i < Math.max(xdiff, ydiff); i++) {
				if (!isNextTileValid(shape, movedir)) {
					if (!xFound	&& !isNextTileValid(shape, movedir.getXDirection())) {
						xFound = true;
						// If obj collides after just one tile it uses it's old
						// x-value to prevent shimmering edges.
						if (i == 0) {
							newGoodPos.set(obj.getCenterPosition().getX(), newGoodPos.getY());
							// Keeps the Y-pos and sets the X-pos to just before
							// the collision.
						} else {
							newGoodPos.set(
									movedir.getX() < 0 ? shape.getCenterX()
											+ shape.getWidth() / 2
											- shape.getCenterX()
											% map.getTileWidth() : shape
											.getCenterX()
											- shape.getWidth()
											/ 2
											+ (map.getTileWidth()
													- shape.getCenterX()
													% map.getTileWidth() - 1),
									newGoodPos.getY());
						}
						movedir = movedir.getYDirection();
						obj.onCollision(null, Movement.Alignment.HORIZONTAL);
					}
					if (!yFound	&& !isNextTileValid(shape, movedir.getYDirection())) {
						yFound = true;
						// If obj collides after just one tile it uses it's old
						// x-value to prevent shimmering edges.
						if (i == 0) {
							newGoodPos.set(newGoodPos.getX(), obj.getCenterPosition().getY());
							// Keeps the X-pos and sets the Y-pos to just before
							// the collision.
						} else {
							newGoodPos.set(
									newGoodPos.getX(),
									movedir.getY() < 0 ? shape.getCenterY()
											+ shape.getHeight() / 2
											- shape.getCenterY()
											% map.getTileHeight()
											- map.getTileHeight() : shape
											.getCenterY()
											- shape.getHeight()
											/ 2
											+ (map.getTileHeight() - shape
													.getCenterY()
													% map.getTileHeight())
											+ map.getTileHeight() - 1);
						}
						movedir = movedir.getXDirection();
						obj.onCollision(null, Movement.Alignment.VERTICAL);
					}
				}
				// If no collision occurred the testing shape will move one tile
				// on the x-axis.
				if (!xFound) {
					shape.setCenterX(shape.getCenterX() + map.getTileWidth()
							* movedir.getX());
					xFound = i == xdiff;
				}
				// If no collision occurred the testing shape will move one tile
				// on the y-axis.
				if (!yFound) {
					shape.setCenterY(shape.getCenterY() + map.getTileHeight()
							* movedir.getY());
					yFound = i == ydiff;
				}
				// On collision on both x- and y- axis the loop is aborted and
				// the calculated values are used.
				if (xFound && yFound) {
					break;
				}
			}
		}
		return newGoodPos;
	}


	/**
	 * Checks if the tiles adjacent to the shape in the given direction is
	 * valid.
	 * 
	 * @param shape
	 *            The moving Shape.
	 * @param dir
	 *            The Direction the Shape is moving.
	 * @return true if all the tiles adjacent to the Shape is valid in the given
	 *         direction.
	 */
	private boolean isNextTileValid(Shape shape, Direction dir) {
		boolean xmov = dir.getX() != 0;
		boolean ymov = dir.getY() != 0;
		// Checks if the next x-tiles are valid.
		if (xmov) {
			int height = getTilePositionY(shape.getMaxY())
					- getTilePositionY(shape.getMinY()) + 1;
			int x = getTilePositionX(dir.getX() < 0 ? shape.getMinX() : shape
					.getMaxX()) + dir.getX();
			int y = getTilePositionY(shape.getMinY());
			for (int i = 0; i < height; i++) {
				if (!isTileValid(x, y + i)) {
					return false;
				}
			}
		}
		// Checks if the next y-tiles are valid.
		if (ymov) {
			int width = getTilePositionX(shape.getMaxX())
					- getTilePositionX(shape.getMinX()) + 1;
			int x = getTilePositionX(shape.getMinX());
			int y = getTilePositionY(dir.getY() < 0 ? shape.getMinY() : shape
					.getMaxY()) + dir.getY();
			for (int i = 0; i < width; i++) {
				if (!isTileValid(x + i, y)) {
					return false;
				}
			}
		}
		// Checks the missed tile on diagonal movement.
		if (xmov && ymov) {
			int x = getTilePositionX(dir.getX() < 0 ? shape.getMinX() : shape
					.getMaxX()) + dir.getX();
			int y = getTilePositionY(dir.getY() < 0 ? shape.getMinY() : shape
					.getMaxY()) + dir.getY();
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
	 * 
	 * @param x
	 *            The x-coordinate.
	 * @param y
	 *            The y-coordinate.
	 * @return true if the tile of the position on the environmentlayer is 0,
	 *         that is, empty.
	 */
	public boolean isTileValid(int x, int y) {
		return getTileID(x, y) == 0;
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
		return getTileID(x, y);
	}

	/**
	 * Returns the Tile ID of the Tile on the given coordinate.
	 * 
	 * @param x
	 *            The x-coordinate.
	 * @param y
	 *            The y-coordinate.
	 * @return The Tile ID if valid, -1 if out of bounds.
	 */
	private int getTileID(int x, int y) {
		if (x < 0 || y < 0 || x > numberXTiles - 1 || y > numberYTiles - 1) {
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
		if (x<0 || tilePosition > numberXTiles - 1) {
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
		if (y<0 || tilePosition > numberYTiles - 1) {
			return -1;
		} else {
			return tilePosition;
		}
	}

	/**
	 * Calculates a Polygon covering the GameObject current Shape´s Position and
	 * new Position. OBS Will handle the GameObjects Shape as a Rectangle.
	 * 
	 * @param obj
	 *            The moving GameObject.
	 * @param newPos
	 *            The new Position for the GameObject.
	 * @return A Polygon covering both the old and new Position.
	 */
	private Polygon getConnectedShape(IGameObject obj, Position newPos) {
		if(newPos == null){
			return SlickUtil.shapeToPolygon(obj.getShape());
		}
		
		// Determines which way the object is moving to decide which corners to
		// use.
		Position centerPos = obj.getCenterPosition();
		boolean movingLeft = centerPos.getX() > newPos.getX();
		boolean movingUp = centerPos.getY() > newPos.getY();

		float width = obj.getShape().getWidth(); // For convenience
		float height = obj.getShape().getHeight(); // For convenience

		// The x-/y values for the corners on the old (A) and new (B) positions.
		float xA = movingLeft ? obj.getShape().getMaxX() : obj.getShape()
				.getMinX();
		float yA = movingUp ? obj.getShape().getMaxY() : obj.getShape()
				.getMinY();
		float xB = movingLeft ? newPos.getX() - width / 2 - 1: newPos.getX()
				+ width / 2 + 1;
		float yB = movingUp ? newPos.getY() - height / 2 - 1: newPos.getY()
				+ height / 2 + 1;

		float[] polypoints = new float[12];

		polypoints[0] = xA;
		polypoints[1] = yA;
		polypoints[2] = xA;
		polypoints[3] = movingUp ? yA - height : yA + height;
		polypoints[4] = movingLeft ? xB + width : xB - width;
		polypoints[5] = yB;
		polypoints[6] = xB;
		polypoints[7] = yB;
		polypoints[8] = xB;
		polypoints[9] = movingUp ? yB + height : yB - height;
		polypoints[10] = movingLeft ? xA - width : xA + width;
		polypoints[11] = yA;

		return new Polygon(polypoints);
	}

	/**
	 * Calculates the best position to place a GameObject on depending on
	 * collisions with the provided list.
	 * 
	 * @param obj
	 *            The GameObject to fix positioning with.
	 * @param newPos
	 *            The new position to be tested.
	 * @param list
	 *            The list of GameObjects to check collisions with.
	 * @return The best position for the GO depending on surroundings.
	 */
	public Position getValidPosition(IGameObject obj, Position newPos, List<IGameObject> list) {
		Position newGoodPos = newPos.copy();
		Polygon poly = getConnectedShape(obj, newPos);
		List<IGameObject> collisions = getCollisions(poly, list);

		if (collisions.size() != 0) {
			Position centerPos = obj.getCenterPosition();
			boolean movingLeft = centerPos.getX() > newPos.getX();
			boolean movingUp = centerPos.getY() > newPos.getY();
			Alignment align = getCollisionAlignment(obj.getShape(), newPos,
					collisions.get(0).getShape());
			IGameObject vertColl = align == Alignment.BOTH ? collisions.get(0)
					: align == Alignment.VERTICAL ? collisions.get(0) : null;
			IGameObject horiColl = align == Alignment.BOTH ? collisions.get(0)
					: align == Alignment.HORIZONTAL ? collisions.get(0) : null;

			if (collisions.size() > 1) {
				// Determines which way the object is moving to decide which
				// object it hit first.
				for (int i = 1; i < collisions.size(); i++) {
					Shape tmp = collisions.get(i).getShape();
					Alignment tmpAlign = getCollisionAlignment(obj.getShape(),
							newPos, tmp);

					if (vertColl == null
							&& (tmpAlign == Alignment.BOTH || tmpAlign == Alignment.VERTICAL)) {
						vertColl = collisions.get(i);
						if (horiColl != null) {
							align = Alignment.BOTH;
						} else {
							align = Alignment.VERTICAL;
						}
					}
					if (horiColl == null
							&& (tmpAlign == Alignment.BOTH || tmpAlign == Alignment.HORIZONTAL)) {
						horiColl = collisions.get(i);
						if (vertColl != null) {
							align = Alignment.BOTH;
						} else {
							align = Alignment.HORIZONTAL;
						}
					}
					if (tmpAlign == Alignment.BOTH
							|| tmpAlign == Alignment.VERTICAL) {
						// If this shape is closer to the original position it
						// replaces the old.
						if ((movingUp ? vertColl.getShape().getMaxY()
								- tmp.getMaxY() : tmp.getMinY()
								- vertColl.getShape().getMinY()) < 0) {
							vertColl = collisions.get(i);
						}
					}
					if (tmpAlign == Alignment.BOTH
							|| tmpAlign == Alignment.HORIZONTAL) {
						// If this shape is closer to the original position it
						// replaces the old.
						if ((movingLeft ? vertColl.getShape().getMaxX()
								- tmp.getMaxX() : tmp.getMinX()
								- vertColl.getShape().getMinX()) < 0) {
							horiColl = collisions.get(i);
						}
					}
				}
			}
			if (align == Alignment.BOTH || align == Alignment.HORIZONTAL) {
				newGoodPos.set(obj.getCenterPosition().getX(), newGoodPos.getY());
				obj.onCollision(vertColl, align);
				// TODO It doesn't feel good to send collision events
				//when you're just checking for a new position.
			}
			if (align == Alignment.BOTH || align == Alignment.VERTICAL) {
				newGoodPos.set(newGoodPos.getX(), obj.getCenterPosition().getY());
				if (vertColl != horiColl) {
					obj.onCollision(vertColl, align);
					// TODO It doesn't feel good to send collision events
					//when you're just checking for a new position.
				}
			}
		}
		return newGoodPos;
	}

	/**
	 * Finds and returns all collisions with the GameObjects in the provided
	 * list.
	 * 
	 * @param shape
	 *            The Shape to be tested for collisions.
	 * @param list
	 *            The list of GameObjects to test for collisions.
	 * @return A list with all colliding GameObjects.
	 */
	private List<IGameObject> getCollisions(Shape shape, List<IGameObject> list) {
		List<IGameObject> collisions = new ArrayList<IGameObject>();
		for (IGameObject go : list) {
			if (collides(shape, go.getShape())) {
				collisions.add(go);
			}
		}
		return collisions;
	}

	/**
	 * Get the Alignment of a collision between two Shapes.
	 * 
	 * @param collider
	 *            The moving Shape.
	 * @param newPos
	 *            The new Position of the moving shape.
	 * @param collidee
	 *            The stationary Shape.
	 * @return The Alignment the two shapes collide in.
	 */
	private Alignment getCollisionAlignment(Shape collider, Position newPos,
			Shape collidee) {
		boolean movingLeft = collider.getCenterX() > newPos.getX();
		boolean movingUp = collider.getCenterY() > newPos.getY();

		float x = movingLeft ? collider.getMinX() : collider.getMaxX();
		float y = movingUp ? collider.getMinY() : collider.getMaxY();

		boolean xColl = movingLeft ? (collidee.getMaxX() - x) < 0
				: (x - collidee.getMinX()) < 0;
		boolean yColl = movingUp ? (collidee.getMaxY() - y) < 0 : (y - collidee
				.getMinY()) < 0;

		if (xColl && yColl) {
			return Alignment.BOTH;
		} else if (xColl) {
			return Alignment.HORIZONTAL;
		} else if (yColl) {
			return Alignment.VERTICAL;
		} else {
			return Alignment.NONE;
		}
	}

	/**
	 * Checks whether two Shapes collides in any matter.
	 * 
	 * @param go0
	 *            The first Shape
	 * @param go1
	 *            The second Shape
	 * @return <code>true</code> if they collides in any matter,
	 *         <code>false</code> otherwise.
	 */
	private boolean collides(Shape s1, Shape s2) {
		return (s1.intersects(s2) || s1.contains(s2));
	}

	/**
	 * Updates all objects in this world
	 * @param delta The time since last update in ms
	 */
	public void update(int delta) {
		//Update character stuff
		for(IGameObject character : this.getObjectsByType(ICharacter.class)){
			Position newPos = character.update(delta);
			Position newShapePos = getValidPosition(character, newPos, this.getObjectsByType(Impassible.class));
			if(!newPos.equals(newShapePos)) {
				character.setCenterPosition(newShapePos);
			} else {
				character.setCenterPosition(getValidTilePosition(character, newPos));
			}

			if(character.isDestroyed()){
				ICharacter ch = (ICharacter)character;
				ch.reset();
				ch.setCenterPosition(this.data.getGameMap().getRandomSpawn());
			}
		}

		//Update all other objects
		List<IGameObject> objects = this.getObjectsByType(ICharacter.class, false);
		IGameObject object = null;
		for (int i = 0; i<objects.size(); i++) {
			object = objects.get(i);
			
			Position newPos = object.update(delta);
			Shape shape = getConnectedShape(object, newPos);
			//Check collisions with all that is not the same
			//o(projectiles does not collide with projectiles)
			List<IGameObject> goList = getCollisions(shape, this.getObjectsByType(object.getClass(), false));
			
			Velocity velocity = object.getTotalVelocity();
			for (IGameObject obj : goList) {
				//FIXME alignment is wrong!
				object.onCollision(obj, Alignment.getAlignment(velocity.getX(), velocity.getY()));
			}
			
			if (getValidTilePosition(object, newPos).equals(newPos)) {
				object.setCenterPosition(newPos);
			}

			if(object.isDestroyed()){
				this.remove(object);
			}
		}

	}

	public void createdObject(IGameObject object) {
		this.add(object);
	}

}

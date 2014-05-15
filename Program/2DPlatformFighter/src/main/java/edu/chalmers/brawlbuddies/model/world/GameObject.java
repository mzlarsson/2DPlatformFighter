package edu.chalmers.brawlbuddies.model.world;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import edu.chalmers.brawlbuddies.model.IDFactory;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.util.SlickUtil;

/**
 * A class to describe an object with a position, velocity and shape in the game.
 * 
 * @author Patrik Haar
 * @version 1.0
 * @revised Matz Larsson
 * 
 */
public abstract class GameObject implements IGameObject{
	
	private Movement movement;
	private Shape shape;
	private int id;
	
	/**
	 * Constructor for a GameObject, assigns movement and shape.
	 * Position will be set to the Shape's value.
	 * @param m		The Movement object to use
	 * @param shape	The shape to use
	 */
	public GameObject(Movement m, Shape shape) {
		this.movement = m;
		this.shape = SlickUtil.copy(shape);
		this.id = IDFactory.getInstance().getID();
	}
	
	/**
	 * Constructor for a GameObject, will set the center position, Movement and the shape.
	 * @param pos Sets the center x- and y-coordinates of the shape.
	 * @param baseSpeed The movement to use
	 * @param shape Sets the given Shape as the GameObjects shape (with the given position).
	 */
	public GameObject(Position pos, Movement m, Shape shape) {
		this(m, shape);
		this.shape.setCenterX(pos.getX());
		this.shape.setCenterY(pos.getY());
	}
	
	/**
	 * Constructor for a GameObject, will set the shape.
	 * Position will be set to the Shape's value.
	 * Movement will be set to the default Movement
	 * @param shape Sets the given Shape as the GameObjects shape.
	 */
	public GameObject(Shape shape) {
		this(new Movement(), shape);
	}
	
	/**
	 * Constructor for a GameObject, will set the center position and Movement.
	 * Shape will be set to a Rectangle with height&width set to 0 and position to the given Position's values.
	 * @param pos	Sets the center x- and y-coordinates of the shape.
	 * @param m		The Movement to use
	 */
	public GameObject(Position pos, Movement m) {
		this(pos, m, new Rectangle(pos.getX(), pos.getY(), 0, 0));
	}
	
	/**
	 * Constructor for a GameObject, will set the center position.
	 * Movement will be as a default Movement object.
	 * Shape will be set to a Rectangle with height&width set to 0 and position to the given Position's values.
	 * @param pos Sets the center x- and y-coordinates of the shape.
	 */
	public GameObject(Position pos) {
		this(pos, new Movement());
	}
	
	/**
	 * Constructor for a GameObject.
	 * Position will be set to x=0, y=0.
	 * Movement will be set to default Movement object.
	 * Shape will be set to a Rectangle with height=0 width=0.
	 * Variable-velocity will be set to 0.
	 */
	public GameObject() {
		this(new Rectangle(0, 0, 0, 0));
	}
	
	/**
	 * Sets the current movement for this Gameobject
	 * @param m The movement to use
	 */
	public void setMovement(Movement m){
		this.movement = m;
	}
	
	/**
	 * Returns the movement assigned to this GameObject
	 * @return The movement of this Gameobject.
	 */
	public Movement getMovement(){
		return this.movement;
	}
	
	public Velocity getTotalVelocity(){
		return this.getMovement().getTotalVelocity();
	}
	
	/**
	 * Returns the x-coordinate of the top left corner of the shape.
	 * @return float, the x-coordinate of the top left corner of the shape.
	 */
	public float getX() {
		return this.shape.getX();
	}
	
	/**
	 * Returns the y-coordinate of the top left corner of the shape.
	 * @return float, the y-coordinate of the top left corner of the shape.
	 */
	public float getY() {
		return this.shape.getY();
	}

	/**
	 * Returns a Position vector with the x- and y-coordinates from the top left corner of the shape.
	 * @return float, a Position vector with the x- and y-coordinates from the top left corner of the shape.
	 */
	public Position getPosition() {
		return new Position(this.getX(),this.getY());
	}

	
	/**
	 * Returns the x-coordinate of the center of the shape.
	 * @return float, the x-coordinate of the center of the shape.
	 */
	public float getCenterX() {
		return this.shape.getCenterX();
	}
	
	/**
	 * Returns the y-coordinate of the center of the shape.
	 * @return float, the y-coordinate of the center of the shape.
	 */
	public float getCenterY() {
		return this.shape.getCenterY();
	}
	
	/**
	 * Returns a Position vector with the center x- and y-coordinates of the shape.
	 * @return float, a Position vector with the center x- and y-coordinates of the shape.
	 */
	public Position getCenterPosition() {
		return new Position(this.getCenterX(),this.getCenterY());
	}
	
	/**
	 * Returns the shape of the GameObject.
	 * @return Shape, the shape of the GameObject.
	 */
	public Shape getShape() {
		return this.shape;
	}
	
	/**
	 * Transforms the GameObject's shape using the given Transform object.
	 * @param trans The Transform object to transform the shape of the GameObject with.
	 */
	//TODO check if this fix works
	public void transform(Transform trans) {
		this.shape = shape.transform(trans);
	}
	
	/**
	 * Sets the x-position of the top left corner of the shape.
	 * @param x Sets the x-position of the top left corner of the shape.
	 */
	public void setX(float x) {
		this.shape.setX(x);
	}
	
	/**
	 * Sets the y-position of the top left corner of the shape.
	 * @param y Sets the y-position of the top left corner of the shape.
	 */
	public void setY(float y) {
		this.shape.setY(y);
	}
	
	/**
	 * Sets the x- and y-position of the top left corner of the shape.
	 * @param x Sets the x-position of the top left corner of the shape.
	 * @param y Sets the y-position of the top left corner of the shape.
	 */
	public void setPosition(float x, float y) {
		this.shape.setLocation(x ,y);
	}
	
	/**
	 * Sets the x- and y-position of the top left corner of the shape.
	 * @param pos Sets the x- and y-position of the top left corner of the shape using the Position's values.
	 */
	public void setPosition(Position pos) {
		this.setPosition(pos.getX(), pos.getY());
	}
	
	/**
	 *  Sets the x-position of the center of the shape.
	 * @param x Sets the x-position of the center of the shape.
	 */
	public void setCenterX(float x) {
		this.shape.setCenterX(x);
	}
	
	/**
	 *  Sets the y-position of the center of the shape.
	 * @param y Sets the y-position of the center of the shape.
	 */
	public void setCenterY(float y) {
		this.shape.setCenterY(y);
	}
	
	/**
	 * Sets the x- and y-position of the center of the shape.
	 * @param x Sets the x-position of the center of the shape.
	 * @param y Sets the y-position of the center of the shape.
	 */
	public void setCenterPosition(float x, float y) {
		this.shape.setCenterX(x);
		this.shape.setCenterY(y);
	}
	
	/**
	 * Sets the x- and y-position of the center of the shape.
	 * @param pos Sets the x- and y-position of the center of the shape using the Position's values.
	 */
	public void setCenterPosition(Position pos) {
		this.setCenterPosition(pos.getX(), pos.getY());
	}
	
	/**
	 * Sets the GameObject's shape to the given Shape.
	 * @param shape Sets the GameObject's shape to the given Shape.
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public void onCollision(GameObject object, Movement.Alignment alignment){
		this.getMovement().resetSpeed(alignment);
	}
	
	/**
	 * Returns the unique ID of the GameObject.
	 * @return The GameObjects unique ID.
	 */
	public int getID() {
		return id;
	}
	
	public abstract Position update(int delta);
	public abstract int getTypeID();

}

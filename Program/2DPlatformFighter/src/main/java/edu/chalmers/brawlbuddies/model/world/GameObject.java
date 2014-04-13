package edu.chalmers.brawlbuddies.model.world;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.util.SlickUtil;

/**
 * A class to describe an object with a position, velocity and shape in the game.
 * 
 * @author Patrik Haar
 * @version 1.0
 * 
 */
public abstract class GameObject {
	
	private Velocity baseSpeed;
	private Velocity variableSpeed;
	private Shape shape;
	
	/**
	 * Constructor for a GameObject, base- and variable-velocity and the shape.
	 * Position will be set to the Shape's value.
	 * @param baseSpeed
	 * @param variableSpeed
	 * @param shape
	 */
	public GameObject(Velocity baseSpeed, Velocity variableSpeed, Shape shape) {
		this.baseSpeed = baseSpeed.copy();
		this.variableSpeed = variableSpeed.copy();
		this.shape = SlickUtil.copy(shape);
	}
	
	/**
	 * Constructor for a GameObject, will set the center position, base- and variable-velocity and the shape.
	 * @param pos Sets the center x- and y-coordinates of the shape.
	 * @param baseSpeed Creates a new Velocity object and use it as the GameObjects base velocity.
	 * @param variableSpeed Creates a new Velocity object and use it as the GameObjects variable velocity.
	 * @param shape Sets the given Shape as the GameObjects shape (with the given position).
	 */
	public GameObject(Position pos, Velocity baseSpeed, Velocity variableSpeed, Shape shape) {
		this(baseSpeed, variableSpeed, shape);
		this.shape.setCenterX(pos.getX());
		this.shape.setCenterY(pos.getY());
	}
	
	/**
	 * Constructor for a GameObject, will set the center position, base-velocity and the shape.
	 * Variable-velocity will be set to 0.
	 * @param pos Sets the center x- and y-coordinates of the shape.
	 * @param baseSpeed Creates a new Velocity object and use it as the GameObjects base velocity.
	 * @param shape Sets the given Shape as the GameObjects shape (with the given position).
	 */
	public GameObject(Position pos, Velocity baseSpeed, Shape shape) {
		this(pos, baseSpeed, new Velocity(0,0), shape);
	}
	
	/**
	 * Constructor for a GameObject, will set the base-velocity and the shape.
	 * Position will be set to the Shape's value.
	 * Variable-velocity will be set to 0.
	 * @param baseSpeed Creates a new Velocity object and use it as the GameObjects base velocity.
	 * @param shape Sets the given Shape as the GameObjects shape.
	 */
	public GameObject(Velocity baseSpeed, Shape shape) {
		this(baseSpeed, new Velocity(0,0), shape);
	}
	
	/**
	 * Constructor for a GameObject, will set the shape.
	 * Position will be set to the Shape's value.
	 * Base-velocity will be set to 0.
	 * Variable-velocity will be set to 0.
	 * @param shape Sets the given Shape as the GameObjects shape.
	 */
	public GameObject(Shape shape) {
		this(new Velocity(0,0), shape);
	}
	
	/**
	 * Constructor for a GameObject, will set the center position and base-velocity.
	 * Variable-velocity will be set to 0.
	 * Shape will be set to a Rectangle with height&width set to 0 and position to the given Position's values.
	 * @param pos Sets the center x- and y-coordinates of the shape.
	 * @param baseSpeed Creates a new Velocity object and use it as the GameObjects base velocity.
	 */
	public GameObject(Position pos, Velocity baseSpeed) {
		this(pos, baseSpeed, new Rectangle(pos.getX(), pos.getY(), 0, 0));
	}
	
	/**
	 * Constructor for a GameObject, will set the center position.
	 * Base-velocity will be set to 0.
	 * Variable-velocity will be set to 0.
	 * Shape will be set to a Rectangle with height&width set to 0 and position to the given Position's values.
	 * @param pos Sets the center x- and y-coordinates of the shape.
	 */
	public GameObject(Position pos) {
		this(pos, new Velocity(0,0));
	}
	
	/**
	 * Constructor for a GameObject.
	 * Position will be set to x=0, y=0.
	 * Base-velocity will be set to 0.
	 * Shape will be set to a Rectangle with height=0 width=0.
	 * Variable-velocity will be set to 0.
	 */
	public GameObject() {
		this(new Rectangle(0, 0, 0, 0));
	}
	
	/**
	 * Returns the total velocity vector made by adding the base- and variable velocity.
	 * @return Velocity, the combined base- and variable velocity vector.
	 */
	public Velocity getTotalVelocity() {
		return this.baseSpeed.add(this.variableSpeed);
	}
	
	/**
	 * Returns the base velocity vector.
	 * @return Velocity, the base velocity vector
	 */
	public Velocity getBaseVelocity() {
		return this.baseSpeed;
	}
	
	/**
	 * Returns the variable velocity vector.
	 * @return Velocity, the variable velocity vector
	 */
	public Velocity getVariableVelocity() {
		return this.variableSpeed;
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
		return this.shape.getX();
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
	 * Sets the base-velocity vector to the given x- and y-values.
	 * @param x The velocity on the x-axis.
	 * @param y The velocity on the y-axis.
	 */
	public void setBaseVelocity(float x, float y) {
		this.baseSpeed = new Velocity(x, y);
	}
	
	/**
	 * Sets the base-velocity vector to the given velocity vector.
	 * @param vel The velocity to set.
	 */
	public void setBaseVelocity(Velocity vel) {
		this.setBaseVelocity(vel.getX(), vel.getY());
	}
	
	/**
	 * Sets the variable-velocity vector to the given x- and y-values.
	 * @param x The velocity on the x-axis.
	 * @param y The velocity on the y-axis.
	 */
	public void setVariableVelocity(float x, float y) {
		this.variableSpeed = new Velocity(x, y);
	}
	
	/**
	 * Sets the variable-velocity vector to the given velocity vector.
	 * @param vel The velocity to set.
	 */
	public void setVariableVelocity(Velocity vel) {
		this.setVariableVelocity(vel.getX(), vel.getY());
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
	
	public abstract void setMovementState(MovementState ms);
	public abstract Position update(int delta);
	public abstract GameObject copy();
}

package edu.chalmers.brawlbuddies.model.world;

import java.io.ObjectStreamException;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;

/**
 * A class to represent a player-controlled character.
 * 
 * @author Patrik Haar
 * @version 0.3
 * @revised David Gustafsson
 */
@XStreamAlias("character")
public class Character extends GameObject {
	
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("bio")
	private String bio;
	@XStreamAlias("health")
	private Health health;
	@XStreamAlias("movespeed")
	private float baseSpeed;
	@XStreamAlias("jumpingpower")
	private float baseJump;
	@XStreamAlias("maxjumps")
	private int maxJumps;
	private int jumpsLeft;
	
	private MovementState movState;
	private Velocity gravity;
	
	private Aim aim;
	private Direction currDir;
	private Direction lastAimed = Direction.RIGHT;
	
	/**
	 * Creates a Character.
	 * @param cd The data from which the character gets its attributes from.
	 * @param player The Player controlling the character.
	 */
	public Character(Shape shape) {
		super(shape);
		this.jumpsLeft = this.maxJumps;
		this.gravity = new Velocity(0,1000);
		this.aim = new Aim(1,0);
		this.setMovementState(new Airborne(this, this.gravity));
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	public void setMaxHealth(float a){
		
	}
	public void setBaseSpeed(float baseSpeed) {
		this.baseSpeed = baseSpeed;
	}

	public void setBaseJump(float baseJump) {
		this.baseJump = baseJump;
	}

	public void setMaxJumps(int maxJumps) {
		this.maxJumps = maxJumps;
		this.jumpsLeft = maxJumps;
	}

	/**
	 * Updates the velocity and position of the Character and returns the old position.
	 * @param delta The time passed since last update in milliseconds.
	 * @return The position before movement.
	 */
	public Position update(int delta) {
		return this.movState.update(delta);
	}
	
	/**
	 * Makes the character move to the left/right/up/down depending on the Direction.
	 */
	public void move(Direction dir) {
		if (dir != currDir) {
			switch (dir) {
			case LEFT:
				this.setBaseVelocity(-this.baseSpeed,this.getBaseVelocity().getY());
				lastAimed = Direction.LEFT;
				this.aim = dir.getAim();
				break;
			case RIGHT:
				this.setBaseVelocity(this.baseSpeed,this.getBaseVelocity().getY());
				lastAimed = Direction.RIGHT;
				this.aim = dir.getAim();
				break;
			case UP:
				this.setBaseVelocity(0, this.getBaseVelocity().getY());
				this.aim = dir.getAim();
				break;
			case DOWN:
				this.setBaseVelocity(0, this.getBaseVelocity().getY());
				this.aim = dir.getAim();
				break;
			case NORTHEAST:
				this.setBaseVelocity(this.baseSpeed,this.getBaseVelocity().getY());
				lastAimed = Direction.RIGHT;
				this.aim = dir.getAim();
				break;
			case NORTHWEST:
				this.setBaseVelocity(-this.baseSpeed,this.getBaseVelocity().getY());
				lastAimed = Direction.LEFT;
				this.aim = dir.getAim();
				break;
			case SOUTHEAST:
				this.setBaseVelocity(this.baseSpeed,this.getBaseVelocity().getY());
				lastAimed = Direction.RIGHT;
				this.aim = dir.getAim();
				break;
			case SOUTHWEST:
				this.setBaseVelocity(-this.baseSpeed,this.getBaseVelocity().getY());
				lastAimed = Direction.LEFT;
				this.aim = dir.getAim();
				break;
			case NONE:
				this.setBaseVelocity(0, this.getBaseVelocity().getY());
				this.aim = lastAimed.getAim();
				break;
			}
		}
	}
	
	/**
	 * Makes the character jump if able.
	 */
	public void makeJump() {
		System.out.println("Försöker hoppa med antal hopp kvar: " + jumpsLeft);
		if (this.canJump()) {
			this.jumpsLeft--;
			if(jumpsLeft==maxJumps-1) {
				this.setMovementState(new Airborne(this, this.gravity));
			}
			if (this.getBaseVelocity().getY()==0) {
				this.increaseBaseVelocity(0, -this.baseJump);
			}
			this.setVariableVelocity(this.getVariableVelocity().getX(), 0);
		}
	}
	
	/**
	 * Cancel a jump during the upwards movement.
	 */
	public void cancelJump() {
		if (this.getBaseVelocity().getY()<0 && this.getTotalVelocity().getY()<10) {
			this.setVariableVelocity(this.getVariableVelocity().getX(), -this.getBaseVelocity().getX()-10);
		}
	}
	
	public Aim getAim() {
		return this.aim;
	}
	
	/**
	 * Inner method to change the base velocity.
	 * @param x The change on the x-axis.
	 * @param y The change on the y-axis.
	 */
	private void increaseBaseVelocity(float x, float y) {
		this.setBaseVelocity(this.getBaseVelocity().add(x,y));
	}
	
	/**
	 * Inner method to see if the character is able to jump.
	 * @return true if the character can jump.
	 */
	private boolean canJump() {
		return jumpsLeft>0;
	}
	
	/**
	 * Returns the current movement state of the character.
	 * @return The current MovementState.
	 */
	public MovementState getMovementState() {
		return this.movState;
	}
	
	/**
	 * Sets the movement state of the character.
	 * @param ms The MovementState to set to the character.
	 */
	public void setMovementState(MovementState ms) {
		if (ms.getClass().equals(Walking.class)) {
			this.setBaseVelocity(this.getBaseVelocity().getX(), 0);
			this.setVariableVelocity(this.getVariableVelocity().getX(), 0);
			jumpsLeft = maxJumps;
		} else if (ms.getClass().equals(Airborne.class)) {
			if (jumpsLeft == maxJumps) {
				jumpsLeft = maxJumps -1;
			}
		}
		this.movState = ms;
	}
	public void takeDamage(float a){
		health.takeDamage(a);
	}
	public void heal(float a){
		health.heal(a);
	}
	public float getHealth(){
		return health.getHp();
	}
	public void restoreHealth(){
		health.restoreHp();
	}
	public void setHealth(float a){
		this.health = new Health(a);
	}
	
	/**
	 * Returns a copy of this character.
	 */
	@Override
	public GameObject copy(){
		//FIXME temporary solution. implement correctly!
		return new Character(new Rectangle(0,0,50,80));
	}
	
	// TODO Temporary draw method to use a shape as the image for the upcoming demo.
	public void draw() {
		Graphics g = new Graphics();
		g.setColor(Color.black);
		g.fill(this.getShape());
	}
	
	private Object readResolve() throws ObjectStreamException {
		this.setShape(new Rectangle(0,0,50,80));
		this.setBaseVelocity(0, 0);
		this.setVariableVelocity(0, 0);
		this.jumpsLeft = this.maxJumps;
		this.gravity = new Velocity(0,1000);
		this.setMovementState(new Airborne(this, this.gravity));
		return this;
	}

}

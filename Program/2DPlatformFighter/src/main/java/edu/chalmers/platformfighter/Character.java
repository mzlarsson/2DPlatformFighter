package edu.chalmers.platformfighter;

import org.newdawn.slick.Graphics;

/**
 * A class to represent a player-controlled character.
 * 
 * @author Patrik Haar
 * @version 0.2
 */
public class Character extends GameObject {
	
	private float baseSpeed;
	private float baseJump;
	private int maxJumps;
	private int jumpsLeft;
	
	private MovementState movState;
	private Velocity gravity;
	
	// TODO We probably don't want it like this in the final version
	private boolean movingLeft;
	private boolean movingRight;
	
	/**
	 * Creates a Character.
	 * @param cd The data from which the character gets its attributes from.
	 * @param player The Player controlling the character.
	 */
	public Character(CharacterData cd) {
		super(cd.shape);
		this.baseSpeed = cd.baseMovementSpeed;
		this.baseJump = cd.baseJumpingPower;
		this.gravity = new Velocity(0,10);
		this.movState = new Airborne(this, this.gravity);
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
		switch (dir) {
		case LEFT:
			if (!movingLeft) {
				if (movingRight) {
					this.increaseBaseVelocity(-2*this.baseSpeed,0);
				} else {
					this.increaseBaseVelocity(-this.baseSpeed,0);
				}
			}
			break;
			
		case RIGHT:
			if (!movingRight) {
				if (movingLeft) {
					this.increaseBaseVelocity(2*this.baseSpeed,0);
				} else {
					this.increaseBaseVelocity(this.baseSpeed,0);
				}
			}
			break;
			
		case UP:
			// TODO Auto-generated method stub
			
		case DOWN:
			// TODO Auto-generated method stub
		}
	}
	
	/**
	 * Makes the character jump if able.
	 */
	public void makeJump() {
		if (this.canJump()) {
			this.jumpsLeft--;
			this.increaseBaseVelocity(0, -this.baseJump);
			this.setMovementState(new Airborne(this, this.gravity));
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
			jumpsLeft = maxJumps;
		} else if (ms.getClass().equals(Airborne.class) && maxJumps==jumpsLeft) {
			jumpsLeft--;
		}
		this.movState = ms;
	}
	
	// TODO Temporary draw method to use a shape as the image for the upcoming demo.
	public void draw(Graphics g) {
		g.draw(this.getShape());
	}
}

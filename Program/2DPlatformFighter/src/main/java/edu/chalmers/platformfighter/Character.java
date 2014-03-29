package edu.chalmers.platformfighter;

/**
 * A class to represent a player controlled character.
 * 
 * @author Patrik Haar
 * @version 0.1
 */
public class Character extends GameObject {

	private String name;
	
	private float baseSpeed;
	private float baseJump;
	
	private MovementState movState;
	private Velocity gravity;
	
	public Character(CharacterData cd, Player player) {
		super(cd.shape);
		this.name = player.getName();
		this.baseSpeed = cd.baseMovementSpeed;
		this.baseJump = cd.baseJumpingPower;
		this.gravity = new Velocity(0,1);
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
	
	public void moveLeft() {
		
	}
	
	public void moveRight() {
		
	}
	
	public void moveUp() {
		
	}
	
	public void moveDown() {
		
	}
	
	public void makeJump() {
		
	}
	
	public void cancelJump() {
		//TODO Implement ability to cancel jumps
	}
	
	/**
	 * Sets the movement state of the character.
	 * @param ms The MovementState to set to the character.
	 */
	public void setMovementState(MovementState ms) {
		this.movState = ms;
	}
}

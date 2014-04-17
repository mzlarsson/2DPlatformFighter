package edu.chalmers.brawlbuddies.model.world;

import edu.chalmers.brawlbuddies.model.Velocity;

/**
 * Class that extends the Movement class by adding logics for jumps as well.
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class JumpMovement extends Movement{
	
	private float baseJumpSpeed;
	private int maxJumps;
	private int jumpsLeft;


	/**
	 * Initiates a Movement with extended logic for jumping.
	 * Default values are: Maximum 2 jumps and 1000 as the jumpspeed.
	 */
	public JumpMovement(){
		this(1000, 2);
	}

	/**
	 * Initiates a Movement with extended logic for jumping.
	 * @param baseJumpSpeed	The initial speed in a jump
	 * @param maxJumps		The amount of allowed jumps in a row
	 */
	public JumpMovement(float baseJumpSpeed, int maxJumps){
		super();
		this.baseJumpSpeed = baseJumpSpeed;
		this.maxJumps = maxJumps;
		resetJumps();
	}
	
	/**
	 * Initiates a Movement with extended logic for jumping.
	 * @param baseSpeed 	The original speed when moving
	 * @param baseJumpSpeed	The initial speed in a jump
	 * @param maxJumps		The amount of allowed jumps in a row
	 */
	public JumpMovement(Velocity baseSpeed, float baseJumpSpeed, int maxJumps){
		super(baseSpeed);
		this.baseJumpSpeed = baseJumpSpeed;
		this.maxJumps = maxJumps;
		resetJumps();
	}
	
	/**
	 * Initiates a Movement with extended logic for jumping.
	 * @param baseSpeed 	The original speed when moving
	 * @param gravity 		The gravity
	 * @param baseJumpSpeed	The initial speed in a jump
	 * @param maxJumps		The amount of allowed jumps in a row
	 */
	public JumpMovement(Velocity baseSpeed, Velocity gravity, float baseJumpSpeed, int maxJumps){
		super(baseSpeed, gravity);
		this.baseJumpSpeed = baseJumpSpeed;
		this.maxJumps = maxJumps;
		resetJumps();
	}
	
	/**
	 * Resets the speed in the given alignment and adjusts the jump counter
	 * if neccessary.
	 */
	@Override
	public void resetSpeed(Movement.Alignment align){
		//Check if we should reset the jump counter.
		if(align == Movement.Alignment.VERTICAL || align == Movement.Alignment.BOTH){
			if(this.getTotalVelocity().getY()>0){
				this.resetJumps();
			}
		}
		
		//Perform all ordinary resets.
		super.resetSpeed(align);
	}
	
	/**
	 * Sets the jumps left to maximum.
	 */
	public void resetJumps(){
		this.jumpsLeft = maxJumps;
	}

	/**
	 * Returns the amount of maximum jumps in a row that is allowed
	 * @return The maximum allowed amount of jumps in a row
	 */
	public int getMaxJumps(){
		return this.maxJumps;
	}
	
	/**
	 * Sets the maximum amount of allowed jumps.
	 * @param maxJumps The maximum allowed amount of jumps
	 */
	public void setMaxJumps(int maxJumps){
		this.maxJumps = maxJumps;
		this.jumpsLeft = Math.min(this.jumpsLeft, this.maxJumps);
	}
	
	/**
	 * Returns the speed a jump is initiated with.
	 * @return The speed a jump is initiated with
	 */
	public float getJumpSpeed(){
		return this.baseJumpSpeed;
	}
	
	/**
	 * Sets the maximum amount of allowed jumps.
	 * @param maxJumps The maximum allowed amount of jumps
	 */
	public void setBaseJumpSpeed(float baseJumpSpeed){
		this.baseJumpSpeed = baseJumpSpeed;
	}
	
	/**
	 * Resets the inner speed along y-axis, that is: cancels jump.
	 */
	public void cancelJump(){
		this.setInnerSpeed(this.getInnerSpeed().getX(), Math.max(0, this.getInnerSpeed().getY()));
	}
	
	/**
	 * Tries to jump.
	 * @return If the jump was made.
	 */
	public boolean jump(){
		if(canJump()){
			this.setInnerSpeed(this.getInnerSpeed().getX(), -this.baseJumpSpeed);
			this.jumpsLeft--;
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Checks if it is allowed to jump
	 * @return <code>true</code> if allowed to jump, <code>false</code> otherwise.
	 */
	public boolean canJump(){
		return this.jumpsLeft>0;
	}

}

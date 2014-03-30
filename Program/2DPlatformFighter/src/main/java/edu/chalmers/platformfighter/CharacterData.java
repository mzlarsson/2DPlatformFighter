package edu.chalmers.platformfighter;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class CharacterData {
	
	public final Shape shape;
	public final float baseMovementSpeed;
	public final float baseJumpingPower;
	public final int maxJumps;
	
	public CharacterData() {
		this.shape = new Circle(0,0,30);
		this.baseMovementSpeed = 300;
		this.baseJumpingPower = 100;
		this.maxJumps = 1;
	}
}

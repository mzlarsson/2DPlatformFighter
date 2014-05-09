package edu.chalmers.brawlbuddies.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.chalmers.brawlbuddies.model.Vector;
import edu.chalmers.brawlbuddies.model.Velocity;

/**
 * A test class for Velocity
 * 
 * @author David Gustafsson
 * 
 */
public class VelocityTest {
	
	@Test
	public void testNewVelocity() {
		Velocity v1 = new Velocity(1f, 2f);
		assertTrue(v1.x == 1f);
		assertTrue(v1.y == 2f);
		Velocity v2 = new Velocity(new Vector(3f, 4f));
		assertTrue(v2.x == 3f);
		assertTrue(v2.y == 4f);
	}
	
	@Test
	public void testCopy() {
		Velocity v1 = new Velocity(1f, 2f);
		Velocity v2 = v1.copy();
		v1.set(3f, 4f);
		assertTrue(v1.x == 3f);
		assertTrue(v1.y == 4f);
		assertTrue(v2.x == 1f);
		assertTrue(v2.y == 2f);
	}
	
	@Test
	public void testAddFloats() {
		Velocity v1 = new Velocity(1f, 2f);
		Velocity v2 = v1.add(3f, 4f);
		assertTrue(v1.getX() == 1f);
		assertTrue(v1.getY() == 2f);
		assertTrue(v2.getX() == 4f);
		assertTrue(v2.getY() == 6f);
	}
	
	@Test
	public void testAddVelocity() {
		Velocity v1 = new Velocity(1f, 2f);
		Velocity v2 = new Velocity(3f, 4f);
		Velocity v3 = v1.add(v2);
		assertTrue(v1.getX() == 1f);
		assertTrue(v1.getY() == 2f);
		assertTrue(v2.getX() == 3f);
		assertTrue(v2.getY() == 4f);
		assertTrue(v3.getX() == 4f);
		assertTrue(v3.getY() == 6f);
	}
	
	@Test
	public void testScale() {
		Velocity v1 = new Velocity(1f, 2f);
		Velocity v2 = v1.scale(4f);
		assertTrue(v1.getX() == 1f);
		assertTrue(v1.getY() == 2f);
		assertTrue(v2.getX() == 4f);
		assertTrue(v2.getY() == 8f);
	}
	
	@Test
	public void testGetNormalized(){
		Velocity v1 = new Velocity(10f, 30f);
		Velocity v2 = v1.getNormalized();
		assertTrue(v1.x == 10f);
		assertTrue(v1.y == 30f);
		assertTrue(v2.x * v2.x + v2.y * v2.y > 1 - 0.0000001);
	}
	public void testToString(){
		Velocity v1 = new Velocity(10f, 30f);
		String s1 = "Velocity (10.0 , 30.0)";
		assertTrue(v1.toString().equals(s1));
	}
	

}

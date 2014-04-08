package edu.chalmers.brawlbuddies;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A test class for Velocity
 * 
 * @author David Gustafsson
 * 
 */
public class VelocityTest {
	/**
	 * A test for the constructors in velocity
	 */
	@Test
	public void testNewVelocity() {
		Velocity v1 = new Velocity(1f, 2f);
		assertTrue(v1.x == 1f);
		assertTrue(v1.y == 2f);
		Velocity v2 = new Velocity(new Vector(3f, 4f));
		assertTrue(v2.x == 3f);
		assertTrue(v2.y == 4f);
	}
	/**
	 * A test for the copy method in velocity
	 */
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
	/**
	 * A test for add(float x , float y) in velocity
	 */
	@Test
	public void testAddFloats() {
		Velocity v1 = new Velocity(1f, 2f);
		Velocity v2 = v1.add(3f, 4f);
		assertTrue(v1.getX() == 1f);
		assertTrue(v1.getY() == 2f);
		assertTrue(v2.getX() == 4f);
		assertTrue(v2.getY() == 6f);
	}
	/**
	 * A test for add(Vector v) in velocity
	 */
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
	/**
	 * A test for scale in velocity
	 */
	@Test
	public void testScale() {
		Velocity v1 = new Velocity(1f, 2f);
		Velocity v2 = v1.scale(4f);
		assertTrue(v1.getX() == 1f);
		assertTrue(v1.getY() == 2f);
		assertTrue(v2.getX() == 4f);
		assertTrue(v2.getY() == 8f);
	}

}

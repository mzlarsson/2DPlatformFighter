package edu.chalmers.brawlbuddies;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * A test class for Velocity
 * @author David Gustafsson
 *
 */
//TODO Write more test for Velocity 
public class VelocityTest {

	@Test
	public void test() {
		testAdd();
	}
	public void testAdd(){
		Velocity v1 = new Velocity(1, 1);
		Velocity v2 = new Velocity(1, 2);
		Velocity v3 = v1.add(v2);
		assertTrue(v1.getX() == 1);
		assertTrue(v3.getX() == 2);
		assertTrue(v3.getY() == 3);
	}

}

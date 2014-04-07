package edu.chalmers.brawlbuddies;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.chalmers.brawlbuddies.Vector;

/**
 * A test class for Vector
 * 
 * @author David Gustafsson
 * 
 */
// TODO Write more test for Vector
public class VectorTest {
	/**
	 * The test that will be run
	 */
	@Test
	public void test() {
		testAdd();
		testNewVector();
	}

	public void testFailure() throws Exception {
		fail();
	}

	/**
	 * Testing Vector constructor
	 */
	public void testNewVector() {
		Vector v1 = new Vector(1, 1);
		assertTrue(v1.x == 1);
		assertTrue(v1.y == 1);
	}

	/**
	 * Testing add method in Vector
	 */
	public void testAdd() {
		Vector v1 = new Vector(1, 1);
		Vector v2 = new Vector(1, 3);
		Vector v3 = v1.add(v2);
		assertTrue(v1.x == 1);
		assertTrue(v1.y == 1);
		assertTrue(v2.x == 1);
		assertTrue(v2.y == 3);
		assertTrue(v3.x == 2);
		assertTrue(v3.y == 4);
	}

}

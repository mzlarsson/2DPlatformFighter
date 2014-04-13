package edu.chalmers.brawlbuddies;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.chalmers.brawlbuddies.model.Vector;

/**
 * A test class for Vector
 * 
 * @author David Gustafsson
 * 
 */
public class VectorTest {
	/**
	 * Testing Vector constructor
	 */
	@Test
	public void testNewVector() {
		Vector v1 = new Vector(1f, 1f);
		assertTrue(v1.x == 1f);
		assertTrue(v1.y == 1f);
	}
	/**
	 * Test for Increase(Vector) in Vector
	 */
	@Test
	public void testIncreaseVector(){
		Vector v1 = new Vector( 1f, 2f);
		Vector v2 = new Vector( 3f, 4f);
		v1.Increase(v2);
		assertTrue(v1.x == 4f);
		assertTrue(v1.y == 6f);
		assertTrue(v2.x == 3f);
		assertTrue(v2.y == 4f);
	}
	/**
	 * Test for Increase(float x , float y) in Vector
	 */
	@Test
	public void testIncreaseFloats(){
		Vector v1 = new Vector( 1f, 2f);
		v1.Increase(3f,4f);
		assertTrue(v1.x == 4f);
		assertTrue(v1.y == 6f);
	}
	/**
	 * Test for copy in vector
	 */
	@Test
	public void testCopy(){
		Vector v1 = new Vector(1f , 2f);
		Vector v2 = v1.copy();
		v1.set(3f, 5f);
		assertTrue(v1.x == 3f);
		assertTrue(v1.y == 5f);
		assertTrue(v2.x == 1f);
		assertTrue(v2.y == 2f);
	}
	/**
	 * Testing add method in Vector
	 */
	@Test
	public void testAddVector() {
		Vector v1 = new Vector(1f, 1f);
		Vector v2 = new Vector(1f, 3f);
		Vector v3 = v1.add(v2);
		assertTrue(v1.x == 1f);
		assertTrue(v1.y == 1f);
		assertTrue(v2.x == 1f);
		assertTrue(v2.y == 3f);
		assertTrue(v3.x == 2f);
		assertTrue(v3.y == 4f);
	}
	/**
	 * Test for add(float x , float y) in Vector
	 */
	@Test
	public void testAddFloats(){
		Vector v1 = new Vector(1f , 3f);
		Vector v2 = v1.add(2f , 3f);
		assertTrue(v1.x == 1f);
		assertTrue(v1.y == 3f);
		assertTrue(v2.x == 3f);
		assertTrue(v2.y == 6f);
	}
	@Test
	public void testgetNormalised(){
		Vector v1 = new Vector(1f , 3f);
		Vector v2 = v1.getNormalized();
		assertTrue(v1.x == 1f);
		assertTrue(v1.y == 3f);
		assertTrue(v2.x * v2.x + v2.y * v2.y > 1 - 0.0000001);
	}
}

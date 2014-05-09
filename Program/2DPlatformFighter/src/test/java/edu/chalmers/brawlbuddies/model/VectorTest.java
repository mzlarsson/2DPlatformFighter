package edu.chalmers.brawlbuddies.model;

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
		v1.increase(v2);
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
		v1.increase(3f,4f);
		assertTrue(v1.x == 4f);
		assertTrue(v1.y == 6f);
	}
	@Test
	public void testSubtractFloats(){
		Vector v1 = new Vector(4f,6f);
		Vector v2 = v1.subtract(3f, 4f);
		assertTrue(v1.x == 4f && v1.y == 6f);
		assertTrue(v2.x == 1f && v2.y == 2f);
	}
	@Test
	public void testSubtractVector(){
		Vector v1 = new Vector(4f,6f);
		Vector v2 = new Vector(3f , 4f);
		Vector v3 = v1.subtract(v2);
		assertTrue(v1.x == 4f && v1.y == 6f);
		assertTrue(v2.x == 3f && v2.y == 4f);
		assertTrue(v3.x == 1f && v3.y == 2f);
	}
	@Test
	public void testDecreaseFloats(){
		Vector v1 = new Vector(4f,6f);
		v1.decrease(3f, 4f);
		assertTrue(v1.x == 1f && v1.y == 2f);
	}
	@Test
	public void testDecreaseVector(){
		Vector v1 = new Vector(4f,6f);
		Vector v2 = new Vector(3f, 4f);
		v1.decrease(v2);
		assertTrue(v1.x == 1f && v1.y == 2f);
	}
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
	@Test
	public void testToString(){
		Vector v1 = new Vector(1f , 2f);
		String s1  = "Vector (1.0 , 2.0)";
		assertTrue(v1.toString().equals(s1));
	}
	@Test
	public void testScale(){
		Vector v1 = new Vector(1f,2f);
		Vector v2 = v1.scale(5);
		assertTrue(v2.x == 5 && v2.y == 10);
	}
	@Test
	public void testGetDirection(){
		Vector[] vectors = { new Vector(125, 0) , new Vector( 100, -100), new Vector(0 , -150)
		, new Vector (-175 , 0), new Vector(-175, -75), new Vector( -25 , 50 ), new Vector( 0, 85), new Vector( 150 , 135),
		new Vector(0,0)};
		Direction[] result = { Direction.RIGHT, Direction.NORTHEAST , Direction.UP, 
				Direction.LEFT , Direction.NORTHWEST , Direction.SOUTHWEST, Direction.DOWN , 
				Direction.SOUTHEAST , Direction.NONE};
		for(int i = 0 ; i < vectors.length && i < result.length ; i++){
			assertTrue(vectors[i].getDirection() == result[i]);
		}
	}
}

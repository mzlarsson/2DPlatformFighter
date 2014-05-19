package edu.chalmers.brawlbuddies.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Vector;
/**
 *  A test class for Position
 * @author David Gustafsson
 *
 */
public class PositionTest {
	/**
	 * Testing constructors in Position
	 */
	@Test
	public void newPositionTest(){
		Position p1 = new Position(1f,1f);
		assertTrue(p1.x == 1f);
		assertTrue(p1.y == 1f);
		Vector v = new Vector(3f , 5f);
		Position p2 = new Position(v);
		assertTrue(p2.x == 3f);
		assertTrue(p2.y == 5f);
	}
	
	@Test
	public void copyTest(){
		Position p1 = new Position(1f,1f);
		Position p2 = p1.copy();
		p1.set(new Vector(2f,2f));
		assertTrue(p1.x == 2f);
		assertTrue(p1.y == 2f);
		assertTrue(p2.x == 1f);
		assertTrue(p2.y == 1f);
		
	}
	
	@Test
	public void addVectorTest(){
		Position p1 = new Position(1f, 2f);
		Position p2 = new Position(3f, 4f);
		Position p3 = p1.add(p2);
		assertTrue(p1.x == 1f);
		assertTrue(p1.y == 2f);
		assertTrue(p2.x == 3f);
		assertTrue(p2.y == 4f);
		assertTrue(p3.x == 4f);
		assertTrue(p3.y == 6f);
	}
	
	@Test
	public void addFloatsTest(){
		Position p1 = new Position(1f, 2f);
		Position p2 = p1.add(3f, 4f);
		assertTrue(p1.x == 1f);
		assertTrue(p1.y == 2f);
		assertTrue(p2.x == 4f);
		assertTrue(p2.y == 6f);
	}
	@Test
	public void testSubtractFloats(){
		Position p1 = new Position(8f, 10f);
		Position p2 = p1.subtract(5f, 3f);
		assertTrue(p2.x == 3f && p2.y == 7f);
		assertTrue(p1.x == 8f && p1.y == 10f);
	}
	@Test
	public void testToString(){
		Position p1 = new Position(1f , 2f);
		String s1 = "Position (1.0 , 2.0)";
		assertTrue(p1.toString().equals(s1));
	}

}

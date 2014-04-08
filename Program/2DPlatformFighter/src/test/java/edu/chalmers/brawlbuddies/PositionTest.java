package edu.chalmers.brawlbuddies;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.geom.Vector2f;

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
	
	
	/**
	 * Testing copy method in Position
	 */
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
	/**
	 * Testing add(Vector v) in Position
	 */
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
	/**
	 * Testing add(float x , float y) in Position
	 */
	@Test
	public void addFloatsTest(){
		Position p1 = new Position(1f, 2f);
		Position p2 = p1.add(3f, 4f);
		assertTrue(p1.x == 1f);
		assertTrue(p1.y == 2f);
		assertTrue(p2.x == 4f);
		assertTrue(p2.y == 6f);
	}

}

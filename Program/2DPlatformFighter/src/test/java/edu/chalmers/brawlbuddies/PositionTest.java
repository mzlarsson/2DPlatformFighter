package edu.chalmers.brawlbuddies;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 *  A test class for Position
 * @author David Gustafsson
 *
 */
//TODO Write more test for Position.
public class PositionTest {
	@Test
	public void test() {
		addTest();
	}
	/**
	 * Testing add method in Position
	 */
	public void addTest(){
		Position p1 = new Position(1, 1);
		Position p2 = new Position(1, 1);
		Position p3 = p1.add(p2);
		assertTrue(p3.x == 2);
		assertTrue(p1.x == 1);
	}

}

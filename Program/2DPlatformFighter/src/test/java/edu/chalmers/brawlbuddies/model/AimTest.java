package edu.chalmers.brawlbuddies.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.chalmers.brawlbuddies.model.Aim;
/**
 * A test for the Aim class
 * @author David Gustafsson
 *
 */
//TODO Write more comments
//TODO Write more tests
public class AimTest {
	
	@Test
	public void testAddVector(){
		Aim a1 = new Aim(0f, 1f);
		Aim a2 = new Aim(1f , 2f);
		Aim a3 = a1.add(a2);
		Aim a4 = a2.add(a1);
		assertTrue( a1.x == 0f);
		assertTrue( a1.y == 1f);
		assertTrue( a2.x == 1f);
		assertTrue( a2.y == 2f);
		assertTrue( a3.x == a4.x && a3.x == 1f);
		assertTrue( a3.y == a4.y && a4.y == 3f);
	}
	
	@Test 
	public void testAddFloats(){
		Aim a1 = new Aim(0f , 1f);
		float x = 1f;
		float y  = 2f;
		Aim a2 = a1.add(x, y);
		assertTrue(a2.x == 1f && a2.y == 3f);
		assertTrue(a1.x == 0f && a1.y == 1f);
	}
	
	@Test
	public void testAngle() {
		Aim a = new Aim(0f, 1f);
		assertTrue(a.getTheta() == 90);
		Aim b = new Aim(1, 0);
		assertTrue(b.getTheta() == 0);
	}
	
	@Test
	public void testNormalise(){
		Aim a = new Aim(5f,10f);
		Aim b = new Aim(5f, 10f);
		a.normalise();
		assertTrue(a.getX() * a.getX() + a.getY() * a.getY() > 1 - 0.0000001 );
		assertTrue(a.getClass().equals(b.getClass()) );
	}
	
	@Test
	public void testScale(){
		Aim a1 = new Aim(1f , 2f);
		float scale = 5f;
		Aim a2 = a1.scale(scale);
		assertTrue(a1.x == 1f && a1.y == 2f);
		assertTrue(a2.x == 5f && a2.y == 10f);
	}
	
	@Test
	public void testCopy(){
		Aim a1 = new Aim(1f , 2f);
		Aim a2 = a1.copy();
		assertTrue(a1 != a2);
	}
	
	@Test
	public void testToString(){
		Aim a1 = new Aim(1f, 2f);
		String s1 = "Aim (1.0 , 2.0)";
		assertTrue(a1.toString().equals(s1));
	}

}

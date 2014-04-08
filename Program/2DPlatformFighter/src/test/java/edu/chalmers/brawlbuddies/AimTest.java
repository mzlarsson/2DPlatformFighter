package edu.chalmers.brawlbuddies;

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

}

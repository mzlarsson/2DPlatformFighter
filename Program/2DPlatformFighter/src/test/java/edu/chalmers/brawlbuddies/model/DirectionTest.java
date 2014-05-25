package edu.chalmers.brawlbuddies.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chalmers.brawlbuddies.util.GameLogger;
/**
 * A test class for Direction
 * @author David Gustafsson
 *
 */
public class DirectionTest {
	private Direction down = Direction.DOWN;
	private Direction up = Direction.UP;
	private Direction left  = Direction.LEFT;
	private Direction right  = Direction.RIGHT;
	private Direction southWest = Direction.SOUTHWEST;
	private Direction northWest = Direction.NORTHWEST;
	private Direction southEast = Direction.SOUTHEAST;
	private Direction northEast = Direction.NORTHEAST;
	private Direction none = Direction.NONE;
	
	
	@Test
	public void testaddDirection(){
		Direction[] test1 = {down, up, left , right, southWest , northEast, southEast, northWest , none, none};
		Direction[] test2  = { southEast , southWest , down , none, down , down};
		Direction[] test3 = { northEast , northWest , up , none, up , up};
		Direction[] test4 = { up , left, northWest , up , northWest , none , northWest , northWest};
		Direction[] test5  = { down , left , down , southWest , southWest , none, southWest , southWest};
		Direction[] test6 = { up , right , up, northEast , northEast , none ,northEast , northEast };
		Direction[] test7 = { down , right, down , southEast , southEast , none , southEast , southEast};
		Direction[] test8 = { southWest , northWest , left, none , left , left};
		Direction[] test9 = { southEast, northEast , right , none , right , right};
		testAddDirectionArray(test1);
		testAddDirectionArray(test2);
		testAddDirectionArray(test3);
		testAddDirectionArray(test4);
		testAddDirectionArray(test5);
		testAddDirectionArray(test6);
		testAddDirectionArray(test7);
		testAddDirectionArray(test8);
		testAddDirectionArray(test9);
	}
	@Test
	public void testGetXDirection(){
		assertTrue(southEast.getXDirection() == Direction.RIGHT);
		assertTrue(northEast.getXDirection() == Direction.RIGHT);
		assertTrue(southWest.getXDirection() == Direction.LEFT);
		assertTrue(northWest.getXDirection() == Direction.LEFT);
	}
	@Test
	public void testGetYDirection(){
		assertTrue(southEast.getYDirection() == Direction.DOWN);
		assertTrue(southWest.getYDirection() == Direction.DOWN);
		assertTrue(northEast.getYDirection() == Direction.UP);
		assertTrue(northWest.getYDirection() == Direction.UP);
	}
	@Test 
	public void testGetDirectionFloats(){
		// One and another greater number is used to test if a magnitude makes a differance
		float[] test1  = {(float) (100 * Math.random() + 1), 1};
		// -1 and another lesser number is used to test if a magnitude makes a differance
		float[] test2 = {(float) (-100 * Math.random()-1 ), -1};
		float[] test3 = {0};
		testGetDirectionCalculateMethod(test1 , test2 , Direction.NORTHEAST);
		testGetDirectionCalculateMethod(test1 , test1 , Direction.SOUTHEAST);
		testGetDirectionCalculateMethod(test2 , test2 , Direction.NORTHWEST);
		testGetDirectionCalculateMethod(test2 , test1 , Direction.SOUTHWEST);
		testGetDirectionCalculateMethod(test3 , test1 , Direction.DOWN);
		testGetDirectionCalculateMethod(test3 , test2 , Direction.UP);
		testGetDirectionCalculateMethod(test1, test3, Direction.RIGHT);
		testGetDirectionCalculateMethod(test2 , test3 , Direction.LEFT);
		testGetDirectionCalculateMethod(test3 , test3 , Direction.NONE);
	}
	@Test
	public void testGetDirectionAngle(){
		Double[] angles = { 0.0, 45.0 , 90.0 , 135.0 , 180.0 , 225.0, 270.0 , 315.0 , 360.0};
		Direction[] result = { Direction.RIGHT, Direction.NORTHEAST , Direction.UP, Direction.NORTHWEST , 
				Direction.LEFT , Direction.SOUTHWEST, Direction.DOWN , Direction.SOUTHEAST , Direction.RIGHT};
		for(int i = 0; i < angles.length && i < result.length; i++){
			assertTrue(Direction.getDirection(angles[i]) == result[i]);
		}
	}
	
	public static void testGetDirectionCalculateMethod(float[] x , float[] y , Direction result){
		for(int i = 0; i < x.length; i++){
			for( int j = y.length - 1 ; j >= 0; j--){
			if(Direction.getDirection(x[i], y[j]) != result){
				GameLogger.getLogger().warning(Direction.getDirection(x[i], y[j]) + " != " + result); 
			}
			assertTrue(Direction.getDirection(x[i], y[j]) == result);
			}
		}
	}
	public static void testAddDirectionArray(Direction[] dir){
		for(int i = 0; i + 1 < dir.length; i = i + 2){
		assertTrue(dir[i].add(dir[i+ 1]) == dir[dir.length - 1] && dir[i + 1].add(dir[i]) == dir[dir.length - 1] );
		if (!(dir[i].add(dir[i+ 1]) == dir[dir.length - 1] && dir[i + 1].add(dir[i]) == dir[dir.length - 1] )){
			GameLogger.getLogger().warning(dir[i] + " " + dir[i + 1] + "!= "  + dir[dir.length - 1] );
		}
		}
	}

}

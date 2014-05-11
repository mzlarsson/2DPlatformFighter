package edu.chalmers.brawlbuddies.model.skills;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Velocity;

public class PushEffectTest {
	
	@Test
	public void test() {
		PushEffect testEffect1 = new PushEffect(1, new Velocity(1, 1) , 1);
		Velocity nor1 = new Velocity(1,1).getNormalized();
		Dummy bob = new Dummy(100);
		testEffect1.setCreatorID(5);
		bob.setID(5);
		assertTrue(bob.getPushedVelocity().getX() == 0 && bob.getPushedVelocity().getY() == 0);
		testEffect1.effect(null, bob);
		testEffect1.setCreatorID(4);
		testEffect1.effect(null, bob);
		assertTrue(bob.getPushedVelocity().getX() == nor1.getX() && 
				bob.getPushedVelocity().getY() == nor1.getY());
		
		PushEffect testEffect2 = new PushEffect(3 , null , 0);
		bob.resetPushedVelocity();
		bob.setVelocity(new Velocity(2,1));
		Velocity nor2 = new Velocity(2, 1).getNormalized().scale(3);
		testEffect2.setCreatorID(0);
		testEffect2.effect(bob, bob);
		assertTrue(bob.getPushedVelocity().getX() == nor2.getX() &&
		bob.getPushedVelocity().getY() == nor2.getY());
		
		PushEffect testEffect3 = new PushEffect(3 , null , 10);
		testEffect3.setCreatorID(0);
		bob.resetPushedVelocity();
		bob.setVelocity(new Velocity(-1 , -1));
		Velocity nor3 =( new Velocity(-1, -1)).getNormalized().scale(3);
		nor3.setTheta(nor3.getTheta() + 10);
		testEffect3.effect(bob, bob);
		assertTrue(bob.getPushedVelocity().getX() == nor3.getX() &&
				bob.getPushedVelocity().getY() == nor3.getY());
		
		
	}

}

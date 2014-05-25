package edu.chalmers.brawlbuddies.model.skills;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.world.Character;
import edu.chalmers.brawlbuddies.model.world.Health;
import edu.chalmers.brawlbuddies.model.world.Movement;
import edu.chalmers.brawlbuddies.model.world.Projectile;

/**
 * A test class for PushEffect
 * @author David Gustafsson
 *
 */
public class PushEffectTest {
	private Character bob = new Character(new Rectangle(10, 10, 10, 10), 1, new Position(10 , 10) );

	@Test
	public void testPushWithSameID(){
		PushEffect testEffect1 = new PushEffect(1, new Velocity(10, 10) , 1);
		bob.setHealth(new Health(1000, 1000, 1));
		assertTrue(bob.getTotalVelocity().x == 0 & bob.getTotalVelocity().y == 0);
		testEffect1.setCreatorID(bob.getID());
		testEffect1.effect(bob, bob);
		assertTrue(bob.getTotalVelocity().x == 0 & bob.getTotalVelocity().y == 0);
		bob.reset();
	}
	
	@Test
	public void testPushWithDiffrentID(){
		PushEffect testEffect1 = new PushEffect(1, new Velocity(10, 10) , 1);
		Velocity nor1 = new Velocity(10,10).getNormalized();
		bob.setHealth(new Health(1000, 1000, 1));
		testEffect1.setCreatorID(bob.getID() + 1);
		testEffect1.effect(bob, bob);
		assertTrue(bob.getTotalVelocity().x == nor1.x && bob.getTotalVelocity().y == nor1.y);		
		bob.reset();
		
	}
	
	@Test
	public void testPushWithDifferentIDAndAScale(){
		bob.setHealth(new Health(1000, 1000, 1));
		PushEffect testEffect2 = new PushEffect(2, new Velocity(10, 10), 1);
		Velocity nor2 = new Velocity(10,10).getNormalized().scale(2);
		testEffect2.effect(bob, bob);
		assertTrue(bob.getTotalVelocity().x == nor2.x && bob.getTotalVelocity().y == nor2.y);
		bob.reset();
		
	}
	@Test
	public void testSelfCast(){
		bob.setHealth(new Health(1000, 1000, 1));
		PushEffect testEffect3 = new PushEffect(3, null , 0);
		bob.setAim(new Position(10, 10), false);
		Aim a =  bob.getAim().copy();
		Velocity nor3 = new Velocity(a.x , a.y).getNormalized().scale(3);
		testEffect3.effect(null, bob);
		assertTrue(bob.getTotalVelocity().x == nor3.x && bob.getTotalVelocity().y == nor3.y);
		
	}
	
	@Test
	public void testWithProjectileAsSender() {
		bob.setHealth(new Health(1000, 1000, 1));
		PushEffect testEffect3 = new PushEffect(3, null , 0);
		Projectile testProjectile = new Projectile(new Rectangle(10, 10, 10 , 10), new Movement() , 0, 0, new ArrayList<IEffect>());
		bob.reset();
		testProjectile.update(100);
		Velocity nor4 = testProjectile.getTotalVelocity().getNormalized().scale(3);
		testEffect3.effect(testProjectile, bob);
		assertTrue(bob.getTotalVelocity().x == nor4.x && bob.getTotalVelocity().y == nor4.y);
		
	}

}

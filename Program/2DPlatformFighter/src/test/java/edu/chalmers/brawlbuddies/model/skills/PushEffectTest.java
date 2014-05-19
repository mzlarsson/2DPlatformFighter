package edu.chalmers.brawlbuddies.model.skills;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void test() {
		PushEffect testEffect1 = new PushEffect(1, new Velocity(10, 10) , 1);
		Velocity nor1 = new Velocity(10,10).getNormalized();
		Character bob = new Character(new Rectangle(10, 10, 10, 10), 1, new Position(10 , 10) );
		bob.setHealth(new Health(1000, 1000, 1));
		assertTrue(bob.getTotalVelocity().x == 0 & bob.getTotalVelocity().y == 0);
		testEffect1.setCreatorID(1);
		testEffect1.effect(bob, bob);
		assertTrue(bob.getTotalVelocity().x == 0 & bob.getTotalVelocity().y == 0);
		testEffect1.setCreatorID(5);
		testEffect1.effect(bob, bob);
		assertTrue(bob.getTotalVelocity().x == nor1.x && bob.getTotalVelocity().y == nor1.y);		
		bob.reset();
		
		
		PushEffect testEffect2 = new PushEffect(2, new Velocity(10, 10), 1);
		Velocity nor2 = new Velocity(10,10).getNormalized().scale(2);
		testEffect2.effect(bob, bob);
		assertTrue(bob.getTotalVelocity().x == nor2.x && bob.getTotalVelocity().y == nor2.y);
		
		bob.reset();
		PushEffect testEffect3 = new PushEffect(3, null , 0);
		bob.setAim(new Position(10, 10), false);
		Aim a =  bob.getAim().copy();
		Velocity nor3 = new Velocity(a.x , a.y).getNormalized().scale(3);
		testEffect3.effect(null, bob);
		assertTrue(bob.getTotalVelocity().x == nor3.x && bob.getTotalVelocity().y == nor3.y);
		
		Projectile testProjectile = new Projectile(new Rectangle(10, 10, 10 , 10), new Movement() , 0, 0, new ArrayList<IEffect>());
		bob.reset();
		testProjectile.update(100);
		Velocity nor4 = testProjectile.getTotalVelocity().getNormalized().scale(3);
		testEffect3.effect(testProjectile, bob);
		assertTrue(bob.getTotalVelocity().x == nor4.x && bob.getTotalVelocity().y == nor4.y);
		
	}

}

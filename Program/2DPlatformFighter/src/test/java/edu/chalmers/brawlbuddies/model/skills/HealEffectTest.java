package edu.chalmers.brawlbuddies.model.skills;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.Character;
import edu.chalmers.brawlbuddies.model.world.Health;
/**
 * A test class for HealEffect
 * @author David Gustafsson
 *
 */
public class HealEffectTest {
	@Test
	public void test() {
		HealEffect heal = new HealEffect(600);		
		Character bob = new Character(new Rectangle(10, 10, 10, 10), 1, new Position(10 , 10) );
		bob.setHealth(new Health(1000, 1000 , 2));
		bob.takeDamage(500);
		assertTrue(bob.getHealth() == 500);
		heal.effect(null, bob); 
		assertTrue(bob.getHealth() == 1000);
		bob.takeDamage(700);
		heal.setCreatorID(1);
		heal.effect(bob, bob);
		assertTrue(bob.getHealth() == 300);
		heal.setCreatorID(5);
		heal.effect(bob, bob);
		assertTrue(bob.getHealth() == 900);
	}

}

package edu.chalmers.brawlbuddies.model.skills;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.Health;
import edu.chalmers.brawlbuddies.model.world.Character;
/**
 * A test class for DamageEffect
 * @author David Gustafsson
 *
 */
public class DamageEffectTest {
	
	@Test
	public void test() {
	DamageEffect damage = new DamageEffect(500f);
	// Test if the effect will damage a object when creatorID is different from
	// target ID
	Character bob = new Character(new Rectangle(10, 10, 10, 10), 1, new Position(10 , 10) );
	bob.setHealth(new Health(1000, 1000 , 2));
	damage.setCreatorID(2);
	damage.effect(null, bob);
	assertTrue(bob.getHealth() == 500);
	
	// Test if the effect will damage a object when creatorID is the same as
	// the target ID
	damage.setCreatorID(1);
	bob.restoreHealth();
	damage.effect(null, bob);
	assertTrue(bob.getHealth() == 1000);
	}

}

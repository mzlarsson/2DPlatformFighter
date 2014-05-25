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
	

	private DamageEffect damage = new DamageEffect(500f);
	private Character bob = new Character(new Rectangle(10, 10, 10, 10), 1, new Position(10 , 10) );
	
	@Test
	public void testDamageWhenDifferentID() {
	bob.setHealth(new Health(1000, 1000 , 5));
	damage.setCreatorID(bob.getID() + 1);
	damage.effect(null, bob);
	System.out.println(bob.getHealth());
	assertTrue(bob.getHealth() == 500);
	}
	
	@Test
	public void testDamageWhenSameID(){
	bob.setHealth(new Health(1000, 1000 , 2));
	damage.setCreatorID(bob.getID());
	bob.restoreHealth();
	damage.effect(null, bob);
	assertTrue(bob.getHealth() == 1000);
	}

}

package edu.chalmers.brawlbuddies.model.skills;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.chalmers.brawlbuddies.model.world.ICharacter;
import edu.chalmers.brawlbuddies.services.factories.CharacterFactory;
/**
 * A class to test DamageEffect
 * @author David Gustafsson
 *
 */
public class DamageEffectTest {
	
	@Test
	public void test() {
	DamageEffect damage = new DamageEffect(500f);
	// Test if the effect will damage a object when creatorID is different from
	// target ID
	Dummy bob = new Dummy(1000);
	damage.setCreatorID(1);
	bob.setID(5);
	damage.effect(null, bob);
	assertTrue(bob.getMissingHealth() == 500);
	
	// Test if the effect will damage a object when creatorID is the same as
	// the target ID
	damage.setCreatorID(5);
	bob.restoreHealth();
	damage.effect(null, bob);
	assertTrue(bob.getMissingHealth() == 0);
	}

}

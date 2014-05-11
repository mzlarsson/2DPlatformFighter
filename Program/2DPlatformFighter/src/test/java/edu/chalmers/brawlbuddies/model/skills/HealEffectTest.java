package edu.chalmers.brawlbuddies.model.skills;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * A class to test the HealEffect
 * @author David Gustafsson
 *
 */
public class HealEffectTest {
	@Test
	public void test() {
		HealEffect heal = new HealEffect(600);
		DamageEffect damage = new DamageEffect(500);
		Dummy bob = new Dummy(1000);
		bob.setID(5);
		damage.effect(null, bob);
		assertTrue(bob.getMissingHealth() == 500);
		heal.effect(null, bob); 
		assertTrue(bob.getMissingHealth() == 0);
		assertTrue(bob.getHealth() == 1000);
	}

}

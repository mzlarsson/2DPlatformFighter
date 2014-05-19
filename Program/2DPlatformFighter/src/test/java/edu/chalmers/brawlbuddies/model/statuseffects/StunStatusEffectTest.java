package edu.chalmers.brawlbuddies.model.statuseffects;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * A test class for StunStatusEffect 
 * @author David Gustafsson
 *
 */
public class StunStatusEffectTest {

	@Test
	public void test() {
		StatusEffectList list = new StatusEffectList();
		StunStatusEffect test = new StunStatusEffect(1000);
		list.add(test);
		assertTrue(!list.canMove());
		assertTrue(!list.canJump());
		assertTrue(!list.canUseSkill());
	}

}

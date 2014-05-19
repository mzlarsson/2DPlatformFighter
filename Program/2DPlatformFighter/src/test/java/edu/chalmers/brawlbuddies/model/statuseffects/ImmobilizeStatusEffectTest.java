package edu.chalmers.brawlbuddies.model.statuseffects;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * A test class for ImmobilizeStatusEffect
 * @author David Gustafsson
 *
 */
public class ImmobilizeStatusEffectTest {

	@Test
	public void test() {
		StatusEffectList list = new StatusEffectList();
		ImmobilizeStatusEffect test = new ImmobilizeStatusEffect(1000);
		list.add(test);
		assertTrue(!list.canMove());
		assertTrue(!list.canJump());
		assertTrue(list.canUseSkill());
		
	}

}

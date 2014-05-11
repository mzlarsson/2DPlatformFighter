package edu.chalmers.brawlbuddies.model.skills;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.chalmers.brawlbuddies.model.statuseffects.DamageStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.IStatusEffect;

public class SEEffectTest {
	private SEEffect testEffect = new SEEffect( new DamageStatusEffect(100 , 10 , 10));
	
	@Test
	public void test() {
		Dummy bob = new Dummy(1000);
		List<IStatusEffect> list = bob.getStatusEffects();
		assertTrue(list.isEmpty());
		bob.setID(0);
		testEffect.setCreatorID(0);
		testEffect.effect(null, bob);
		assertTrue(list.isEmpty());
		bob.setID(5);
		testEffect.effect(null, bob);
		assertTrue(!list.isEmpty());
	}

}

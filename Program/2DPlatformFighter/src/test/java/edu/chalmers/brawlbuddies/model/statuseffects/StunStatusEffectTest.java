package edu.chalmers.brawlbuddies.model.statuseffects;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.Character;
/**
 * A test class for StunStatusEffect 
 * @author David Gustafsson
 *
 */
public class StunStatusEffectTest {

	@Test
	public void testWithUpdate() {
		Character bob = new Character(new Rectangle(10, 10 ,10 ,10 ), 1 , new Position(0, 0));
		StatusEffectList list = new StatusEffectList();
		StunStatusEffect test = new StunStatusEffect(100);
		list.add(test);
		assertTrue(!list.canMove());
		assertTrue(!list.canJump());
		assertTrue(!list.canUseSkill());
		list.update(100, bob);
		assertTrue(list.canJump());
		assertTrue(list.canMove());
		assertTrue(list.canUseSkill());
	}

}

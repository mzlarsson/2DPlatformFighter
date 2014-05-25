package edu.chalmers.brawlbuddies.model.statuseffects;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.skills.SEEffect;
import edu.chalmers.brawlbuddies.model.skills.Skill;
import edu.chalmers.brawlbuddies.model.world.Character;
import edu.chalmers.brawlbuddies.model.world.Health;
/**
 * A test class for ImmobilizeStatusEffect
 * @author David Gustafsson
 *
 */
public class ImmobilizeStatusEffectTest {

	@Test
	public void testWithUpdates() {
		Character bob = new Character(new Rectangle(10, 10 ,10 ,10 ), 1 , new Position(0, 0));
		StatusEffectList list = new StatusEffectList();
		ImmobilizeStatusEffect test = new ImmobilizeStatusEffect(100);
		list.add(test);
		assertTrue(!list.canMove());
		assertTrue(!list.canJump());
		assertTrue(list.canUseSkill());
		list.update(100, bob);
		assertTrue(list.canJump());
		assertTrue(list.canMove());
		assertTrue(list.canUseSkill());
		
	}

}

package edu.chalmers.brawlbuddies.model.statuseffects;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.skills.Skill;
import edu.chalmers.brawlbuddies.model.world.Character;
import edu.chalmers.brawlbuddies.model.world.Health;
import edu.chalmers.brawlbuddies.model.Position;
/**
 * A test class for DamageStatusEffect
 * @author David Gustafsson
 *
 */
public class DamageStatusEffectTest {

	@Test
	public void testDamageStatusEffectAtDifferentUpdate() {
		DamageStatusEffect testEffect1 = new DamageStatusEffect(100 , 10 , 10);
		Character bob = new Character(new Rectangle(10, 10 ,10 ,10 ), 1 , new Position(0, 0));
		bob.setHealth(new Health(1000, 1));
		bob.applyStatusEffect(testEffect1);
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		bob.update(49);
		assertTrue(bob.getHealth() == 960);
		bob.update(1);
		assertTrue(bob.getHealth() == 950);
		bob.update(75);
		assertTrue(bob.getHealth() == 900);
		
	}

}

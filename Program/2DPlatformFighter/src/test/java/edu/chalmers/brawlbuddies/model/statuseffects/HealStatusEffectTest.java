package edu.chalmers.brawlbuddies.model.statuseffects;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.skills.DamageEffect;
import edu.chalmers.brawlbuddies.model.skills.Skill;
import edu.chalmers.brawlbuddies.model.world.Character;
import edu.chalmers.brawlbuddies.model.world.Health;

/**
 * A class for testing HealthStatusEffect
 * @author David Gustafsson
 *
 */
public class HealStatusEffectTest {

	@Test
	public void test() {
		Character bob = new Character(new Rectangle(10, 10 ,10 ,10 ), 1 , new Position(0, 0));
		bob.setHealth(new Health(1000, 1));
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		DamageEffect testDamage = new DamageEffect(500);
		testDamage.setCreatorID(5);
		testDamage.effect(null, bob);
		assertTrue(bob.getHealth()== 500);
		HealStatusEffect testEffect1 = new HealStatusEffect(100, 1, 1);
		bob.applyStatusEffect(testEffect1);
		bob.update(50);
		assertTrue(bob.getHealth() == 550);
		HealStatusEffect testEffect2 = new HealStatusEffect(100, 1 , 10);
		bob.applyStatusEffect(testEffect2);
		bob.update(10);
		assertTrue(bob.getHealth() == 660);
		bob.update(90);
		assertTrue(bob.getHealth() == 1000);
	}

}

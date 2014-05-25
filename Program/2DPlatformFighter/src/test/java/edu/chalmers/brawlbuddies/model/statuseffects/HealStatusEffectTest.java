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
	private Character bob = new Character(new Rectangle(10, 10 ,10 ,10 ), 1 , new Position(0, 0));
	
	@Test
	public void testHealthStatusEffectAtDifferentUpdateIntervall() {
		bob.setHealth(new Health(1000, 1));
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		bob.takeDamage(500);
		assertTrue(bob.getHealth()== 500);
		HealStatusEffect testEffect1 = new HealStatusEffect(100, 10, 10);
		bob.applyStatusEffect(testEffect1);
		bob.update(49);
		assertTrue(bob.getHealth() == 540);
		bob.update(1);
		assertTrue(bob.getHealth() == 550);
		bob.update(100);
		assertTrue(bob.getHealth() == 600);
		
	}

}

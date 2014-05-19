package edu.chalmers.brawlbuddies.model.statuseffects;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.skills.Skill;
import edu.chalmers.brawlbuddies.model.world.Character;
import edu.chalmers.brawlbuddies.model.world.Health;

/**
 * A test class for DamageImmunityStatusEffect
 * @author David Gustafsson
 *
 */
public class DamageImmunityStatusEffectTest {

	@Test
	public void test() {
		Character bob = new Character(new Rectangle(10, 10 ,10 ,10 ), 1 , new Position(0, 0));
		bob.setHealth(new Health(1000, 1));
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		
		DamageImmunityStatusEffect test = new DamageImmunityStatusEffect(10, 1);
		bob.applyStatusEffect(test);
		bob.takeDamage(1000);
		assertTrue(bob.getHealth() == 1000);
		bob.update(10);
		bob.takeDamage(10);
		assertTrue(bob.getHealth() == 990);
	}

}

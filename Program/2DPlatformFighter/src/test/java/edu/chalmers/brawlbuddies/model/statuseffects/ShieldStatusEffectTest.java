package edu.chalmers.brawlbuddies.model.statuseffects;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.skills.Skill;
import edu.chalmers.brawlbuddies.model.world.Character;
import edu.chalmers.brawlbuddies.model.world.Health;
/**
 * A test class for ShieldStatusEffect
 * @author David Gustafsson
 *
 */
public class ShieldStatusEffectTest {

	@Test
	public void test() {
		Character bob = new Character(new Rectangle(10, 10 ,10 ,10 ), 1 , new Position(0, 0));
		bob.setHealth(new Health(1000, 1));
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		
		ShieldStatusEffect test1 = new ShieldStatusEffect(100, 2, 100);
		bob.applyStatusEffect(test1.copy());
		bob.takeDamage(100);
		assertTrue(bob.getHealth() == 1000);
		bob.takeDamage(100);
		assertTrue(bob.getHealth() == 900);
		
		bob.applyStatusEffect(test1.copy());
		bob.takeDamage(150);
		assertTrue(bob.getHealth() == 850);
		
		DamageImmunityStatusEffect test2 = new DamageImmunityStatusEffect(10, 1);
		bob.applyStatusEffect(test1.copy());
		bob.applyStatusEffect(test2.copy());
		bob.takeDamage(110);
		bob.update(10);
		bob.takeDamage(110);
		assertTrue(bob.getHealth() == 840);
		
		bob.applyStatusEffect(test1);
		bob.update(100);
		bob.takeDamage(10);
		assertTrue(bob.getHealth() == 830);
	
	}

}

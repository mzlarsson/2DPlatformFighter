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
	public void testWthSheildAmountHigherThanDamage(){
		Character bob = new Character(new Rectangle(10, 10 ,10 ,10 ), 1 , new Position(0, 0));
		bob.setHealth(new Health(1000, 1));
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		ShieldStatusEffect test = new ShieldStatusEffect(100, 2, 200);
		bob.applyStatusEffect(test.copy());
		bob.takeDamage(100);
		assertTrue(bob.getHealth() == 1000);
		
	}

	@Test
	public void testWithDurationGone(){
		Character bob = new Character(new Rectangle(10, 10 ,10 ,10 ), 1 , new Position(0, 0));
		bob.setHealth(new Health(1000, 1));
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		ShieldStatusEffect test = new ShieldStatusEffect(100, 2, 200);
		bob.applyStatusEffect(test.copy());
		bob.update(100);
		bob.takeDamage(100);
		assertTrue(bob.getHealth() == 900);
		
	}

	@Test
	public void testWithShieldAmountLowerThanDamage(){
		Character bob = new Character(new Rectangle(10, 10 ,10 ,10 ), 1 , new Position(0, 0));
		bob.setHealth(new Health(1000, 1));
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		ShieldStatusEffect test = new ShieldStatusEffect(100, 2, 50);
		bob.applyStatusEffect(test.copy());
		bob.takeDamage(100);
		assertTrue(bob.getHealth() == 950);
		
	}
	@Test
	public void testTwoShieldWithDifferentPriority(){
		Character bob = new Character(new Rectangle(10, 10 ,10 ,10 ), 1 , new Position(0, 0));
		bob.setHealth(new Health(1000, 1));
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		
		ShieldStatusEffect test1 = new ShieldStatusEffect(100, 2, 100);
		bob.applyStatusEffect(test1.copy());
		ShieldStatusEffect test2 = new ShieldStatusEffect(10, 1, 1000);
		bob.applyStatusEffect(test2.copy());
		bob.takeDamage(110);
		bob.update(10);
		bob.takeDamage(110);
		assertTrue(bob.getHealth() == 990);
		
	}
}

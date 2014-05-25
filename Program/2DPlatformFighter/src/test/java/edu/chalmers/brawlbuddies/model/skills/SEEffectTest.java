package edu.chalmers.brawlbuddies.model.skills;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.statuseffects.DamageStatusEffect;
import edu.chalmers.brawlbuddies.model.world.Character;
import edu.chalmers.brawlbuddies.model.world.Health;
/**
 * A test class for SEEffectTest
 * @author David Gustafsson
 *
 */
public class SEEffectTest {
	private Character bob = new Character(new Rectangle(10, 10, 10, 10), 1, new Position(10 , 10) );
	
	@Test 
	public void testWithSameID(){
	bob.setHealth(new Health(1000 , 1000, 1));
	SEEffect testEffect = new SEEffect( new DamageStatusEffect(100 , 10 , 10));
	Skill skill = new Skill(0, 0 , 0 , 0, null );
	Skill[] skills = {skill};
	bob.setSkills(skills);
	testEffect.setCreatorID(bob.getID());
	testEffect.effect(bob, bob);
	bob.update(10);
	assertTrue(bob.getHealth() == 1000);
	bob.reset();
	}
	@Test
	public void testWithDifferentID(){
		bob.setHealth(new Health(1000 , 1000, 1));
		SEEffect testEffect = new SEEffect( new DamageStatusEffect(100 , 10 , 10));
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		testEffect.setCreatorID(bob.getID() + 1);
		testEffect.effect(bob, bob);
		bob.update(10);
		assertTrue(bob.getHealth() == 990);
		bob.reset();
	}
	@Test
	public void testSelfCast(){
		bob.setHealth(new Health(1000 , 1000, 1));
		SEEffect testEffect = new SEEffect( new DamageStatusEffect(100 , 10 , 10));
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		testEffect.setCreatorID(bob.getID());
		testEffect.effect(null, bob);
		bob.update(10);
		assertTrue(bob.getHealth() == 990);
		bob.reset();
	}

}

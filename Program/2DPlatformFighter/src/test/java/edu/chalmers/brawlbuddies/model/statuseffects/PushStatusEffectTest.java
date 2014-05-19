package edu.chalmers.brawlbuddies.model.statuseffects;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.skills.Skill;
import edu.chalmers.brawlbuddies.model.world.Character;
import edu.chalmers.brawlbuddies.model.world.Health;

/**
 * A test for PushStatusEffect
 * @author David Gustafsson
 *
 */
public class PushStatusEffectTest {

	@Test
	public void test() {
		Character bob = new Character(new Rectangle(10, 10 ,10 ,10 ), 1 , new Position(0, 0));
		bob.setHealth(new Health(1000, 1));
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		bob.setAim(new Position(10, 10), false);
		PushStatusEffect test1 = new PushStatusEffect(100, 1 ,null, 0);
		bob.applyStatusEffect(test1);
		bob.update(10);
		assertTrue(bob.getTotalVelocity().getX() == bob.getAim().getNormalized().scale(1).getX());
		bob.update(90);
		assertTrue(bob.getTotalVelocity().getX() == 0);
		
		
		PushStatusEffect test2 = new PushStatusEffect(100, 1, new Aim(30,30),  0);
		bob.applyStatusEffect(test2);
		bob.update(10);
		assertTrue(bob.getTotalVelocity().getX() == new Aim(30, 30).getNormalized().scale(1).getX());

	}

}

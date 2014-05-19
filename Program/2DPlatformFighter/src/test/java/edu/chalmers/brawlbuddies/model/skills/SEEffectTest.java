package edu.chalmers.brawlbuddies.model.skills;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.statuseffects.DamageStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.IStatusEffect;
import edu.chalmers.brawlbuddies.model.world.Character;
import edu.chalmers.brawlbuddies.model.world.Health;

public class SEEffectTest {
	private SEEffect testEffect = new SEEffect( new DamageStatusEffect(100 , 10 , 10));
	
	@Test
	public void test() {
		Character bob = new Character(new Rectangle(10, 10, 10, 10), 1, new Position(10 , 10) );
		bob.setHealth(new Health(1000, 1000, 1));
		SEEffect testEffect = new SEEffect( new DamageStatusEffect(100 , 10 , 10));
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		testEffect.setCreatorID(1);
		testEffect.effect(bob, bob);
		testEffect.effect(null, bob);
		bob.update(10);
		assertTrue(bob.getHealth() == 1000);
		testEffect.setCreatorID(5);
		testEffect.effect(bob, bob);
		bob.update(10);
		assertTrue(bob.getHealth() == 990);
		bob.reset(); 
		testEffect.effect(null, bob);
		bob.update(10);
		assertTrue(bob.getHealth() == 990);
		
	}

}

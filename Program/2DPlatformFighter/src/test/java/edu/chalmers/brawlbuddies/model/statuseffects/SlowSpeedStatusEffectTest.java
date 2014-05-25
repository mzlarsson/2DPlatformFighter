package edu.chalmers.brawlbuddies.model.statuseffects;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.skills.Skill;
import edu.chalmers.brawlbuddies.model.world.Character;
import edu.chalmers.brawlbuddies.model.world.Health;
/**
 * A test class for SlowSpeedStatusEffect
 * @author David Gustafsson
 *
 */
public class SlowSpeedStatusEffectTest {
	@Test
	public void testWhenVelocityIsNull(){
		Character bob = new Character(new Rectangle(10, 10 ,10 ,10 ), 1 , new Position(0, 0));
		bob.setHealth(new Health(1000, 1));
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		bob.resetGravity();
		bob.setBaseSpeed(new Velocity (100, 100));
		bob.move(Direction.RIGHT);
		
		SlowSpeedStatusEffect test1 = new SlowSpeedStatusEffect(100, 1, null);
		bob.applyStatusEffect(test1);
		assertTrue(bob.getTotalVelocity().getX() == 100);
		bob.update(10);
		assertTrue(bob.getTotalVelocity().getX() == 200);
		bob.update(90);
		assertTrue(bob.getTotalVelocity().getX() == 100);
	}
	@Test
	public void testWhenVelocityIsSet() {
		Character bob = new Character(new Rectangle(10, 10 ,10 ,10 ), 1 , new Position(0, 0));
		bob.setHealth(new Health(1000, 1));
		Skill skill = new Skill(0, 0 , 0 , 0, null );
		Skill[] skills = {skill};
		bob.setSkills(skills);
		bob.resetGravity();
		bob.setBaseSpeed(new Velocity (100, 100));
		bob.move(Direction.RIGHT);
		
		SlowSpeedStatusEffect test2 = new SlowSpeedStatusEffect(100, 0 , new Velocity(0 , -100));
		bob.applyStatusEffect(test2);
		assertTrue(bob.getTotalVelocity().getX() == 100);
		bob.update(1);
		bob.resetGravity();
		assertTrue(bob.getTotalVelocity().getY() == -100);
	}

}

package edu.chalmers.brawlbuddies.model.world;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.skills.DamageAble;
import edu.chalmers.brawlbuddies.model.skills.HealAble;
import edu.chalmers.brawlbuddies.model.skills.PushAble;
import edu.chalmers.brawlbuddies.model.statuseffects.IStatusEffect;

/**
 * A interface for Character
 * @author David Gustafsson
 * @revised Matz Larsson
 *
 */
public interface ICharacter extends IGameObject, HealAble, DamageAble, PushAble{
	
	public Aim getAim();
	public Position getProjFirePos();
	public void move(Direction dir);
	public void makeJump();
	public void setAim(Position aimPosition, boolean isRelative);
	public void activateSkill(int skillIndex);
	public void reset();
	
	public Direction getDirection();
	public boolean isInAir();
	
	public void applyStatusEffect(IStatusEffect copy);
	public void addScale(float scale);
	public void removeScale(float scale);
	public void restoreScale();
	public void resetGravity();
	public void addSpeed(Velocity velocity);
	public void removeSpeed(Velocity velocity);

}

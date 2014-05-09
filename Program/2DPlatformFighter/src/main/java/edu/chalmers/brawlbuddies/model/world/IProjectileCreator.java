package edu.chalmers.brawlbuddies.model.world;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Position;

public interface IProjectileCreator {
	public void setCreatorID(int a);
	public IProjectile fire(Position pos, Aim aim);
}

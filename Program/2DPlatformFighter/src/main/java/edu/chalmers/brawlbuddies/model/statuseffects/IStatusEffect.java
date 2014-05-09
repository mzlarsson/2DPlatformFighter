package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.world.ICharacter;

public interface IStatusEffect {
	
	public void update(ICharacter c , float delta);
	public boolean isActive();
	public float getDuration();
	public IStatusEffect copy();

}

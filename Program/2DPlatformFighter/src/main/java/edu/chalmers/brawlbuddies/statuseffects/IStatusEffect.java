package edu.chalmers.brawlbuddies.statuseffects;

import edu.chalmers.brawlbuddies.model.Skills.ICharacter;

public interface IStatusEffect {
public void update(ICharacter c , float delta);
public boolean isActive();
public float getDuration();
public IStatusEffect copy();

}

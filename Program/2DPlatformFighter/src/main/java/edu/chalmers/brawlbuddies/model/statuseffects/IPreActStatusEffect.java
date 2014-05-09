package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.statuseffects.StatusEffectList.Action;

public interface IPreActStatusEffect extends IStatusEffect {
	boolean canAct(Action move);
}

package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.statuseffects.StatusEffectList.Actions;

public interface IPreActStatusEffect extends IStatusEffect {
	boolean canAct(Actions move);
}

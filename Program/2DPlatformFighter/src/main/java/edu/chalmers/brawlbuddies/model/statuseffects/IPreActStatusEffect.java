package edu.chalmers.brawlbuddies.model.statuseffects;

import edu.chalmers.brawlbuddies.model.statuseffects.StatusEffectList.Action;
/**
 * A interface describing a status effect that will effect the action a character can take
 * @author David Gustafsson
 *
 */
public interface IPreActStatusEffect extends IStatusEffect {
	/**
	 * Returns if the action is permitted by the status effect.
	 * @param act - the action that is to be checked
	 * @return boolean - false if the action is not permitted by the status effect
	 */
	boolean canAct(Action act);
}

package edu.chalmers.brawlbuddies.model.statuseffects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.chalmers.brawlbuddies.model.world.ICharacter;
/**
 * A class that describes a list that handles a characters status effects. 
 * @author David Gustafsson
 * @version 1.0
 *
 */
public class StatusEffectList {
	private List<IStatusEffect> statusEffects = new ArrayList<IStatusEffect>();
	private List<IStatusEffect> movementEffecting = new ArrayList<IStatusEffect>();
	private List<IPreDamageStatusEffect> preDamage = new ArrayList<IPreDamageStatusEffect>();
	private List<IPreActStatusEffect> preAct = new ArrayList<IPreActStatusEffect>();
	
	/**
	 * A enum to describe a characters actions.
	 * @author David Gustafsson
	 *
	 */
	public enum Action{
		SKILL,MOVE,JUMP
	}
	/**
	 * Add a effect to the list
	 * @param effect - the effect to be added
	 */
	public void add(IStatusEffect effect) {
		if (effect instanceof SlowSpeedStatusEffect) {
			movementEffecting.add(effect);
		} else if (effect instanceof IPreDamageStatusEffect) {
			preDamage.add((IPreDamageStatusEffect) effect);
			sortPreDamage();
		}
		else if (effect instanceof IPreActStatusEffect) {
			preAct.add((IPreActStatusEffect) effect);
		} else {
			statusEffects.add(effect);
		}
	}
	
	/**
	 * Remove a effect to the list
	 * @param effect - the effect to be removed
	 */
	public void remove(IStatusEffect effect) {
		if (effect instanceof SlowSpeedStatusEffect) {
			movementEffecting.remove(effect);
		} else if (effect instanceof IPreDamageStatusEffect) {
			preDamage.remove((IPreDamageStatusEffect) effect);
		}else if (effect instanceof IPreActStatusEffect) {
			preAct.remove((IPreActStatusEffect) effect);
		} else {
			statusEffects.remove(effect);
		}

	}
	
	/**
	 * Remove all status effect of the list
	 */
	public void reset(){
		this.statusEffects.clear();
		this.movementEffecting.clear();
		this.preAct.clear();
		this.preDamage.clear();
	}
	
	/**
	 * Calculate how much damage is absorbed by the status effect in the list
	 * @param damage - the incoming damage.
	 * @return float - the resulting damage.
	 */
	public float calculateDamage(float damage) {
		for (IPreDamageStatusEffect effect : preDamage) {
			damage = effect.calculateDamage(damage);
		}
		for (int i = 0; !preDamage.isEmpty() && i < preDamage.size(); i++){
			if( !preDamage.get(i).isActive()){
				preDamage.remove(i);
				i--;
			}
		}
		return damage;
	}
	
	/**
	 * Sort the preDamage list 
	 */
	private void sortPreDamage() {
		Collections.sort(preDamage);
	}
	
	/**
	 * Return if the current status effect will allow movement
	 * @return boolean - true if the status effects will allow movement
	 */
	public boolean canMove(){
		boolean canMove = true;
		for( IPreActStatusEffect effect: preAct){
			if(!effect.canAct(Action.MOVE)){
				canMove = false;
			}
		}
		return canMove;
	}
	
	/**
	 * Return if the current status effects will allow jumping
	 * @return boolean - true if the status effects will allow jumping
	 */
	public boolean canJump(){
		boolean canJump = true;
		for( IPreActStatusEffect effect: preAct){
			if(!effect.canAct(Action.JUMP)){
				canJump = false;
			}
		}
		return canJump;
	}
	
	/**
	 * Return if the current status effects will allow the use of skills
	 * @return boolean - true if the status effects will allow the use of skills
	 */
	public boolean canUseSkill(){
		boolean canAct = true;
		for( IPreActStatusEffect effect: preAct){
			if(!effect.canAct(Action.SKILL)){
				canAct = false;
			}
		}
		return canAct;
	
	}
	
	/**
	 * Updates the status effect in the list.
	 * @param delta - the time gone since the last update. 
	 * @param c - the character that shall be effected by the status effect.
	 */
	public void update(float delta, ICharacter c) {
		for(int i = 0 ; !statusEffects.isEmpty() && i < statusEffects.size(); i++){
			statusEffects.get(i).update(c, delta);
			if(!statusEffects.get(i).isActive()){
				statusEffects.remove(i);
				i--;
			}
		}
		for(int i = 0; !movementEffecting.isEmpty() && i < movementEffecting.size(); i++){
			movementEffecting.get(i).update(c, delta);
			if(!movementEffecting.get(i).isActive()){
				movementEffecting.remove(i);
				i--;
				if(movementEffecting.isEmpty()){
					c.restoreScale();
				}
			}
		}
		for(int i = 0; !preAct.isEmpty() && i < preAct.size(); i++){
			preAct.get(i).update(c, delta);
			if( !preAct.get(i).isActive()){
				preAct.remove(i);
				i--;
			}
		}
		for (int i = 0; !preDamage.isEmpty() && i < preDamage.size(); i++){
			preDamage.get(i).update(c, delta);
			if( !preDamage.get(i).isActive()){
				preDamage.remove(i);
				i--;
			}
		}
	}
}

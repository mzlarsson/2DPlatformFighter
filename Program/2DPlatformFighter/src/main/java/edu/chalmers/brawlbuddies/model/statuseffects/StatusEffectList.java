package edu.chalmers.brawlbuddies.model.statuseffects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.chalmers.brawlbuddies.model.world.ICharacter;

public class StatusEffectList {
	private List<IStatusEffect> statusEffects = new ArrayList<IStatusEffect>();
	private List<IStatusEffect> movementEffecting = new ArrayList<IStatusEffect>();
	private List<IPreDamageStatusEffect> preDamage = new ArrayList<IPreDamageStatusEffect>();
	private List<IPreMovementStatusEffect> preMove = new ArrayList<IPreMovementStatusEffect>();

	public void add(IStatusEffect effect) {
		if (effect instanceof SlowSpeedStatusEffect) {
			movementEffecting.add(effect);
		} else if (effect instanceof IPreDamageStatusEffect) {
			preDamage.add((IPreDamageStatusEffect) effect);
			sortPreDamage();
		}
		else if (effect instanceof IPreMovementStatusEffect) {
			preMove.add((IPreMovementStatusEffect) effect);
		} else {
			statusEffects.add(effect);
		}
	}

	public void remove(IStatusEffect effect) {
		if (effect instanceof SlowSpeedStatusEffect) {
			movementEffecting.remove(effect);
		} else if (effect instanceof IPreDamageStatusEffect) {
			preDamage.remove((IPreDamageStatusEffect) effect);
		}else if (effect instanceof IPreMovementStatusEffect) {
			preMove.remove((IPreMovementStatusEffect) effect);
		} else {
			statusEffects.remove(effect);
		}

	}

	public float calculateDamage(float damage) {
		for (IPreDamageStatusEffect effect : preDamage) {
			damage = effect.calculateDamage(damage);
		}
		return damage;
	}

	public void sortPreDamage() {
		Collections.sort(preDamage);
	}
	public boolean canMove(){
		boolean canMove = true;
		for( IPreMovementStatusEffect effect: preMove){
			if(!effect.canMove()){
				canMove = false;
			}
		}
		return canMove;
	}
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
		for(int i = 0; !preMove.isEmpty() && i < preMove.size(); i++){
			preMove.get(i).update(c, delta);
			if( !preMove.get(i).isActive()){
				preMove.remove(i);
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

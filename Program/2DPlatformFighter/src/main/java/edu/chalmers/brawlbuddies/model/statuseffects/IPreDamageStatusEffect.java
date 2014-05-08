package edu.chalmers.brawlbuddies.model.statuseffects;


public interface IPreDamageStatusEffect extends IStatusEffect, Comparable<IPreDamageStatusEffect> {
	public float calculateDamage(float damage);
	public int getPriority();
}


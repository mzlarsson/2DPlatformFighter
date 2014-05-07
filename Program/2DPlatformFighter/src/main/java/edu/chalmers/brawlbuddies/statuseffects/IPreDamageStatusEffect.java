package edu.chalmers.brawlbuddies.statuseffects;


public interface IPreDamageStatusEffect extends IStatusEffect, Comparable<IPreDamageStatusEffect> {
	public float calculateDamage(float damage);
	public int getPriority();
}


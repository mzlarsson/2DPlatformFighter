package edu.chalmers.brawlbuddies.services.factories;

import edu.chalmers.brawlbuddies.model.Skills.DamageEffect;
import edu.chalmers.brawlbuddies.model.Skills.Effect;
import edu.chalmers.brawlbuddies.model.Skills.HealEffect;

public class EffectFactory {
	
	/**
	 * Creates an Effect based on the given parameters.
	 * @param effectName The name of the Effect.
	 * @param parameters The parameters for the Effect.
	 * @return
	 */
	public static Effect create(String effectName, String parameters) {
		
		if (effectName.equalsIgnoreCase("damage")) {
			String[] params = parameters.split(",");
			if (params.length == 1) {
				return new DamageEffect(Float.parseFloat(params[0]));
			} else {
				throw new IllegalArgumentException("DamageEffect with: " + params.length
						+ " parameters not supported");
			}
		} else if (effectName.equalsIgnoreCase("heal")) {
			String[] params = parameters.split(",");
			if (params.length == 1) {
				return new HealEffect(Float.parseFloat(params[0]));
			} else {
				throw new IllegalArgumentException("HealEffect with: " + params.length
						+ " parameters not supported");
			}
		} else {
			throw new IllegalArgumentException("The effect: \"" + effectName
					+ "\" is not supported");
		}
	}
}

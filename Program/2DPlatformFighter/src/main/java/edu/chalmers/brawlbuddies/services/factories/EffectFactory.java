package edu.chalmers.brawlbuddies.services.factories;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.Skills.DamageEffect;
import edu.chalmers.brawlbuddies.model.Skills.Effect;
import edu.chalmers.brawlbuddies.model.Skills.HealEffect;
import edu.chalmers.brawlbuddies.model.Skills.PushEffect;

public class EffectFactory {
	
	/**
	 * Creates an Effect based on the given Node.
	 * @param effectNode The node with the parameters.
	 * @return The created effect.
	 */
	public static Effect create(Node effectNode) {
		String effectName = effectNode.getNodeName();
		if (effectName.equalsIgnoreCase("damage")) {
			return new DamageEffect(Float.parseFloat(effectNode.getFirstChild().getNodeValue()));
		} else if (effectName.equalsIgnoreCase("heal")) {
			return new HealEffect(Float.parseFloat(effectNode.getFirstChild().getNodeValue()));
		} else if (effectName.equalsIgnoreCase("push")) {
			NamedNodeMap attributes = effectNode.getAttributes();
			Velocity velocity = null;
			float offset = 0;
			if (attributes.getNamedItem("aim")!=null) {
				String[] veloParams = attributes.getNamedItem("aim").getNodeValue().split(",");
				velocity = new Velocity(Float.parseFloat(veloParams[0]), Float.parseFloat(veloParams[1]));
			}
			if (attributes.getNamedItem("offset")!=null) {
				offset = Float.parseFloat(attributes.getNamedItem("offset").getNodeValue());
			}
			return new PushEffect(Float.parseFloat(effectNode.getFirstChild().getNodeValue()), velocity, offset);
		} else {
			throw new IllegalArgumentException("The effect: \"" + effectName
					+ "\" is not supported");
		}
	}
}

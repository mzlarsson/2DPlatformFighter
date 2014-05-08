package edu.chalmers.brawlbuddies.services.factories;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.skills.DamageEffect;
import edu.chalmers.brawlbuddies.model.skills.Effect;
import edu.chalmers.brawlbuddies.model.skills.HealEffect;
import edu.chalmers.brawlbuddies.model.skills.PushEffect;
import edu.chalmers.brawlbuddies.model.skills.SEEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.DamageImmunityStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.DamageStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.HealStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.PushStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.ShieldStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.SlowSpeedStatusEffect;

public class EffectFactory {
	
	/**
	 * Creates an Effect based on the given Node.
	 * @param effectNode The node with the parameters.
	 * @return The created effect.
	 */
	public static Effect create(Node effectNode) {
		String effectName = effectNode.getNodeName();
		
		// Damage
		if (effectName.equalsIgnoreCase("damage")) {
			return new DamageEffect(Float.parseFloat(effectNode.getFirstChild().getNodeValue()));
		
		// Heal
		} else if (effectName.equalsIgnoreCase("heal")) {
			return new HealEffect(Float.parseFloat(effectNode.getFirstChild().getNodeValue()));
		
		// Push	
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
		
		// Damage over time	
		} else if (effectName.equalsIgnoreCase("damage_over_time")){
			NamedNodeMap attributes = effectNode.getAttributes();
			int duration = 5000;
			int intervall = 1000;
			if( attributes.getNamedItem("duration") != null){
				duration = Integer.parseInt(attributes.getNamedItem("duration").getNodeValue());
			}
			if( attributes.getNamedItem("intervall") != null){
				intervall = Integer.parseInt(attributes.getNamedItem("intervall").getNodeValue());
			}
			return new SEEffect(new DamageStatusEffect(Integer.parseInt(effectNode.getFirstChild().getNodeValue()),1000, 0.5F));
		
		// Heal over time
		} else if(effectName.equalsIgnoreCase("heal_over_time")){
			NamedNodeMap attributes = effectNode.getAttributes();
			int duration = 5000;
			int intervall = 1000;
			if( attributes.getNamedItem("duration") != null){
				duration = Integer.parseInt(attributes.getNamedItem("duration").getNodeValue());
			}
			if( attributes.getNamedItem("intervall") != null){
				intervall = Integer.parseInt(attributes.getNamedItem("intervall").getNodeValue());
			}
			return new SEEffect(new HealStatusEffect(duration, intervall ,Float.parseFloat(effectNode.getFirstChild().getNodeValue())));
		
		// Slow or Speed
		} else if (effectName.equalsIgnoreCase("slow") || effectName.equalsIgnoreCase("speed")){
			NamedNodeMap attributes = effectNode.getAttributes();
			int duration = 5000;
			float scaleAmount = 0;
			Velocity v = null;
			if( attributes.getNamedItem("duration") != null){
				duration = Integer.parseInt(attributes.getNamedItem("duration").getNodeValue());
			}
			if( attributes.getNamedItem("percent") != null){
				scaleAmount += Float.parseFloat(effectNode.getFirstChild().getNodeValue())/ 100;
			}
			//TODO fix this with SlowSpeedStatusEffect
			if( attributes.getNamedItem("static") != null){
				String[] veloParams = attributes.getNamedItem("static").getNodeValue().split(",");
				v = new Velocity( Float.parseFloat(veloParams[0]), Float.parseFloat(veloParams[1]));
			}
			return new SEEffect( new SlowSpeedStatusEffect(duration, scaleAmount, v));	
		
		// Push over time
		} else if(effectName.equals("push_over_time")) {
			NamedNodeMap attributes = effectNode.getAttributes();
			Velocity v = null;
			int duration = 5000;
			int scaleAmount = 1;
			if(attributes.getNamedItem("velocity") != null){
				String[] veloParams = attributes.getNamedItem("static").getNodeValue().split(",");
				v = new Velocity( Float.parseFloat(veloParams[0]), Float.parseFloat(veloParams[1]));	
			}
			if( attributes.getNamedItem("duration") != null){
				duration = Integer.parseInt(attributes.getNamedItem("duration").getNodeValue());
			}
			if( attributes.getNamedItem("strength") != null){
				scaleAmount = Integer.parseInt(attributes.getNamedItem("strength").getNodeValue());
			}
			return new SEEffect(new PushStatusEffect(duration, scaleAmount , v));
		
		// Shield
		}else if( effectName.equalsIgnoreCase("shield")){
			NamedNodeMap attributes = effectNode.getAttributes();
			int duration = 5000;
			int priority = 2;
			if( attributes.getNamedItem("duration") != null){
				duration = Integer.parseInt(attributes.getNamedItem("duration").getNodeValue());
			}
			if (attributes.getNamedItem("priority") != null){
				priority  = Integer.parseInt(attributes.getNamedItem("priority").getNodeValue());
			}
			return new SEEffect(new ShieldStatusEffect(duration , priority , Float.parseFloat(effectNode.getFirstChild().getNodeValue())));	
		
		//Damage Immunity
		}else if( effectName.equalsIgnoreCase("damage_immunity")){
			NamedNodeMap attributes = effectNode.getAttributes();
			int duration = 5000;
			int priority = 1;
			if( attributes.getNamedItem("duration") != null){
				duration = Integer.parseInt(attributes.getNamedItem("duration").getNodeValue());
			}
			if (attributes.getNamedItem("priority") != null){
				priority  = Integer.parseInt(attributes.getNamedItem("priority").getNodeValue());
			}
			return new SEEffect(new DamageImmunityStatusEffect(duration, priority));
		} else {
			throw new IllegalArgumentException("The effect: \"" + effectName
					+ "\" is not supported");
		}
	}
}

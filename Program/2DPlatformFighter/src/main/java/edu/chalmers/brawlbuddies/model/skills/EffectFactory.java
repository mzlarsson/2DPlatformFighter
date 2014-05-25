package edu.chalmers.brawlbuddies.model.skills;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.statuseffects.DamageImmunityStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.DamageStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.HealStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.ImmobilizeStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.PushStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.ShieldStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.SlowSpeedStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.StunStatusEffect;
/**
 * A factory that creates Effect with nodes
 * @author Patrik Haar
 *
 */
public class EffectFactory {
	
	/**
	 * Creates an Effect based on the given Node.
	 * @param effectNode The node with the parameters.
	 * @return The created effect.
	 */
	public static IEffect create(Node effectNode) {
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
			return new SEEffect(new DamageStatusEffect(duration, intervall, Integer.parseInt(effectNode.getFirstChild().getNodeValue())));
		
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
				scaleAmount = (float) (Float.parseFloat(attributes.getNamedItem("percent").getNodeValue())/ 100.0);
			}
			if( attributes.getNamedItem("static") != null){
				String[] veloParams = attributes.getNamedItem("static").getNodeValue().split(",");
				v = new Velocity( Float.parseFloat(veloParams[0]), Float.parseFloat(veloParams[1]));
			}
			return new SEEffect( new SlowSpeedStatusEffect(duration, scaleAmount, v));	
		
		// Push over time
		} else if(effectName.equals("push_over_time")) {
			NamedNodeMap attributes = effectNode.getAttributes();
			Aim aim = null;
			int duration = 5000;
			int power = 1;
			double aimOffset = 0;
			if(attributes.getNamedItem("aim") != null){
				String[] aimParams = attributes.getNamedItem("aim").getNodeValue().split(",");
				aim = new Aim( Float.parseFloat(aimParams[0]), Float.parseFloat(aimParams[1]));	
			}
			if( attributes.getNamedItem("aimOffset") != null){
				aimOffset = Integer.parseInt(attributes.getNamedItem("aimOffset").getNodeValue());
			}
			if( attributes.getNamedItem("duration") != null){
				duration = Integer.parseInt(attributes.getNamedItem("duration").getNodeValue());
			}
			if( attributes.getNamedItem("power") != null){
				power = Integer.parseInt(attributes.getNamedItem("power").getNodeValue());
			}
			return new SEEffect(new PushStatusEffect(duration, power, aim, aimOffset));
		
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
		
		//Stun
		} else if ( effectName.equalsIgnoreCase("stun")){
			NamedNodeMap attributes = effectNode.getAttributes();
			int duration = 5000;
			if( attributes.getNamedItem("duration") != null){
				duration = Integer.parseInt(attributes.getNamedItem("duration").getNodeValue());
			}
			return new SEEffect(new StunStatusEffect(duration));
		
		//Immobilize
		} else if ( effectName.equalsIgnoreCase("immobilize")){
			NamedNodeMap attributes = effectNode.getAttributes();
			int duration = 5000;
			if( attributes.getNamedItem("duration") != null){
				duration = Integer.parseInt(attributes.getNamedItem("duration").getNodeValue());
			}
			return new SEEffect(new ImmobilizeStatusEffect(duration));
		}else {
			throw new IllegalArgumentException("The effect: \"" + effectName
					+ "\" is not supported");
		}
	}
}

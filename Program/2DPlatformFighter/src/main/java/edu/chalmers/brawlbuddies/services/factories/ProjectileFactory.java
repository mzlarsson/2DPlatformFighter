package edu.chalmers.brawlbuddies.services.factories;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Shape;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.Skills.Effect;
import edu.chalmers.brawlbuddies.model.world.ProjectileCreator;

/**
 * A factory for creating ProjectileCreators
 * @author Patrik Haar
 * @version 0.1
 */
public class ProjectileFactory {
	
	/**
	 * Creates a Projectile by reading from the xml file with
	 * the provided name.
	 * @param projName The name of the projectile.
	 * @return The created character with all stats and effects set.
	 */
	public static ProjectileCreator create(String projName) {
		
		Document xmlDoc = XMLReader.getDocument(Constants.PROJECTILES_DATA + projName.toLowerCase() + ".xml");

		Element rootNode = xmlDoc.getDocumentElement();
		
		// Getting the type ID
		int typeID = Integer.parseInt(rootNode.getAttribute("id"));
		
		// Making the hitbox
		NamedNodeMap shapeParams = rootNode.getElementsByTagName("hitbox").item(0).getAttributes();
		Shape shape = ShapeFactory.create(shapeParams.getNamedItem("shape").getNodeValue()
				, shapeParams.getNamedItem("parameters").getNodeValue(), 0, 0);
		
		// Getting the projectile´s speed
		float speed = Float.parseFloat(rootNode
				.getElementsByTagName("speed").item(0).getFirstChild().getNodeValue());
		
		// Getting the lifetime
		float lifetime = Float.parseFloat(rootNode
				.getElementsByTagName("lifetime").item(0).getFirstChild().getNodeValue());
		
		// Getting the affecting gravity
		Velocity gravity = null;
		Node gravNode = rootNode.getElementsByTagName("speed").item(0).getAttributes().getNamedItem("gravity");
		if (gravNode != null) {
			String[] gravParams = gravNode.getNodeValue().split(",");
			gravity = new Velocity(Float.parseFloat(gravParams[0]), Float.parseFloat(gravParams[1]));
		}
		
		// Getting the effects
		NodeList effectList = rootNode.getElementsByTagName("effects").item(0).getChildNodes();
		List<Effect> effects = new ArrayList<Effect>();
		for (int i=0; i<effectList.getLength(); i++) {
			if (effectList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				effects.add(EffectFactory.create(effectList.item(i)));
			}
		}
		
		return new ProjectileCreator(shape, speed, lifetime, typeID, gravity, effects);
	}
}

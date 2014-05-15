package edu.chalmers.brawlbuddies.model.world;

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
import edu.chalmers.brawlbuddies.model.skills.EffectFactory;
import edu.chalmers.brawlbuddies.model.skills.IEffect;
import edu.chalmers.brawlbuddies.util.ShapeFactory;
import edu.chalmers.brawlbuddies.util.XMLReader;

public class MeleeFactory {
	public static MeleeCreator create(String projName){
		
		Document xmlDoc = XMLReader.getDocument(Constants.MELEES_DATA + projName.toLowerCase() + ".xml");
	
		Element rootNode = xmlDoc.getDocumentElement();
		
		// Getting the type ID
		int typeID = Integer.parseInt(rootNode.getAttribute("id"));
		
		// Making the hitbox
		NamedNodeMap shapeParams = rootNode.getElementsByTagName("hitbox").item(0).getAttributes();
		Shape shape = ShapeFactory.create(shapeParams.getNamedItem("shape").getNodeValue()
				, shapeParams.getNamedItem("parameters").getNodeValue(), 0, 0);
		
		// Getting the effects
		NodeList effectList = rootNode.getElementsByTagName("effects").item(0).getChildNodes();
		List<IEffect> effects = new ArrayList<IEffect>();
		for (int i=0; i<effectList.getLength(); i++) {
			if (effectList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				effects.add(EffectFactory.create(effectList.item(i)));
			}
		}
		return new MeleeCreator(effects, shape, typeID);	
	}
}

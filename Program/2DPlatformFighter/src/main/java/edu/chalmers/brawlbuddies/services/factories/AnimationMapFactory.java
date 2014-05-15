package edu.chalmers.brawlbuddies.services.factories;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.chalmers.brawlbuddies.Constants;

/**
 * A factory for getting a map of Animations
 * @author Patrik Haar
 * @version 1.0
 */
public class AnimationMapFactory {

	/**
	 * Gets the animations related to the given ID.
	 * @param id The type ID connected to the animations wanted.
	 * @return A HashMap with the Animations as value and their names as key.
	 */
	public static Map<String, Animation> create(int id) {
		
		String[] typeName = { null, "characters", "skills", "projectiles", "hud" };
		
		Document xmlDoc = XMLReader.getDocument(Constants.IMAGES + "image_resource_locator.xml");
		
		NodeList resources = xmlDoc.getElementsByTagName("resource");
		
		Element el = null;
		for (int i=0; i<resources.getLength(); i++) {
			if (resources.item(i).getNodeType() == Node.ELEMENT_NODE) {
				if (resources.item(i).getAttributes().getNamedItem("id").getNodeValue().equalsIgnoreCase(String.valueOf(id))) {
					el = (Element)resources.item(i);
				}
			}
		}
		SpriteSheet sheet = null;
		if (el!=null) {
			try {
				String[] size = el.getAttribute("size").split(",");
				sheet = new SpriteSheet((Constants.IMAGES + typeName[Character.getNumericValue(String.valueOf(id).charAt(0))] + "/" + el.getAttribute("name") + ".png"), Integer.parseInt(size[0]), Integer.parseInt(size[1]));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("ID: " + id + " does not have a valid resource");
		}
		
		HashMap<String, Animation> map = new HashMap<String,Animation>();
		
		NamedNodeMap attr = el.getAttributes();
		
		for (int i=0; i<attr.getLength(); i++) {
			String nodeName = attr.item(i).getNodeName();
			if (nodeName!="id" && nodeName!="name" && nodeName!="size") {
				String[] animAttributes = attr.item(i).getNodeValue().split(",");
				Animation ani = new Animation(sheet, Integer.parseInt(animAttributes[0]), Integer.parseInt(animAttributes[1])
						, Integer.parseInt(animAttributes[2]), Integer.parseInt(animAttributes[3]), true, Integer.parseInt(animAttributes[4]), true);
				ani.setLooping(Boolean.parseBoolean(animAttributes[5]));
				ani.setPingPong(Boolean.parseBoolean(animAttributes[6]));
				ani.stop();
				map.put(nodeName, ani);
			}
		}
		
		return map;
	}
}

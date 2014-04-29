package edu.chalmers.brawlbuddies.services.factories;

import org.w3c.dom.Document;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.model.world.Character;

public class ProjectileFactory {

	public static Character create(String projName) {

		Document xmlDoc = XMLReader.getDocument(Constants.CHARACTER_DATA
				+ projName.toLowerCase() + ".xml");
		
		return null;
	}
}

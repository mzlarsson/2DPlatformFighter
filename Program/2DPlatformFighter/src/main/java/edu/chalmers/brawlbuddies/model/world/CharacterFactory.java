package edu.chalmers.brawlbuddies.model.world;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.newdawn.slick.geom.Rectangle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/**
 * A factory for creating characters.
 * @author Patrik Haar
 * @version 0.1
 */
public class CharacterFactory {
	
	/**
	 * Creates a character by reading from the xml file with the provided name.
	 * @param charName The name of the character.
	 * @return The created character with all stats set.
	 */
	public static Character createCharacter(String charName) {
		
		Document xmlDoc = getDocument("res/characters/" + charName.toLowerCase() + ".xml");

		Element rootNode = xmlDoc.getDocumentElement();
		Character character = new Character(new Rectangle(0, 0, 50, 80));
		character.setName(rootNode.getElementsByTagName("name").item(0)
				.getChildNodes().item(0).getNodeValue());
		character.setBio(xmlDoc.getElementsByTagName("bio").item(0).getChildNodes()
				.item(0).getNodeValue());
		character.setBaseSpeed(Float.parseFloat(xmlDoc
				.getElementsByTagName("movespeed").item(0).getChildNodes()
				.item(0).getNodeValue()));
		character.setBaseJump(Float.parseFloat(xmlDoc
				.getElementsByTagName("jumpingpower").item(0).getChildNodes()
				.item(0).getNodeValue()));
		character.setMaxJumps(Integer.parseInt(xmlDoc
				.getElementsByTagName("maxjumps").item(0).getChildNodes()
				.item(0).getNodeValue()));
		return character;
	}

	/**
	 * Finds and returns the xml file on the path provided.
	 * @param docString The path to the file.
	 * @return A Document made from the xml file.
	 */
	private static Document getDocument(String docString) {
		try {
			// API used to convert XML into a DOM object tree
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// Ignore all of the comments
			factory.setIgnoringComments(true);
			// Ignore white space in elements
			factory.setIgnoringElementContentWhitespace(true);
			// Validate the XML as it is parsed
			factory.setValidating(true);
			// Provides access to the documents data
			DocumentBuilder builder = factory.newDocumentBuilder();
			new InputSource(docString);
			// Takes the document
			return builder.parse(new InputSource(docString));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
}

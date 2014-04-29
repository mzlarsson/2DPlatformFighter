package edu.chalmers.brawlbuddies.model.world;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.Skills.DamageEffect;
import edu.chalmers.brawlbuddies.model.Skills.Effect;
import edu.chalmers.brawlbuddies.model.Skills.HealEffect;
import edu.chalmers.brawlbuddies.model.Skills.ProjectileSkill;
import edu.chalmers.brawlbuddies.model.Skills.SelfCastSkill;
import edu.chalmers.brawlbuddies.model.Skills.Skill;

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
		//TODO check if this works
		character.setHealth(Float.parseFloat(xmlDoc.getElementsByTagName("health").item(0).getChildNodes()
				.item(0).getNodeValue()));
		float moveSpeed = Float.parseFloat(xmlDoc
				.getElementsByTagName("movespeed").item(0).getChildNodes()
				.item(0).getNodeValue());
		float jumpSpeed = Float.parseFloat(xmlDoc
				.getElementsByTagName("jumpingpower").item(0).getChildNodes()
				.item(0).getNodeValue());
		int maxJumps = Integer.parseInt(xmlDoc
				.getElementsByTagName("maxjumps").item(0).getChildNodes()
				.item(0).getNodeValue());
		character.setMovement(new JumpMovement(new Velocity(moveSpeed, 0), jumpSpeed, maxJumps));
		
		//TODO temporary Solution to set a Character Skills
		List<Effect>effects1  = new ArrayList<Effect>();
		effects1.add(new DamageEffect(50));
		Skill firstSkill  = new ProjectileSkill(new Circle(0,0,10), 1000, 5000 , 0, effects1);
		List<Effect> effects2 = new ArrayList<Effect>();
		effects2.add( new HealEffect(50));
		Skill secondSkill = new SelfCastSkill(0 , effects2);
		Skill[] skills = { firstSkill , secondSkill};
		character.setSkills(skills);
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

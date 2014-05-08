package edu.chalmers.brawlbuddies.services.factories;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.GameMap;

/**
 * A factory for creating GameMaps.
 * @author Patrik Haar
 * @version 1.0
 */
public class GameMapFactory {

	/**
	 * Creates the map with the given name.
	 * @param mapName The name of the map.
	 * @return A GameMap containing the TiledMap and spawn positions.
	 */
	public static GameMap create(String mapName) {
		
		TiledMap map = null;
		try {
			map = new TiledMap(Constants.TILEMAPS + mapName.toLowerCase() + ".tmx");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Document xmlDoc = XMLReader.getDocument(Constants.TILEMAPS + mapName.toLowerCase() + ".tmx");

		Element rootNode = xmlDoc.getDocumentElement();
		
		String[] spawnString = rootNode.getElementsByTagName("spawns").item(0).getFirstChild().getNodeValue().split(",");
		Position[] spawns = new Position[spawnString.length/2];
		for (int i=0; i<spawns.length; i++) {
			spawns[i] = new Position(Float.parseFloat(spawnString[i*2]),Float.parseFloat(spawnString[i*2+1]));
		}
		
		return new GameMap(map, spawns);
	}
}

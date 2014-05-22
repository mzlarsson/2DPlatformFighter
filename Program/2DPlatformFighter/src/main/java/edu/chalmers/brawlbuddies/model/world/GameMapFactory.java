package edu.chalmers.brawlbuddies.model.world;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.util.ResourceLoader;
import edu.chalmers.brawlbuddies.util.XMLReader;

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
			map = new TiledMap(Constants.TILEMAPS + mapName + ".tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		String[] spawnString = map.getMapProperty("spawns", "100,50").split(",");
		Position[] spawns = new Position[spawnString.length/2];
		for (int i=0; i<spawns.length; i++) {
			spawns[i] = new Position(Float.parseFloat(spawnString[i*2]),Float.parseFloat(spawnString[i*2+1]));
		}

		EventBus.getInstance().fireEvent(new EventBusEvent("createMap", map, null));
		return new GameMap(map, spawns);
	}
	
	public static Map<String, String> getAvailableMaps(){
		List<String> fileNames = ResourceLoader.listFileNames(Constants.TILEMAPS);
		Map<String, String> mapNames = new LinkedHashMap<String, String>();
		for(int i = 0; i<fileNames.size(); i++){
			Document xmlDoc = XMLReader.getDocument(fileNames.get(i));
			Element rootNode = xmlDoc.getDocumentElement();
			String mapName = "Name not found";
			NodeList list = rootNode.getElementsByTagName("property");
			for (int j=0; j<list.getLength(); j++) {
				NamedNodeMap att = list.item(j).getAttributes();
				if (att.getNamedItem("name")!=null && att.getNamedItem("name").getNodeValue().equals("name")) {
					mapName = att.getNamedItem("value").getNodeValue();
				}
			}

			String path = fileNames.get(i);
			int startIndex = Math.max(path.lastIndexOf("/"), path.lastIndexOf("\\"))+1;
			String name = path.substring(startIndex).substring(0, path.lastIndexOf(".")-startIndex);
			mapNames.put(name, mapName);
		}
		return mapNames;
	}
}

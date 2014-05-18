package edu.chalmers.brawlbuddies.model.world;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.Position;
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

		EventBus.getInstance().fireEvent(new EventBusEvent("createMap", map, null));
		return new GameMap(map, spawns);
	}
	
	public static Map<String, String> getAvailableMaps(){
		File folder = new File(Constants.TILEMAPS);
		File[] files = folder.listFiles(new FileFilter(){
			public boolean accept(File file) {
				return file.isFile();
			}
		});
		Map<String, String> mapNames = new LinkedHashMap<String, String>();
		for(int i = 0; i<files.length; i++){
			String path = files[i].getAbsolutePath();
			int startIndex = Math.max(path.lastIndexOf("/"), path.lastIndexOf("\\"))+1;
			String name = path.substring(startIndex).substring(0, path.lastIndexOf(".")-startIndex);
			mapNames.put(name, name);
		}
		return mapNames;
	}
}

package edu.chalmers.brawlbuddies.model.world;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import edu.chalmers.brawlbuddies.Constants;

public class GameMap {

	private TiledMap map; 
	
	
	public GameMap() {
		try {
			map = new TiledMap(Constants.TILEMAPS + "basic16Map.tmx"); //TODO Add flexability
		} catch (SlickException e) {
			e.printStackTrace();
		}

		

	}
	
	public TiledMap getMap(){
		return map;
	}

}

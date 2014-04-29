package edu.chalmers.brawlbuddies.model.world;

import java.util.List;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class GameMap {

	private TiledMap map; 
	
	
	public GameMap() {
		try {
			map = new TiledMap("res/basic16Map.tmx");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}
	
	public TiledMap getMap(){
		return map;
	}
	
	
	public List<GameObject> getAllObjects(){
		//FIXME implement this.
		return null;
	}

}

package edu.chalmers.brawlbuddies.services.factories;

import edu.chalmers.brawlbuddies.model.BrawlBuddies;
import edu.chalmers.brawlbuddies.model.IBrawlBuddies;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.GameMap;
import edu.chalmers.brawlbuddies.model.world.World;

public class GameFactory {

	public static IBrawlBuddies create(String mapName, String[] characterNames){
		GameMap map = GameMapFactory.create(mapName);
		World world = new World(map);
		
		Position tmpPos = null;
		for(int i = 0; i<characterNames.length; i++){
			if(map.getSpawnCount()>i){
				tmpPos = map.getSpawn(i);
			}else{
				tmpPos = map.getRandomSpawn();
			}
			
			world.add(CharacterFactory.create(characterNames[i], tmpPos));
		}
		
		return new BrawlBuddies(world);
	}

}

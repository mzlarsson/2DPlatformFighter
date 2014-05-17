package edu.chalmers.brawlbuddies.model;

import edu.chalmers.brawlbuddies.model.world.CharacterFactory;
import edu.chalmers.brawlbuddies.model.world.GameMap;
import edu.chalmers.brawlbuddies.model.world.GameMapFactory;
import edu.chalmers.brawlbuddies.model.world.World;

public class GameFactory {

	public static IBrawlBuddies create(String mapName, String[] characterNames, GameListener gl, int timeLimit, int lifeLimit){
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
		BrawlBuddies bb = new BrawlBuddies(world);
		if (timeLimit>0) {
			TimeLimitGoalWrapper time = new TimeLimitGoalWrapper(timeLimit);
			time.addGameListener(gl);
			bb.addGoal(time);
		}
		if (lifeLimit>0) {
			LifeLimitGoalWrapper life = new LifeLimitGoalWrapper(lifeLimit);
			life.addGameListener(gl);
			bb.addGoal(life);
		}
		return bb;
	}

}

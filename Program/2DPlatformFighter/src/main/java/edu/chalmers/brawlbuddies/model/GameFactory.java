package edu.chalmers.brawlbuddies.model;

import edu.chalmers.brawlbuddies.model.world.CharacterFactory;
import edu.chalmers.brawlbuddies.model.world.GameMap;
import edu.chalmers.brawlbuddies.model.world.GameMapFactory;
import edu.chalmers.brawlbuddies.model.world.World;
/**
 * A factory to create a Game
 * @author Matz Larsson
 *
 */
public class GameFactory {
	/**
	 * Creates a new instance of BrawlBuddies with a mapName, characterNames, gamelistener, 
	 * timelimit and lifelimit.
	 * @param mapName - the name of the mape the game will be played on
	 * @param characterNames - the name of the characters that will be used
	 * @param gl - the game listener for the game
	 * @param timeLimit - the time limit of the game
	 * @param lifeLimit - the life limit of the game
	 * @return IBrawlBuddies- a new BrawBuddies game
	 */
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
		GoalHandlerWrapper gh = new GoalHandlerWrapper();
		gh.addGameListener(gl);
		bb.setGoalHandler(gh);
		if (timeLimit>0) {
			bb.addGoal(new TimeLimitGoalWrapper(timeLimit));
		}
		if (lifeLimit>0) {
			bb.addGoal(new LifeLimitGoalWrapper(lifeLimit));
		}
		return bb;
	}

}

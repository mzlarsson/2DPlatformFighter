import edu.chalmers.brawlbuddies.controller.Controller;
import edu.chalmers.brawlbuddies.controller.Player;
import edu.chalmers.brawlbuddies.model.world.CharacterFactory;

/**
 * This is the main class of the game Brawl Buddies, a 2D platform fighter game.
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class Main {

	/**
	 * Creates a controller and starts a game.
	 * @param args	Parameters in.
	 * 				NOTE: This variable is not used.
	 */
	public static void main(String[] args){
		Controller controller = new Controller();
		Player[] players = { new Player("BobTheSparklyMidget", CharacterFactory.createCharacter("bob")) };
		controller.startGame(players);
	}
	
}

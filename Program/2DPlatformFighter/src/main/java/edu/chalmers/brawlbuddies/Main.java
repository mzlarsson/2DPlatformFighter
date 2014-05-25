package edu.chalmers.brawlbuddies;

import java.util.logging.Level;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import edu.chalmers.brawlbuddies.controller.Controller;
import edu.chalmers.brawlbuddies.util.GameLogger;
import edu.chalmers.brawlbuddies.util.ResourceLoader;

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
		GameLogger.setup();

		Controller controller = new Controller();
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(controller);
			appgc.setIcon((ResourceLoader.insideJar()?"":Constants.ECLIPSE_RESOURCE_PREFIX)+Constants.MENU_IMAGES+"BrawlBuddies_icon.png");

			appgc.setDisplayMode(1366, 768, false);
			appgc.start();
		} catch (SlickException ex) {
			GameLogger.getLogger().log(Level.SEVERE, null, ex);
		}
	}
	
}

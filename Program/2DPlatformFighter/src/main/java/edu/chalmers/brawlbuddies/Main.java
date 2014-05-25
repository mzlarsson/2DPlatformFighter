package edu.chalmers.brawlbuddies;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import edu.chalmers.brawlbuddies.controller.Controller;

/**
 * This is the main class of the game Brawl Buddies, a 2D platform fighter game.
 * @author Matz Larsson
 * @version 1.0
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
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(controller);

			appgc.setDisplayMode(1600, 800, false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}

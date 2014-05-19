package edu.chalmers.brawlbuddies;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import edu.chalmers.brawlbuddies.controller.Controller;

/**
 * This is the main class of the game Brawl Buddies, a 2D platform fighter game.
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class Main {

	/**
	 * Sets the java library path to the specified path
	 *
	 * @param path the new library path
	 * @throws Exception
	 */
	public static void setLibraryPath(String path) throws Exception {
	    System.setProperty("java.library.path", path);
	 
	    //set sys_paths to null
	    final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
	    sysPathsField.setAccessible(true);
	    sysPathsField.set(null, null);
	}
	/**
	 * Creates a controller and starts a game.
	 * @param args	Parameters in.
	 * 				NOTE: This variable is not used.
	 */
	public static void main(String[] args){
		try {
			setLibraryPath("target/natives");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(System.getProperty("java.library.path"));
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

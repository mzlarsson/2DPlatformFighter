package edu.chalmers.brawlbuddies;

import edu.chalmers.brawlbuddies.model.Velocity;

/**
 * Contains simple contants that are used in Brawl Buddies.
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class Constants {

	public static final int MODIFIER = 1000;

	public static final Velocity DEFAULT_GRAVITY = new Velocity(0,1000);
	
	public static final int GAMESTATE_MENU = 0;
	public static final int GAMESTATE_PLAY = 1;
	
	public static final String IMAGES = "res/imgs/";
	public static final String CHARACTER_DATA = "res/data/characters/";
	public static final String CHARACTER_IMAGES = "res/imgs/characters/";
	public static final String PROJECTILES_DATA = "res/data/projectiles/";
	public static final String PROJECTILES_IMAGES = "res/imgs/projectiles/";
	public static final String TILEMAPS = "res/maps/";
	public static final String SKILLS = "res/data/skills/";
}

package edu.chalmers.brawlbuddies.controller;

import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.chalmers.brawlbuddies.controller.menu.OptionMenuState;
import edu.chalmers.brawlbuddies.util.GameLogger;
import edu.chalmers.brawlbuddies.view.sound.SoundPlayer;

/**
 * Basic controller used for handling the general aspects of the
 * game Brawl Buddies.
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class Controller extends StateBasedGame {
	
	private AppGameContainer gameContainer;

	/**
	 * Creates a new Controller and a new Slick window
	 */
	public Controller() {
		super("BrawlBuddies v1.0");
		GameLogger.getLogger().info("Starting BrawlBuddies...");
		SoundPlayer player = SoundPlayer.getInstance();
		player.setMusic(edu.chalmers.brawlbuddies.Constants.MUSIC + "menu.ogg");
	}

	/**
	 * Inits all the states used in the game
	 * @see StateFactory
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException {		
		gameContainer = (AppGameContainer)container;
		
		//Show splash screen (game intro)
		this.addState(new Intro());
		//this.enterState(Constants.GAMESTATE_INTRO);
		
		//Inits all the states
		List<BasicGameState> states = StateFactory.getAllStates();
		for(BasicGameState state : states){
			this.addState(state);
		}
		
		//Load settings
		((OptionMenuState)(this.getState(Constants.GAMESTATE_MENU_OPTIONS))).loadSettings(this);
		//FIXME remove when having intro
		this.enterState(Constants.GAMESTATE_MAIN_MENU);
	}
	
	/**
	 * Set up if music should be on or off
	 * @param useMusic If the music should be on
	 */
	public void useMusic(boolean useMusic){
		if(useMusic){
			SoundPlayer.getInstance().startMusic();
		}else{
			SoundPlayer.getInstance().stopMusic();
		}
	}
	
	/**
	 * Sets the volume of the music in the game
	 * @param volume The volume to use
	 */
	public void setMusicVolume(float volume){
		SoundPlayer.getInstance().setMusicVolume(volume);
	}
	
	/**
	 * Set up if sound should be on or off
	 * @param useSound If the sound should be on
	 */
	public void useSounds(boolean useSound){
		if(useSound){
			SoundPlayer.getInstance().startSounds();
		}else{
			SoundPlayer.getInstance().stopSounds();
		}
	}
	
	/**
	 * Sets the volume of the sounds in the game
	 * @param volume The volume to use
	 */
	public void setSoundVolume(float volume){
		SoundPlayer.getInstance().setSoundVolume(volume);
	}
	
	/**
	 * Sets the resolution of the screen
	 * @param x The x-value
	 * @param y The y-value
	 * @param fullscreen If fullscreen should be used
	 */
	public void setResolution(int x, int y, boolean fullscreen){
		try {
			gameContainer.setDisplayMode(x, y, fullscreen);
		} catch (SlickException e) {
			GameLogger.getLogger().warning("Could not change resolution");
		}
	}
	
	/**
	 * Redirects the game to the main menu
	 */
	public void gotoMainMenu(){
		this.enterState(Constants.GAMESTATE_MAIN_MENU);
	}
	
	/**
	 * Starts up a new game with some game parameters
	 * @param players The players to take part of this game
	 * @param characterNames The names of the characters used, synched with the player array
	 */
	public void startGame(Player[] players, String[] characterNames, String mapName, int lives, int time){
		((PlayState)(this.getState(Constants.GAMESTATE_PLAY))).startGame(players, characterNames, mapName, lives, time);
		this.enterState(Constants.GAMESTATE_PLAY);
	}
	
	/**
	 * Called when the program wishes to shutdown
	 * @return <code>true</code> if shutting down, <code>false</code> otherwise
	 */
	@Override
	public boolean closeRequested(){
		GameLogger.getLogger().info("Closing...");
		SoundPlayer.getInstance().stop();
		System.exit(0);
		return true;
	}

}

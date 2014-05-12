package edu.chalmers.brawlbuddies.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.chalmers.brawlbuddies.model.IBrawlBuddies;
import edu.chalmers.brawlbuddies.services.factories.GameFactory;
import edu.chalmers.brawlbuddies.view.GameView;
import edu.chalmers.brawlbuddies.view.IView;
import edu.chalmers.brawlbuddies.view.sound.SoundPlayer;

/**
 * State for handling the main gameplay of the game BrawlBuddies
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class PlayState extends BasicGameState{
	
	private IView view;
	private IBrawlBuddies game;
	private Player[] players;

	/**
	 * Creates a new PlayState
	 */
	public PlayState(){
	}
	
	/**
	 * Inits all variables that are needed etc.
	 * @param container The slick game container
	 * @param game The parent controller
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		//Do nothing.
	}
	
	/**
	 * Starts a new game with given parameters
	 * @param players The players to take part in the game
	 * @param names The names of the characters to used, synched with players array
	 */
	public void startGame(Player[] players, String[] names){
		view = new GameView();
		this.players = players;
		game = GameFactory.create("basic16Map", names);
		int[] id = game.getCharacterIDs();
		for(int i = 0; i<players.length; i++){
			players[i].setCharacterID(id[i]);
		}
	}
	
	/**
	 * Handles all control signals and updates all the data
	 * @param container The slick game container
	 * @param game The parent controller
	 * @param delta The time in ms since the last update
	 * @throws SlickException If the slick engine finds invalid data
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//Check if any players set
		if(this.players != null){
			//Send all control signals to model
			for(int i = 0; i<this.players.length; i++){
				InputHandler handler = players[i].getInputHandler();
				int characterID = players[i].getCharacterID();
				//FIXME do other implementation.
				if(handler instanceof KeyInputHandler && ((KeyInputHandler)handler).getInput() == null){
					((KeyInputHandler)handler).setInput(gc.getInput());
				}
				
				game.move(characterID, handler.getDirection());
				
				if(handler.isActivated(GameKey.JUMP)){
					game.jump(characterID);
				}
				if(handler.isActive(GameKey.SKILL1)){
					game.activateSkill(characterID, 0);
				}
				if(handler.isActive(GameKey.SKILL2)){
					game.activateSkill(characterID, 1);
				}
				if(handler.isActive(GameKey.SKILL3)){
					game.activateSkill(characterID, 2);
				}
				if(handler.isActive(GameKey.SKILL4)){
					game.activateSkill(characterID, 3);
				}
				
				//Update mouse position
				game.setAim(characterID, handler.getMousePosition(), handler.isMousePositionRelative());
			}
	
			//Update model
			game.update(delta);
		}
	}

	/**
	 * Renders the game on the screen
	 * @param container The slick game container
	 * @param game The parent controller
	 * @param g The graphics object to draw with
	 * @throws SlickException If the slick engine finds invalid data
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		view.render(gc, g);
	}
	
	/**
	 * Called on when the game goes into gameplay state.
	 * Starts neccessary processes (sound etc.)
	 * @param container The slick game container
	 * @param game The parent controller
	 */
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		System.out.println("Entering Play state");
		SoundPlayer.getInstance().start();
	}

	/**
	 * Called on when the game goes out from gameplay state.
	 * Stops neccessary processes (sound etc.)
	 * @param container The slick game container
	 * @param game The parent controller
	 */
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException{
		System.out.println("Leaving Play state");
		SoundPlayer.getInstance().stop();
	}
	
	/**
	 * Determines the ID of this game state as declared in the
	 * edu.chalmers.brawlbuddies.controller.Constants class
	 * @return The ID of this game state
	 */
	@Override
	public int getID(){
		return Constants.GAMESTATE_PLAY;
	}

}

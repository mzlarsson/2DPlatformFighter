package edu.chalmers.brawlbuddies.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.chalmers.brawlbuddies.controller.input.GameKey;
import edu.chalmers.brawlbuddies.controller.input.InputHandler;
import edu.chalmers.brawlbuddies.controller.input.KeyInputHandler;
import edu.chalmers.brawlbuddies.controller.menu.EndScreenState;
import edu.chalmers.brawlbuddies.model.GameFactory;
import edu.chalmers.brawlbuddies.model.GameListener;
import edu.chalmers.brawlbuddies.model.IBrawlBuddies;
import edu.chalmers.brawlbuddies.view.GameView;
import edu.chalmers.brawlbuddies.view.IView;

/**
 * State for handling the main gameplay of the game BrawlBuddies
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class PlayState extends BasicGameState implements GameListener{
	
	private IView view;
	private IBrawlBuddies game;
	private Player[] players;
	private StateBasedGame state;

	/**
	 * Creates a new PlayState
	 */
	public PlayState(){
	}
	
	/**
	 * Inits all variables that are needed etc.
	 * @param gc The slick game container
	 * @param sbg The parent controller
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		//Do nothing.
	}
	
	/**
	 * Starts a new game with given parameters
	 * @param players The players to take part in the game
	 * @param names The names of the characters to used, synched with players array
	 */
	public void startGame(Player[] players, String[] names, String mapName, int lives, int time){
		view = new GameView();
		this.players = players;
		game = GameFactory.create(mapName, names, this, time, lives);
		int[] id = game.getCharacterIDs();
		for(int i = 0; i<players.length; i++){
			players[i].setCharacterID(id[i]);
		}
	}
	
	public boolean gameStarted(){
		return this.players != null && this.players.length>0;
	}
	
	/**
	 * Handles all control signals and updates all the data
	 * @param gc The slick game container
	 * @param sbg The parent controller
	 * @param delta The time in ms since the last update
	 * @throws SlickException If the slick engine finds invalid data
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//Check if game started
		if(this.gameStarted()){
			if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
				sbg.enterState(Constants.GAMESTATE_MAIN_MENU);
			}
			//Send all control signals to model
			for(int i = 0; i<this.players.length; i++){
				InputHandler handler = players[i].getInputHandler();
				int characterID = players[i].getCharacterID();
				//FIXME do other implementation.
				if(handler instanceof KeyInputHandler && ((KeyInputHandler)handler).getInput() == null){
					((KeyInputHandler)handler).setInput(gc.getInput());
					((KeyInputHandler)handler).setScroller(view.getScroller());
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
			view.update(delta);
		}
	}

	/**
	 * Renders the game on the screen
	 * @param gc The slick game container
	 * @param sbg The parent controller
	 * @param g The graphics object to draw with
	 * @throws SlickException If the slick engine finds invalid data
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		if(this.gameStarted()){
			view.render(gc, g);
		}
	}
	
	/**
	 * Called on when the game goes into gameplay state.
	 * Starts neccessary processes (sound etc.)
	 * @param container The slick game container
	 * @param game The parent controller
	 */
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		state = game;
	}

	/**
	 * Called on when the game goes out from gameplay state.
	 * Stops neccessary processes (sound etc.)
	 * @param container The slick game container
	 * @param game The parent controller
	 */
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException{
		view.close();
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

	public void gameEventPerformed(String evtName, Object value) {
		if (evtName.equals("gameOver")) {
			String winner = "DRAW! Or bugged.";
			for (Player p : players) {
				if (p.getCharacterID()==(Integer)value) {
					winner = p.getName();
				}
			}
			((EndScreenState)state.getState(Constants.GAMESTATE_END_SCREEN)).setWinner(winner);;
			state.enterState(Constants.GAMESTATE_END_SCREEN);
		}
	}

}

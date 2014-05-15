package edu.chalmers.brawlbuddies.controller.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.chalmers.brawlbuddies.controller.Constants;
import edu.chalmers.brawlbuddies.controller.Controller;
import edu.chalmers.brawlbuddies.controller.PlayState;
import edu.chalmers.brawlbuddies.controller.Player;
import edu.chalmers.brawlbuddies.controller.input.InputHandlerChooser;
import edu.chalmers.brawlbuddies.view.menu.MainMenuView;
import edu.chalmers.brawlbuddies.view.menu.MenuView;

/**
 * State for displaying and handling the main menu system
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class MainMenuState extends BasicGameState implements MenuListener{
	
	private MenuView view;
	private MenuHandler handler;
	private StateBasedGame game;

	/**
	 * Creates a new Menu state
	 */
	public MainMenuState() {
		view = new MainMenuView();
		handler = new MenuHandler(view);
		handler.addMenuListener(this);
	}

	/**
	 * Inits all variables that are needed etc.
	 * @param container The slick game container
	 * @param game The parent controller
	 */
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		((OptionMenuState)(game.getState(Constants.GAMESTATE_MENU_OPTIONS))).loadSettings((Controller)game);
	}

	/**
	 * Renders the menu on the screen
	 * @param container The slick game container
	 * @param game The parent controller
	 * @param g The graphics object to draw with
	 * @throws SlickException If the slick engine finds invalid data
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		view.render(container, g);
	}

	/**
	 * Updates the data of the menu
	 * @param container The slick game container
	 * @param game The parent controller
	 * @param delta The time in ms since the last update
	 * @throws SlickException If the slick engine finds invalid data
	 */
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.game = game;
		handler.update(container, delta);
	}

	/**
	 * Determines the ID of this game state as declared in the
	 * edu.chalmers.brawlbuddies.controller.Constants class
	 * @return The ID of this game state
	 */
	@Override
	public int getID() {
		return Constants.GAMESTATE_MAIN_MENU;
	}
	
	public void startGame(){
		Player[] players = { new Player("Player1", InputHandlerChooser.getInstance().getInputHandler(1, false)),
	 			new Player("Player2", InputHandlerChooser.getInstance().getInputHandler(2, true))};
		String[] characterNames = {"bob", "bob"};
		((PlayState)game.getState(Constants.GAMESTATE_PLAY)).startGame(players, characterNames);

		game.enterState(Constants.GAMESTATE_PLAY);
	}
	
	public void gotoOptions(){
		game.enterState(Constants.GAMESTATE_MENU_OPTIONS);
	}
	
	public void gotoCredits(){
		game.enterState(Constants.GAMESTATE_MENU_CREDITS);
	}
	
	public void shutdown(){
		System.exit(0);
	}

	public void menuActivated(MenuEvent event) {
		if(event.getValue().equals("Start game")){
			startGame();
		}else if(event.getValue().equals("Options")){
			gotoOptions();
		}else if(event.getValue().equals("Credits")){
			gotoCredits();
		}else if(event.getValue().equals("Exit")){
			shutdown();
		}
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		view.update();
	}

}

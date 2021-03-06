package edu.chalmers.brawlbuddies.controller.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.chalmers.brawlbuddies.controller.Constants;
import edu.chalmers.brawlbuddies.view.menu.EndScreenView;

/**
 * Controls the end screen menu
 * @author Matz Larsson
 * @version 1.0
 */

public class EndScreenState extends BasicGameState implements MenuListener{

	private EndScreenView view;
	private MenuHandler handler;
	private StateBasedGame game;
	
	public EndScreenState() {
		view = new EndScreenView();
		handler = new MenuHandler(view);
		handler.addMenuListener(this);
	}
	
	/**
	 * Inits all variables that are needed etc.
	 * @param container The slick game container
	 * @param game The parent controller
	 */
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
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

	public void menuActivated(MenuEvent event) {
		if(event.getName()=="gotoMain"){
			gotoMainMenu();
		}
	}

	public void gotoMainMenu(){
		game.enterState(Constants.GAMESTATE_MAIN_MENU);
	}
	
	public void setWinner(String winnerName) {
		view.setWinner(winnerName);
	}
	
	@Override
	public int getID() {
		return Constants.GAMESTATE_END_SCREEN;
	}

}

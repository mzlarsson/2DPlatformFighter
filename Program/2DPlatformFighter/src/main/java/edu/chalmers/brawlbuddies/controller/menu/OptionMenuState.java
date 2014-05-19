package edu.chalmers.brawlbuddies.controller.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.chalmers.brawlbuddies.controller.Controller;
import edu.chalmers.brawlbuddies.controller.Constants;
import edu.chalmers.brawlbuddies.view.menu.MenuItem;
import edu.chalmers.brawlbuddies.view.menu.MenuView;
import edu.chalmers.brawlbuddies.view.menu.MultiChoiceMenuItem;
import edu.chalmers.brawlbuddies.view.menu.OptionsMenuView;

public class OptionMenuState extends BasicGameState implements MenuListener{

	private MenuView view;
	private MenuHandler handler;
	private StateBasedGame game;
	
	public OptionMenuState() {
		view = new OptionsMenuView(Settings.getInstance().getSettings());
		handler = new MenuHandler(view);
		handler.addMenuListener(this);
	}

	/**
	 * Inits all variables that are needed etc.
	 * @param container The slick game container
	 * @param game The parent controller
	 */
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		for(MenuItem item : view.getMenuItems()){
			if(item instanceof MultiChoiceMenuItem){
				MultiChoiceMenuItem mcmItem = ((MultiChoiceMenuItem)item);
				String val = Settings.getInstance().getSetting(mcmItem.getName());
				mcmItem.setItem(val);
			}
		}
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
		view.update(delta);
		
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			gotoMainMenu();
		}
	}
	
	public void gotoMainMenu(){
		setSettings();
		Settings.getInstance().save();
		loadSettings((Controller)game);
		game.enterState(Constants.GAMESTATE_MAIN_MENU);
	}
	
	public void setSettings(){
		Settings settings = Settings.getInstance();
		for(MenuItem item : view.getMenuItems()){
			if(item instanceof MultiChoiceMenuItem){
				MultiChoiceMenuItem mcmItem = ((MultiChoiceMenuItem)item);
				settings.setSetting(mcmItem.getItemName(), mcmItem.getValue());
			}
		}
	}
	
	public void loadSettings(Controller controller){
		Settings settings = Settings.getInstance();
		if(settings.getSetting("music") != null){
			controller.useMusic(Boolean.parseBoolean(settings.getSetting("music")));
			controller.useSounds(Boolean.parseBoolean(settings.getSetting("sound")));
			String[] res = settings.getSetting("resolution").split("x");
			boolean fullscreen = Boolean.parseBoolean(settings.getSetting("fullscreen"));
			controller.setResolution(Integer.parseInt(res[0]), Integer.parseInt(res[1]), fullscreen);
		}
	}

	/**
	 * Determines the ID of this game state as declared in the
	 * edu.chalmers.brawlbuddies.controller.Constants class
	 * @return The ID of this game state
	 */
	@Override
	public int getID() {
		return Constants.GAMESTATE_MENU_OPTIONS;
	}

	public void menuActivated(MenuEvent event){
		if(event.getName()=="gotoMain"){
			gotoMainMenu();
		}
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		view.updateContents();
	}
}

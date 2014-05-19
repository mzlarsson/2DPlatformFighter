package edu.chalmers.brawlbuddies.controller.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.chalmers.brawlbuddies.controller.Constants;
import edu.chalmers.brawlbuddies.controller.Controller;
import edu.chalmers.brawlbuddies.controller.Player;
import edu.chalmers.brawlbuddies.controller.input.InputHandlerChooser;

public class GameLoadState extends BasicGameState{
	
	private String p1_character, p2_character, map;
	private int p1_control, p2_control, lives, time;
	
	private boolean painted = false;
	private Image loadingScreen;
	
	public GameLoadState() {
		try {
			this.loadingScreen = new Image(edu.chalmers.brawlbuddies.Constants.MENU_IMAGES + "loading_screen.png");
		} catch (SlickException e) {
			System.out.println("Could not load loading screen");
		}
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	}
	
	public void setup(String p1_character, int p1_control, String p2_character, int p2_control, String map, int lives, int time){
		this.p1_character = p1_character;
		this.p1_control = p1_control;
		this.p2_character = p2_character;
		this.p2_control = p2_control;
		this.map = map;
		this.lives = lives;
		this.time = time;
	}
	
	public void load(StateBasedGame game){
		Player[] players = { new Player("Player1", InputHandlerChooser.getInstance().getInputHandler(p1_control, false)),
							 new Player("Player2", InputHandlerChooser.getInstance().getInputHandler(p2_control, isSecondControl(p1_control, p2_control)))};
		String[] charNames = {p1_character, p2_character};
		((Controller)game).startGame(players, charNames, map, lives, time);
	}
	
	private boolean isSecondControl(int p1, int p2){
		InputHandlerChooser handler = InputHandlerChooser.getInstance();
		return handler.usesKeyboard(p1) && handler.usesKeyboard(p2);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if(!painted){
			g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
			if(loadingScreen != null){
				g.drawImage(loadingScreen, 0, 0, loadingScreen.getWidth(), loadingScreen.getHeight(), 0, 0, gc.getWidth(), gc.getHeight());
			}
			painted = true;
		}else{
			load(sbg);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	}
	
	@Override
	public int getID() {
		return Constants.GAMESTATE_LOADING_GAME;
	}

}

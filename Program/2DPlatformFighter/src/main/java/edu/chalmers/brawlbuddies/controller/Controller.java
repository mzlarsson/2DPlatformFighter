package edu.chalmers.brawlbuddies.controller;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import edu.chalmers.brawlbuddies.controller.InputHandler.GameKey;
import edu.chalmers.brawlbuddies.model.BrawlBuddies;
import edu.chalmers.brawlbuddies.model.world.CharacterFactory;
import edu.chalmers.brawlbuddies.model.world.GameMap;
import edu.chalmers.brawlbuddies.model.world.World;
import edu.chalmers.brawlbuddies.view.GameView;

public class Controller extends BasicGame{
	
	private GameView view;
	private BrawlBuddies game;

	public Controller() {
		super("Brawl Buddies v1.0");
	}
	
	public void init(GameContainer gc){
		//Empty for now.
	}
	
	public void startGame(Player[] players){
		game = new BrawlBuddies(players, new World(players, new GameMap()));
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		//Send all control signals to model
		Player[] players = game.getPlayers();
		Input input = gc.getInput();
		for(int i = 0; i<players.length; i++){
			InputHandler handler = players[i].getInputHandler();
			game.move(players[i], handler.getDirection(input));
			
			if(handler.isActive(input, GameKey.JUMP)){
				game.jump(players[i]);
			}
			if(handler.isActive(input, GameKey.FIRE)){
				//game.fireProjectile(players[i]);		//FIXME how to perform this?
			}
		}

		//Update model
		game.update(delta);
	}
	
	public void render(GameContainer gc, Graphics g){
		view.render(gc, g);
	}

}

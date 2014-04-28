package edu.chalmers.brawlbuddies.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.model.BrawlBuddies;
import edu.chalmers.brawlbuddies.model.world.GameMap;
import edu.chalmers.brawlbuddies.model.world.World;
import edu.chalmers.brawlbuddies.view.GameView;

public class PlayState extends BasicGameState{
	
	private GameView view;
	private BrawlBuddies game;

	public PlayState() {
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		//Empty for now.
	}
	
	public void startGame(Player[] players){
		game = new BrawlBuddies(players, new World(players, new GameMap()));
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//Send all control signals to model
		Player[] players = game.getPlayers();
		Input input = gc.getInput();
		for(int i = 0; i<players.length; i++){
			InputHandler handler = players[i].getInputHandler();
			game.move(players[i], handler.getDirection(input));
			
			if(handler.isActive(input, GameKey.JUMP)){
				game.jump(players[i]);
			}
			if(handler.isActive(input, GameKey.SKILL1)){
				players[i].getCharacter().activateSkill(0);
			}
			if(handler.isActive(input, GameKey.SKILL2)){
				players[i].getCharacter().activateSkill(1);
			}
			if(handler.isActive(input, GameKey.SKILL3)){
				players[i].getCharacter().activateSkill(2);
			}
			if(handler.isActive(input, GameKey.SKILL4)){
				players[i].getCharacter().activateSkill(3);
			}
		}

		//Update model
		game.update(delta);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		view.render(gc, g);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		System.out.println("Entering Play state");
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException{
		System.out.println("Leaving Play state");
	}
	
	@Override
	public int getID(){
		return Constants.GAMESTATE_PLAY;
	}

}

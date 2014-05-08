package edu.chalmers.brawlbuddies.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.chalmers.brawlbuddies.model.BrawlBuddies;
import edu.chalmers.brawlbuddies.model.IBrawlBuddies;
import edu.chalmers.brawlbuddies.view.GameView;

public class PlayState extends BasicGameState{
	
	private GameView view;
	private IBrawlBuddies game;
	private Player[] players;

	public PlayState(){
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		//Do nothing.
	}
	
	public void startGame(Player[] players, String[] names){
		this.players = players;
		game = GameFactory.create("defaultworld", names);
		int[] id = game.getCharacterIDs();
		for(int i = 0; i<players.length; i++){
			players[i].setCharacterID(id[i]);
		}
	}
	
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
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//view.render(gc, g);
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

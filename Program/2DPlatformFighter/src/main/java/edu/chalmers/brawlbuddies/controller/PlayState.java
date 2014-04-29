package edu.chalmers.brawlbuddies.controller;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.model.BrawlBuddies;
import edu.chalmers.brawlbuddies.model.world.GameMap;
import edu.chalmers.brawlbuddies.model.world.Projectile;
import edu.chalmers.brawlbuddies.model.world.World;
import edu.chalmers.brawlbuddies.view.GameView;

public class PlayState extends BasicGameState{
	
	private GameView view;
	private BrawlBuddies game;

	public PlayState() {
		//Nothing.
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		//Do nothing.
	}
	
	public void startGame(Player[] players){
		game = new BrawlBuddies(players, new World(players, new GameMap()));
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//Send all control signals to model
		Player[] players = game.getPlayers();
		for(int i = 0; i<players.length; i++){
			InputHandler handler = players[i].getInputHandler();
			if(handler == null){
				players[i].setInputHandler(new KeyInputHandler(gc.getInput()));
				handler = players[i].getInputHandler();
			}
			game.move(players[i], handler.getDirection());
			
			if(handler.isActivated(GameKey.JUMP)){
				game.jump(players[i]);
			}
			if(handler.isActivated(GameKey.SKILL1)){
				players[i].getCharacter().activateSkill(0);
			}
			if(handler.isActivated(GameKey.SKILL2)){
				players[i].getCharacter().activateSkill(1);
			}
			if(handler.isActivated(GameKey.SKILL3)){
				players[i].getCharacter().activateSkill(2);
			}
			if(handler.isActivated(GameKey.SKILL4)){
				players[i].getCharacter().activateSkill(3);
			}
		}

		//Update model
		game.update(delta);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//view.render(gc, g);
		game.getWorld().getMap().render(0,0);

		for(Player p:game.getPlayers()){
			p.getCharacter().draw();
			// Following Code is used to check if bob takes damage or if hes dead
			if( p.getCharacter().isDead()){
				g.setColor(Color.gray);
				g.drawString("Bob is dead. RIP BOB", 25, 25);
			}
			else if( p.getCharacter().getMaxHealth() != p.getCharacter().getHealth()){
				g.setColor(Color.darkGray);
				g.drawString("Bob is hurt", 25 , 25);
			}
			else{
				g.setColor(Color.black);
				g.drawString("Bob is ok", 25, 25);
			}

		}
		List <Projectile> projectiles = game.getProjectiles();
		for(int i=0; i< projectiles.size(); i++) {
			g.setColor(Color.black);
			g.fill(projectiles.get(i).getShape());
		}
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

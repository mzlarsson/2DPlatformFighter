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
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.GameMap;
import edu.chalmers.brawlbuddies.model.world.GameObject;
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
			//FIXME do other implementation.
			if(handler instanceof KeyInputHandler && ((KeyInputHandler)handler).getInput() == null){
				((KeyInputHandler)handler).setInput(gc.getInput());
			}
			game.move(players[i], handler.getDirection());
			
			if(handler.isActivated(GameKey.JUMP)){
				game.jump(players[i]);
			}
			if(handler.isActive(GameKey.SKILL1)){
				players[i].getCharacter().activateSkill(0);
			}
			if(handler.isActive(GameKey.SKILL2)){
				players[i].getCharacter().activateSkill(1);
			}
			if(handler.isActive(GameKey.SKILL3)){
				players[i].getCharacter().activateSkill(2);
			}
			if(handler.isActive(GameKey.SKILL4)){
				players[i].getCharacter().activateSkill(3);
			}
			
			//Update mouse position
			game.setPlayerAim(players[i], handler.getMousePosition(), handler.isMousePositionRelative());
		}

		//Update model
		game.update(delta);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//view.render(gc, g);
		game.getWorld().getMap().render(0, 0);
		List<GameObject> gameOB = game.getWorld().getImpassableObjects();
		for (GameObject o : gameOB) {
			g.setColor(Color.darkGray);
			g.draw(o.getShape());
		}
		for (Player p : game.getPlayers()) {
			p.getCharacter().draw();
			Position aimPos = p.getCharacter().getCenterPosition().add(p.getCharacter().getAim());
			g.setColor(Color.red);
			g.drawOval(aimPos.getX()-10, aimPos.getY()-10, 20, 20);

		}
		List<Projectile> projectiles = game.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
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

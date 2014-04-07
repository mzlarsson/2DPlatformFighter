package edu.chalmers.brawlbuddies;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.thoughtworks.xstream.XStream;

/**
 * A test game for the basic function of the model
 * 
 * @author David Gustafsson
 * 
 */

public class TestGame extends BasicGame {
	private GameModel game;

	/**
	 * Creates a new instance of test game
	 * 
	 * @param game
	 *            the game model for this test game
	 */
	public TestGame() {
		super("Demo");
	}
	public void startGame(Player[] players){
		game = new GameModel(players, new World(players, new GameMap()));
	}

	/**
	 * Renders the test game
	 */
	public void render(GameContainer gc, Graphics g) throws SlickException {
		game.getWorld().getMap().render(0,0);

		for(Player p:game.getPlayers()){
			p.getCharacter().draw();
		}
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Player[] players = { new Player("Player1", generateBob()) };
		this.startGame(players);
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Player[] players = game.getPlayers();
		if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
			game.move(players[0], Direction.RIGHT);
		} else if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
			game.move(players[0], Direction.LEFT);

		} else {
			game.move(players[0], Direction.NONE);
		}
		if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
			game.jump(players[0]);

		}
		game.update(delta);
	}

	private Character generateBob() {
		XStream xStream = new XStream();
		xStream.processAnnotations(Character.class);
		Object readObject = xStream.fromXML(new File("res/characters/bob.xml"));
		return (Character)readObject;
	}
	
	public static void main(String[] args) {
		try {
			TestGame game = new TestGame();
			AppGameContainer appgc;
			// Fixa world
			appgc = new AppGameContainer(game);

			appgc.setDisplayMode(1024, 768, false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

package edu.chalmers.brawlbuddies.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.thoughtworks.xstream.XStream;

import edu.chalmers.brawlbuddies.controller.Player;
import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.BrawlBuddies;
import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Skills.Effect;
import edu.chalmers.brawlbuddies.model.world.Character;
import edu.chalmers.brawlbuddies.model.world.GameMap;
import edu.chalmers.brawlbuddies.model.world.GameObject;
import edu.chalmers.brawlbuddies.model.world.Projectile;
import edu.chalmers.brawlbuddies.model.world.ProjectileCreator;
import edu.chalmers.brawlbuddies.model.world.World;
import edu.chalmers.brawlbuddies.services.factories.CharacterFactory;
import edu.chalmers.brawlbuddies.util.CharacterActionListener;

/**
 * A test game for the basic function of the model
 * 
 * @author David Gustafsson
 * 
 */

public class TestGame extends BasicGame {
	private BrawlBuddies game;

	/**
	 * Creates a new instance of test game
	 * 
	 * @param game
	 *            the game model for this test game
	 */
	public TestGame() {
		super("Demo");
	}

	public void startGame(Player[] players) {
		game = new BrawlBuddies(players, new World(players, new GameMap()));
	}

	/**
	 * Renders the test game
	 */
	public void render(GameContainer gc, Graphics g) throws SlickException {
		game.getWorld().getMap().render(0, 0);
		List<GameObject> gameOB = game.getWorld().getImpassableObjects();
		for (GameObject o : gameOB) {
			g.setColor(Color.darkGray);
			g.draw(o.getShape());
		}
		for (Player p : game.getPlayers()) {
			
			// Following Code is used to check if bob takes damage or if hes
			// dead
			p.getCharacter().draw();

		}
		List<Projectile> projectiles = game.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			g.setColor(Color.black);
			g.fill(projectiles.get(i).getShape());
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Player[] players = { new Player("Player1", generateBob(200,200)),
				new Player("Player2", generateBob(1400,200)) };
		// Make this testgame a listener for events from character
		this.startGame(players);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Player[] players = game.getPlayers();
		Direction dir = Direction.NONE;
		Direction dir2 = Direction.NONE;
		if (gc.getInput().isKeyDown(Input.KEY_LEFT)){
			dir2 = dir2.add(Direction.LEFT);
		}
		if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
			dir2 = dir2.add(Direction.RIGHT);
		}
		if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
			dir2 = dir2.add(Direction.DOWN);
		}
		if (gc.getInput().isKeyDown(Input.KEY_UP)){
			dir2 = dir2.add(Direction.UP);
		}
		if (gc.getInput().isKeyDown(Input.KEY_W)) {
			dir = dir.add(Direction.UP);
		}
		if (gc.getInput().isKeyDown(Input.KEY_S)) {
			dir = dir.add(Direction.DOWN);
		}
		if (gc.getInput().isKeyDown(Input.KEY_D)) {
			dir = dir.add(Direction.RIGHT);
		}
		if (gc.getInput().isKeyDown(Input.KEY_A)) {
			dir = dir.add(Direction.LEFT);
		}
		game.move(players[0], dir);
		players[0].getCharacter().updateAim(dir);
		game.move(players[1], dir2);
		players[1].getCharacter().updateAim(dir2);
		
		if (gc.getInput().isKeyPressed(Input.KEY_LALT)) {
			game.jump(players[0]);
		}
		if (gc.getInput().isKeyDown(Input.KEY_LSHIFT)) {
			players[0].getCharacter().activateSkill(0);
		}
		if (gc.getInput().isKeyDown(Input.KEY_LCONTROL)) {
			players[0].getCharacter().activateSkill(1);
		}
		if (gc.getInput().isKeyPressed(Input.KEY_I)){
			game.jump(players[1]);
		}
		if( gc.getInput().isKeyDown(Input.KEY_O)){
			players[1].getCharacter().activateSkill(0);
		}
		if( gc.getInput().isKeyDown(Input.KEY_P)){
			players[1].getCharacter().activateSkill(2);
		}
		if( gc.getInput().isKeyDown(Input.KEY_ESCAPE)){
			for (Player p : players) {
				p.getCharacter().restoreHealth();
			}
		}
		game.update(delta);
	}

	private Character generateBob(float x, float y) {
		return CharacterFactory.create("bob", x, y);
	}

	public static void main(String[] args) {
		try {
			TestGame game = new TestGame();
			AppGameContainer appgc;
			appgc = new AppGameContainer(game);

			appgc.setDisplayMode(1600, 800, false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

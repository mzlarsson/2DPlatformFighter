package edu.chalmers.brawlbuddies.model;

import edu.chalmers.brawlbuddies.controller.Player;
import edu.chalmers.brawlbuddies.model.world.Airborne;
import edu.chalmers.brawlbuddies.model.world.Character;
import edu.chalmers.brawlbuddies.model.world.World;

/**
 * Holds the logic for the game.
 * 
 * @author Patrik Haar
 * @version 0.1
 */
public class BrawlBuddies {

	private World world;
	private Player[] players;

	public BrawlBuddies(Player[] p, World w) {
		players = p;
		world = w;
	}

	/**
	 * Returns the players playing the game.
	 * 
	 * @return The Players.
	 */
	public Player[] getPlayers() {
		return players;
	}

	/**
	 * Moves the character connected to the player in the given direction.
	 * 
	 * @param player
	 *            The Player connected to the event.
	 * @param dir
	 *            The Direction to move the character.
	 */
	public void move(Player player, Direction dir) {
		player.getCharacter().move(dir);
	}

	/**
	 * Make the character connected to the player jump.
	 * 
	 * @param player
	 *            The Player connected to the event.
	 */
	public void jump(Player player) {
		player.getCharacter().makeJump();
	}

	/**
	 * Updates the Player´s positions and states.
	 * 
	 * @param delta
	 *            Time since last update in milliseconds.
	 */
	public void update(int delta) {
		for (Player p : players) {
			Position old = p.update(delta);
			Character ch = p.getCharacter();
			if (!world.isTileValid(ch.getCenterPosition())) {
				ch.setCenterPosition(world.getValidTilePosition(ch, old));
			} else {
				ch.setMovementState(new Airborne(ch, new Velocity(0,1000)));
			}
			
			if(!world.isValid(ch)){
				ch.setCenterPosition(world.getValidPosition(ch, old));
			}
		}
	}

	/**
	 * Returns the world used by the game.
	 * 
	 * @return The world used by the game.
	 */
	public World getWorld() {
		return world;
	}
}

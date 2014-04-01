package edu.chalmers.platformfighter;

/**
 * Holds the logic for the game.
 * @author Patrik Haar
 * @version 0.1
 */
public class GameModel {

	private World world;
	private Player[] players;
	
	public GameModel(Player[] p, World w){
		players = p;
		world = w;
	}
	public Player[] getPlayers(){
		return players;
	}
	/**
	 * Moves the character connected to the player in the given direction.
	 * @param player The Player connected to the event.
	 * @param dir The Direction to move the character.
	 */
	public void move(Player player, Direction dir) {
		Character ch = player.getCharacter();
		Position old = ch.move(dir);
		if (!world.isValid(ch)) {
			ch.setCenterPosition(world.getValidPosition(ch, old));
		}
	}
	
	/**
	 * Updates the Player´s positions and states.
	 * @param delta Time since last update in milliseconds.
	 */
	public void update(int delta) {
		for (Player p : players) {
			p.update(delta);
		}
//		List list = world.getGameObjects();
//		for (GameObject go : list) {
//			go.update(delta);
//		}
	}	
}

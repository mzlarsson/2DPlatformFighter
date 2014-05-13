package edu.chalmers.brawlbuddies.model;

public interface IBrawlBuddies {
	
	/**
	 * Moves the character with given ID in the given direction.
	 * @param characterID The ID of the character to move
	 * @param dir The Direction to move the character.
	 */
	public void move(int characterID, Direction dir);
	
	/**
	 * Make the given character jump.
	 * @param player The ID of the character to jump
	 */
	public void jump(int characterID);
	
	/**
	 * Activates a skill of the character with the given characterID
	 * @param characterID The ID of the wanted character
	 * @param skillIndex The index of the skill
	 */
	public void activateSkill(int characterID, int skillIndex);
	
	/**
	 * Sets the aim of the character with the given ID
	 * @param characterID The ID of the character
	 * @param aimPosition The position to aim at
	 * @param isRelative If the position is given relatively
	 */
	public void setAim(int characterID, Position aimPosition, boolean isRelative);
	
	/**
	 * Updates the game model
	 * @param delta Time since last update in milliseconds.
	 */
	public void update(int delta);
	
	/**
	 * Returns all the IDs of the character in the order they were created
	 * @return IDs of all the character sorted by creation time
	 */
	public int[] getCharacterIDs();
	
	/**
	 * Adds the given GameListener as a listener to catch game events.
	 * @param gl The GameListener to handle the events this class throws.
	 */
	public void addGameListener(GameListener gl);
	
	/**
	 * Removes the given GameListener as a listener to this class.
	 * @param gl The GameListener to be removed.
	 */
	public void removeGameListener(GameListener gl);
}

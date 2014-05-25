package edu.chalmers.brawlbuddies.model.skills;

import edu.chalmers.brawlbuddies.model.world.IGameObject;
/**
 * A interface describing effects
 * @author David Gustafsson
 * @revised Matz Larsson
 * @version 1.0
 *
 */
public interface IEffect {

	/**
	 * Trigger the Effect.
	 * @param sender The object to effect the other object.
	 * @param reciever The object getting effected.
	 * @return <code>true</code> if reciever successfully effected, <code>false</code> otherwise.
	 */
	public boolean effect(IGameObject sender, IGameObject reciever);
	
	/**
	 * Sets the creator ID.
	 * @param id The ID of the creator.
	 */
	public void setCreatorID(int id);
	
}

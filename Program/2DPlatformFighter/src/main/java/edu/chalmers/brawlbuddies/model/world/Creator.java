package edu.chalmers.brawlbuddies.model.world;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an event handler for handling creation calls of IGameObjects.
 * The design pattern "singleton" is used.
 * @author Matz Larsson
 * @version 1.0
 *
 */

public class Creator {
	
	private List<CreatorListener> listeners;
	private static Creator instance = null;

	private Creator() {
		listeners = new ArrayList<CreatorListener>();
	}
	
	/**
	 * Retrieves the instance of this class
	 * @return The instance of this class
	 */
	public static Creator getInstance(){
		if(instance == null){
			instance = new Creator();
		}
		
		return instance;
	}
	
	/**
	 * Adds the given listener for recieving events from this class
	 * @param listener The listener to add
	 */
	public void addListener(CreatorListener listener){
		getInstance().listeners.add(listener);
	}
	
	/**
	 * Sets the given listener to stop listen for events from this class
	 * @param listener The listener to remove
	 */
	public void removeListener(CreatorListener listener){
		this.listeners.remove(listener);
	}
	
	/**
	 * Notifies all listeners about the given IGameObject
	 * @param object The object to notify about
	 */
	public void fireEvent(IGameObject object){
		for(CreatorListener listener : getInstance().listeners){
			listener.createdObject(object);
		}
	}

}

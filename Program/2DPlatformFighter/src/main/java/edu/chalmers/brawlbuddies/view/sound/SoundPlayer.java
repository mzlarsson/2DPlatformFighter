package edu.chalmers.brawlbuddies.view.sound;

import java.util.Map;
import java.util.TreeMap;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.eventbus.IEventBusSubscriber;
import edu.chalmers.brawlbuddies.view.CharacterWrapper;
import edu.chalmers.brawlbuddies.view.IWrapper;
import edu.chalmers.brawlbuddies.view.ProjectileWrapper;
import edu.chalmers.brawlbuddies.model.skills.ISkill;

public class SoundPlayer implements IEventBusSubscriber{
	
	private static SoundPlayer instance;
	private Map<Integer, SoundData> sounds;

	/**
	 * Initiate a new SoundPlayer
	 */
	private SoundPlayer() {
		sounds = new TreeMap<Integer, SoundData>();
	}
	
	/**
	 * Get an instance of a SoundPlayer
	 * @return An instance of a SoundPlayer
	 */
	public static SoundPlayer getInstance(){
		if(instance == null){
			instance = new SoundPlayer();
		}
		
		return instance;
	}
	
	/**
	 * Starts this sound player
	 */
	public void start(){
		EventBus.getInstance().addSubscriber(this);
	}
	
	/**
	 * Stops this sound player
	 */
	public void stop(){
		EventBus.getInstance().removeSubscriber(this);
		
		for(SoundData data : this.sounds.values()){
			data.stop();
		}
	}

	/**
	 * Method for handling incoming events from the EventBus
	 * @param event The event given from the EventBus
	 */
	public void eventPerformed(EventBusEvent event) {
		int typeID = -1, uniqueID = -1;
		if(event.getActor() != null && event.getActor() instanceof IWrapper){
			typeID = ((IWrapper)event.getActor()).getTypeID();
			uniqueID = ((IWrapper)event.getActor()).getUniqeID();
		}else if(event.getRecipient() != null && event.getRecipient() instanceof IWrapper){
			typeID = ((IWrapper)event.getRecipient()).getTypeID();
			uniqueID = ((IWrapper)event.getRecipient()).getUniqeID();
		}
		
		if(typeID>=0){
			if(!this.sounds.containsKey(typeID)){
				this.sounds.put(typeID, new SoundData(typeID));
			}
			
			IWrapper actor = (event.getActor() instanceof IWrapper?(IWrapper)event.getActor():null);
			IWrapper recipient = (event.getRecipient() instanceof IWrapper?(IWrapper)event.getRecipient():null);
			String soundName = getSoundName(actor, recipient, event.getName());
			this.sounds.get(typeID).playSound(uniqueID, soundName!=null?soundName:event.getName());
		}
	}
	
	/**
	 * Calculates a non-standard sound name if special sounds are wanted.
	 * Null is returned if standard sound should be used.
	 * @param actor The actor as described in the EventBusEvent
	 * @param recipient The recipient as described in the EventBusEvent
	 * @param name The name as described in the EventBusEvent
	 * @return The sound name to be used. Null will trigger the default one.
	 */
	private String getSoundName(IWrapper actor, IWrapper recipient, String name){
		//No valid data was recieved
		if(actor == null && recipient == null){
			return null;
		}
		
		//Determine which wrapper is the active one
		IWrapper activeWrapper = actor;
		if(actor == null){
			activeWrapper = recipient;
		}
		
		//Separate by class
		if(activeWrapper instanceof ProjectileWrapper){
			
		}else if(activeWrapper instanceof CharacterWrapper){
			
		}
		
		//Return null if no special conditions were found
		return null;
	}

}

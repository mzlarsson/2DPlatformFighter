package edu.chalmers.brawlbuddies.view.sound;

import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.eventbus.IEventBusSubscriber;
import edu.chalmers.brawlbuddies.view.CharacterWrapper;
import edu.chalmers.brawlbuddies.view.IWrapper;
import edu.chalmers.brawlbuddies.view.ProjectileWrapper;

public class SoundPlayer implements IEventBusSubscriber{
	
	private float musicVolume = 1.0f;
	private float soundVolume = 1.0f;
	private float prevSoundVolume = 1.0f;
	
	private static SoundPlayer instance;
	private Map<Integer, SoundData> sounds;
	private Music music;
	private boolean playingMusic = false;
	private boolean stopped = true;

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
	 * Sets the volume of all the sounds
	 * @param volume The volume to use
	 */
	public void setSoundVolume(float volume){
		this.soundVolume = volume;
		for(SoundData data : this.sounds.values()){
			data.setVolume(volume);
		}
	}
	
	/**
	 * Starts this sound player
	 */
	public void startSounds(){
		if(this.stopped){
			EventBus.getInstance().addSubscriber(this);
			this.setSoundVolume(this.prevSoundVolume);
			this.stopped = false;
		}
	}
	
	/**
	 * Stops this sound player
	 */
	public void stopSounds(){
		if(!this.stopped){
			EventBus.getInstance().removeSubscriber(this);
			
			this.prevSoundVolume = this.soundVolume;
			for(SoundData data : this.sounds.values()){
				data.stop();
			}
		
			this.setSoundVolume(0.0f);
			this.stopped = true;
		}
	}
	
	/**
	 * Sets the music to the file in the current path
	 * @param path The path to the song to use
	 */
	public void setMusic(String path){
		try {
			this.setMusic(new Music(path));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the music to the given variable
	 * @param music The music object to use
	 */
	public void setMusic(Music music){
		this.music = music;
		
		if(this.playingMusic){
			this.startMusic();
		}
	}
	
	/**
	 * Sets the volume of the music
	 * @param volume The volume to use
	 */
	public void setMusicVolume(float volume){
		this.musicVolume = volume;
		if(this.music != null){
			this.music.setVolume(this.musicVolume);
		}
	}
	
	/**
	 * Starts the music
	 */
	public void startMusic(){
		this.playingMusic = true;
		if(this.music != null){
			if(!this.music.playing()){
				this.music.loop(1.0f, this.musicVolume);
			}
		}
	}
	
	/**
	 * Stops the music
	 */
	public void stopMusic(){
		this.playingMusic = false;
		if(this.music != null){
			this.music.stop();
		}
	}

	/**
	 * Method for handling incoming events from the EventBus
	 * @param event The event given from the EventBus
	 */
	public void eventPerformed(EventBusEvent event) {
		if(!this.stopped){
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
					SoundData data = new SoundData(typeID);
					data.setVolume(this.soundVolume);
					this.sounds.put(typeID, data);
				}
				
				IWrapper actor = (event.getActor() instanceof IWrapper?(IWrapper)event.getActor():null);
				IWrapper recipient = (event.getRecipient() instanceof IWrapper?(IWrapper)event.getRecipient():null);
				String soundName = getSoundName(actor, recipient, event.getName());
				this.sounds.get(typeID).playSound(uniqueID, soundName!=null?soundName:event.getName());
			}
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

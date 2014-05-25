package edu.chalmers.brawlbuddies.view.sound;

import java.util.Map;
/**
 * A class that handles sound information of a particular game object
 * @author Matz Larsson
 * @version 1.0
 *
 */
public class SoundData {
	
	private Map<String, SoundKeeper> sounds;

	/**
	 * Initiates the sounds of a type of an object
	 * @param typeID The type ID of the object to use
	 */
	public SoundData(int typeID){
		this.sounds = SoundFactory.getInstance().getSounds(typeID);
	}
	
	/**
	 * Plays the sound that corresponds to the given soundName
	 * @param soundName The name of the sound as specified in the sound document
	 */
	public void playSound( String soundName){
		SoundKeeper sound = this.sounds.get(soundName);
		
		if(sound != null){
			sound.playSound();
			for(String stopper : sound.getStoppers()){
				this.stopSound(stopper);
			}
		}
	}
	
	/**
	 * Stops the sounds that corresponds to the given soundName
	 * @param soundName The name of the sound as specified in the sound document
	 */
	public void stopSound(String soundName){
		SoundKeeper sound = this.sounds.get(soundName);
		
		if(sound != null){
			sound.stopSound();
		}
	}
	
	/**
	 * Sets the volume of all sounds stored in this object
	 * @param volume The volume to use as used in slick SoundStore
	 * @see org.newdawn.slick.openal.SoundStore
	 */
	public void setVolume(float volume){
		for(SoundKeeper sound : this.sounds.values()){
			sound.setVolume(volume);
		}
	}
	
	/**
	 * Stops all sounds
	 */
	public void stop(){
		for(SoundKeeper sound : this.sounds.values()){
			sound.stopSound();
		}
	}

}

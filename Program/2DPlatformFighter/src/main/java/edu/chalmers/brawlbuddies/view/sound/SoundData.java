package edu.chalmers.brawlbuddies.view.sound;

import java.util.Map;

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
	 * @param uniqueID The unique ID to the object who requested this
	 * @param soundName The name of the sound as specified in the sound document
	 */
	public void playSound(int uniqueID, String soundName){
		SoundKeeper sound = this.sounds.get(soundName);
		
		if(sound != null){
			sound.playSound(uniqueID);
			for(String stopper : sound.getStoppers()){
				this.stopSound(uniqueID, stopper);
			}
		}
	}
	
	/**
	 * Stops the sounds that corresponds to the given soundName
	 * @param uniqueID The unique ID to the object who requested this
	 * @param soundName The name of the sound as specified in the sound document
	 */
	public void stopSound(int uniqueID, String soundName){
		SoundKeeper sound = this.sounds.get(soundName);
		
		if(sound != null){
			sound.stopSound(uniqueID);
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
			sound.stopAllSounds();
		}
	}

}

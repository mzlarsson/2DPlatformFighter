package edu.chalmers.brawlbuddies.view.sound;

import org.newdawn.slick.Sound;

public class SoundKeeper {
	
	//private Map<Integer, Sound> currentSounds;
	private Sound sound;
	private float volume = 1.0f;
	private boolean loop = false;
	private String[] stoppers;

	/**
	 * Creates a new wrapper for a sound
	 * @param sound The actual sound
	 * @param stoppers Array containing the names of all names to stop when this sound plays
	 */
	public SoundKeeper(Sound sound, String[] stoppers){
		this(sound, stoppers, false);
	}
	
	/**
	 * Creates a new wrapper for a sound
	 * @param sound The actual sound
	 * @param stoppers Array containing the names of all names to stop when this sound plays
	 * @param loop If the sound shop loop
	 */
	public SoundKeeper(Sound sound, String[] stoppers, boolean loop){
		//this.currentSounds = new TreeMap<Integer, Sound>();
		this.sound = sound;
		this.stoppers = stoppers;
		this.loop = loop;
	}
	
	/**
	 * Retrieves the names of sounds to stop when this sound starts playing
	 * @return The names of sounds to stop when this sound starts playing
	 */
	public String[] getStoppers(){
		return this.stoppers;
	}
	
	/**
	 * Plays the current sound
	 * @param uniqueID The unique ID of the object who requested this action
	 */
	public void playSound(int uniqueID){
		/*if(this.currentSounds.get(uniqueID) == null){
			this.currentSounds.put(uniqueID, SlickUtil.copy(this.sound));
		}else if(this.currentSounds.get(uniqueID).playing()){
			this.currentSounds.get(uniqueID).stop();
		}
		
		if(this.loop){
			this.currentSounds.get(uniqueID).loop(1.0f, this.volume);
		}else{
			this.currentSounds.get(uniqueID).play(1.0f, this.volume);
		}*/
		
		if(this.loop){
			this.sound.loop(1.0f, this.volume);
		}else{
			this.sound.play(1.0f, this.volume);
		}
	}
	
	/**
	 * Stops the current sound
	 * @param uniqueID The unique ID of the object who requested this action
	 */
	public void stopSound(int uniqueID){
		/*if(this.currentSounds.get(uniqueID) != null){
			this.currentSounds.get(uniqueID).stop();
		}*/
		
		this.sound.stop();
	}
	
	/**
	 * Stops all instances of this sound currently playing
	 */
	public void stopAllSounds(){
		/*Set<Integer> keys = this.currentSounds.keySet();
		for(Integer i : keys){
			stopSound(i);
		}*/
		
		this.stopSound(-1);
	}
	
	/**
	 * Sets the volume to the given value
	 * @param volume The volume to use as used in slick SoundStore
	 * @see org.newdawn.slick.openal.SoundStore
	 */
	public void setVolume(float volume){
		this.volume = volume;
	}
	
	/**
	 * Checks whether this sound is playable
	 * @return <code>true</code> if the sound is playable, <code>false</code> otherwise
	 */
	public boolean isPlayable(){
		return this.sound != null;
	}

}

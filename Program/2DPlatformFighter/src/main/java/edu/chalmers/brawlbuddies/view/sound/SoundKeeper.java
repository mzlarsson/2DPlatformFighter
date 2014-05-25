package edu.chalmers.brawlbuddies.view.sound;

import org.newdawn.slick.Sound;
/**
 * A class to contain and manage a single sound object
 * @author Matz Larsson
 *
 */
public class SoundKeeper {
	
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
	 */
	public void playSound(){
		if(this.loop){
			this.sound.loop(1.0f, this.volume);
		}else{
			this.sound.play(1.0f, this.volume);
		}
	}
	
	/**
	 * Stops the current sound
	 */
	public void stopSound(){
		this.sound.stop();
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

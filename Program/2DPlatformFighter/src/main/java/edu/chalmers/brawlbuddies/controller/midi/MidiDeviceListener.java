package edu.chalmers.brawlbuddies.controller.midi;

/**
 * Interface for handling events from a MIDI device.
 * Recommended is to use a DeviceCommunicator.
 * @author Matz Larsson
 * @version 0.1
 *
 */

public interface MidiDeviceListener {

	/**
	 * Triggered when the key of the MIDI device is pressed
	 * @param key The key which is pressed
	 */
	public void keyPressed(int key);

	/**
	 * Triggered when the key of the MIDI device is released
	 * @param key The key which is released
	 */
	public void keyReleased(int key);
	
}

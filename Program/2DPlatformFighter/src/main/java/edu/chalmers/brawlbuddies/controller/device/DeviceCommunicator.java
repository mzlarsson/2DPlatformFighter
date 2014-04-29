package edu.chalmers.brawlbuddies.controller.device;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

/**
 * Communicator between the computer and a MIDI device such as a synth.
 * This class requires a Transmitter retrieved from a MIDI device.
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class DeviceCommunicator implements Receiver{

	private int[] keys;
	private boolean[] pressed;
	private String name;
	private boolean on = true;
	
	private List<DeviceListener> listeners;

	/**
	 * Creates a new communicator as a bridge towards a Transmitter.
	 * @param name The name of the device
	 * @param trans A transmitter from the device
	 */
	public DeviceCommunicator(String name, Transmitter trans) {
		this.name = name;
		this.keys = new int[128];
		this.pressed = new boolean[128];
		trans.setReceiver(this);

		this.listeners = new ArrayList<DeviceListener>();
	}
	
	/**
	 * Adds a listener to retrieve updates about pressed/released keys
	 * @param listener The listener to add
	 */
	public void addDeviceListener(DeviceListener listener){
		this.listeners.add(listener);
	}
	
	/**
	 * Removes a listener which no longer gets updates about pressed/released keys
	 * @param listener The listener to remove
	 */
	public void removeDeviceListener(DeviceListener listener){
		this.listeners.remove(listener);
	}
	
	/**
	 * Notifies all listeners about a change
	 * @param key The key which has been changed
	 * @param pressed <code>true</code> if key is pressed, <code>false</code> if released
	 */
	private void notifyAll(int key, boolean pressed){
		for(DeviceListener listener : this.listeners){
			if(pressed){
				listener.keyPressed(key);
			}else{
				listener.keyReleased(key);
			}
		}
	}
	
	/**
	 * Returns the name of the communication
	 * @return The name of this communication
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Retrieves the value of a certain key.
	 * @param key The key to check
	 * @return -1 if broken, 0 if not pressed or the hardness (1-127) if pressed
	 */
	public int getKeyValue(int key){
		if(key>=0 && key<this.keys.length){
			return this.keys[key];
		}else{
			return -1;
		}
	}
	
	/**
	 * Checks whether the key has been pressed down since the last check
	 * @param key The key to check for
	 * @return <code>true</code> if the key has been pressed since last check, <code>false</code> otherwise
	 */
	public boolean isKeyPressed(int key){
		boolean pressed = getKeyValue(key)>0 && this.pressed[key];
		this.pressed[key] = false;
		return pressed;
	}

	/**
	 * Registers a message from the MIDI device
	 * @param message The message
	 * @param timeStamp The time elapsed since the communication was opened.
	 */
	public void send(MidiMessage message, long timeStamp) {
		if(on && message.getLength()>1){
			byte[] messageContent = message.getMessage();
			final int tone = (int)messageContent[1];
			final int hardness = (int)messageContent[2];
			
			this.keys[tone] = hardness;
			this.pressed[tone] = true;
			
			notifyAll(tone, hardness>0);
		}
	}

	/**
	 * Stops listening for any new data
	 */
	public void close() {
		System.out.println("Closing");
		on = false;
	}
	
	/**
	 * Creates a representative String for this object
	 * @return A representative String containing information about this object.
	 */
	public String toString(){
		return "-- DeviceListener: "+this.getName()+" ("+(on?"on":"off")+") --";
	}
}

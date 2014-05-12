package edu.chalmers.brawlbuddies.controller.midi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
import javax.swing.Timer;

/**
 * Communicator between the computer and a MIDI device such as a synth.
 * This class requires a Transmitter retrieved from a MIDI device.
 * @author Matz Larsson
 * @version 1.0
 *
 */

public class MidiDeviceCommunicator implements Receiver{

	private int[] keys;
	private boolean[] pressed;
	private String name;
	private boolean on = true;
	
	private List<MidiDeviceListener> listeners;

	/**
	 * Creates a new communicator as a bridge towards a Transmitter.
	 * @param name The name of the device
	 * @param trans A transmitter from the device
	 */
	public MidiDeviceCommunicator(String name, Transmitter trans) {
		this.name = name;
		this.keys = new int[128];
		this.pressed = new boolean[128];
		trans.setReceiver(this);

		this.listeners = new ArrayList<MidiDeviceListener>();
	}
	
	/**
	 * Adds a listener to retrieve updates about pressed/released keys
	 * @param listener The listener to add
	 */
	public void addDeviceListener(MidiDeviceListener listener){
		this.listeners.add(listener);
	}
	
	/**
	 * Removes a listener which no longer gets updates about pressed/released keys
	 * @param listener The listener to remove
	 */
	public void removeDeviceListener(MidiDeviceListener listener){
		this.listeners.remove(listener);
	}
	
	/**
	 * Notifies all listeners about a key that is held down
	 * @param key The key that is held down
	 */
	private void notifyHeld(int key){
		for(MidiDeviceListener listener : this.listeners){
			listener.keyHeld(key);
		}
	}
	
	/**
	 * Notifies all listeners about a change
	 * @param key The key which has been changed
	 * @param pressed <code>true</code> if key is pressed, <code>false</code> if released
	 */
	private void notifyAll(int key, boolean pressed){
		for(MidiDeviceListener listener : this.listeners){
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
			//Fetch the information
			byte[] messageContent = message.getMessage();
			final int tone = (int)messageContent[1];
			final int hardness = (int)messageContent[2];
			
			//Set all variables
			this.keys[tone] = hardness;
			this.pressed[tone] = true;
			
			//Notify listeners about change
			notifyAll(tone, hardness>0);
			
			//Start timer to take care of events for key held down
			final Timer heldTimer = new Timer(10, new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					if(!isKeyPressed(tone)){
						((Timer)ae.getSource()).stop();
					}else{
						notifyHeld(tone);
					}
				}
			});
			heldTimer.start();
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

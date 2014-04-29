package edu.chalmers.brawlbuddies.controller.device;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;

/**
 * A class for handling inputs from a synth.
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class SynthInput {
	
	public static final int KEY_C0 = 24;
	public static final int KEY_Db0 = 25;
	public static final int KEY_D0 = 26;
	public static final int KEY_Eb0 = 27;
	public static final int KEY_E0 = 28;
	public static final int KEY_F0 = 29;
	public static final int KEY_Gb0 = 30;
	public static final int KEY_G0 = 31;
	public static final int KEY_Ab0 = 32;
	public static final int KEY_A0 = 33;
	public static final int KEY_Bb0 = 34;
	public static final int KEY_B0 = 35;
	public static final int KEY_C1 = 36;
	public static final int KEY_Db1 = 37;
	public static final int KEY_D1 = 38;
	public static final int KEY_Eb1 = 39;
	public static final int KEY_E1 = 40;
	public static final int KEY_F1 = 41;
	public static final int KEY_Gb1 = 42;
	public static final int KEY_G1 = 43;
	public static final int KEY_Ab1 = 44;
	public static final int KEY_A1 = 45;
	public static final int KEY_Bb1 = 46;
	public static final int KEY_B1 = 47;
	public static final int KEY_C2 = 48;
	public static final int KEY_Db2 = 49;
	public static final int KEY_D2 = 50;
	public static final int KEY_Eb2 = 51;
	public static final int KEY_E2 = 52;
	public static final int KEY_F2 = 53;
	public static final int KEY_Gb2 = 54;
	public static final int KEY_G2 = 55;
	public static final int KEY_Ab2 = 56;
	public static final int KEY_A2 = 57;
	public static final int KEY_Bb2 = 58;
	public static final int KEY_B2 = 59;
	public static final int KEY_C3 = 60;
	public static final int KEY_Db3 = 61;
	public static final int KEY_D3 = 62;
	public static final int KEY_Eb3 = 63;
	public static final int KEY_E3 = 64;
	public static final int KEY_F3 = 65;
	public static final int KEY_Gb3 = 66;
	public static final int KEY_G3 = 67;
	public static final int KEY_Ab3 = 68;
	public static final int KEY_A3 = 69;
	public static final int KEY_Bb3 = 70;
	public static final int KEY_B3 = 71;
	public static final int KEY_C4 = 72;
	public static final int KEY_Db4 = 73;
	public static final int KEY_D4 = 74;
	public static final int KEY_Eb4 = 75;
	public static final int KEY_E4 = 76;
	public static final int KEY_F4 = 77;
	public static final int KEY_Gb4 = 78;
	public static final int KEY_G4 = 79;
	public static final int KEY_Ab4 = 80;
	public static final int KEY_A4 = 81;
	public static final int KEY_Bb4 = 82;
	public static final int KEY_B4 = 83;
	public static final int KEY_C5 = 84;
	public static final int KEY_Db5 = 85;
	public static final int KEY_D5 = 86;
	public static final int KEY_Eb5 = 87;
	public static final int KEY_E5 = 88;
	public static final int KEY_F5 = 89;
	public static final int KEY_Gb5 = 90;
	public static final int KEY_G5 = 91;
	public static final int KEY_Ab5 = 92;
	public static final int KEY_A5 = 93;
	public static final int KEY_Bb5 = 94;
	public static final int KEY_B5 = 95;
	public static final int KEY_C6 = 96;
	public static final int KEY_Db6 = 97;
	public static final int KEY_D6 = 98;
	public static final int KEY_Eb6 = 99;
	public static final int KEY_E6 = 100;
	public static final int KEY_F6 = 101;
	public static final int KEY_Gb6 = 102;
	public static final int KEY_G6 = 103;
	public static final int KEY_Ab6 = 104;
	public static final int KEY_A6 = 105;
	public static final int KEY_Bb6 = 106;
	public static final int KEY_B6 = 107;
	public static final int KEY_C7 = 108;
	public static final int KEY_Db7 = 109;
	public static final int KEY_D7 = 110;
	public static final int KEY_Eb7 = 111;
	public static final int KEY_E7 = 112;
	public static final int KEY_F7 = 113;
	public static final int KEY_Gb7 = 114;
	public static final int KEY_G7 = 115;
	public static final int KEY_Ab7 = 116;
	public static final int KEY_A7 = 117;
	public static final int KEY_Bb7 = 118;
	public static final int KEY_B7 = 119;
	public static final int KEY_C8 = 120;
	public static final int KEY_Db8 = 121;
	public static final int KEY_D8 = 122;
	public static final int KEY_Eb8 = 123;
	public static final int KEY_E8 = 124;
	public static final int KEY_F8 = 125;
	public static final int KEY_Gb8 = 126;
	public static final int KEY_G8 = 127;
	
	private DeviceCommunicator communicator;

	/**
	 * Creates a new SynthInput object that is linked to a MidiDevice
	 * which it fetches data from.
	 * @param device The MIDI-device to use.
	 */
	public SynthInput(MidiDevice device){
		if(device.getTransmitters().size()==0){
			try {
				device.getTransmitter();
			} catch (MidiUnavailableException e) {}
		}
		
		this.communicator = new DeviceCommunicator(device.getDeviceInfo().getName(), device.getTransmitters().get(0));
	}
	
	/**
	 * Adds a listener for events from the device
	 * @param listener The listener to add
	 */
	public void addDeviceListener(DeviceListener listener){
		this.communicator.addDeviceListener(listener);
	}
	/**
	 * Removes a listener from the device
	 * @param listener The listener to remove
	 */
	public void removeDeviceListener(DeviceListener listener){
		this.communicator.addDeviceListener(listener);
	}
	
	/**
	 * Checks if a key is pressed
	 * @param key The value of the key to check
	 * @return <code>true</code> if the key is pressed, <code>false</code> otherwise
	 */
	public boolean isPressed(int key){
		return this.communicator.isKeyPressed(key);
	}
	
	/**
	 * Checks if a key is pressed hard (over 70% of maximum)
	 * @param key The value of the key to check
	 * @return <code>true</code> if the key is pressed with hardness>90 (127 max), <code>false</code> otherwise
	 */
	public boolean isPressedHard(int key){
		return this.communicator.getKeyValue(key)>90;
	}
	
	/**
	 * Checks if a key is held down
	 * @param key The value of the key to check
	 * @return <code>true</code> if the key is held down, <code>false</code> otherwise
	 */
	public boolean isHeldDown(int key){
		return this.communicator.getKeyValue(key)>0;
	}

	/**
	 * Checks if there are no possible value for this key
	 * @param key The key to check
	 * @return <code>true</code> if this key is invalid or not working, <code>false</code> otherwise
	 */
	public boolean isBroken(int key){
		return this.communicator.getKeyValue(key)<0;
	}
	
	/**
	 * Checks if any keys are pressed down
	 * @return <code>true</code> if any key is pressed down, <code>false</code> otherwise
	 */
	public boolean hasActiveKeys(){
		int currentKey = 0;
		while(this.communicator.getKeyValue(currentKey)>=0){
			if(this.communicator.getKeyValue(currentKey)>0){
				return true;
			}
			currentKey++;
		}
		return false;
	}
	
	/**
	 * Retrieves the actual name of the key
	 * @param key The key to use
	 * @return The actual name of the given key
	 */
	public String getKeyName(int key){
		//First key starts at 24.
		key -= 24;
		
		String[] tones = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};
		return tones[key%12]+(key/12);
	}
	
	/**
	 * Returns all keys that are pressed down at the moment
	 * @return A list of all keys that are currently pressed down
	 */
	public List<Integer> getAllActiveKeys(){
		List<Integer> activeKeys = new ArrayList<Integer>();
		int currentKey = 0;
		int keyValue = 0;
		while((keyValue = this.communicator.getKeyValue(currentKey)) >= 0){
			if(keyValue>0){
				activeKeys.add(currentKey);
			}
			currentKey++;
		}

		return activeKeys;
	}
}

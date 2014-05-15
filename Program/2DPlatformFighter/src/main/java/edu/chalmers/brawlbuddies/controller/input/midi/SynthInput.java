package edu.chalmers.brawlbuddies.controller.input.midi;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;

/**
 * A class for handling inputs from a synth.
 * @author Matz Larsson
 * @version 1.0
 *
 */

public class SynthInput {
	
	/** The key C in the first octave */
	public static final int KEY_C0 = 24;
	/** The key Db in the first octave */
	public static final int KEY_Db0 = 25;
	/** The key D in the first octave */
	public static final int KEY_D0 = 26;
	/** The key Eb in the first octave */
	public static final int KEY_Eb0 = 27;
	/** The key E in the first octave */
	public static final int KEY_E0 = 28;
	/** The key F in the first octave */
	public static final int KEY_F0 = 29;
	/** The key Gb in the first octave */
	public static final int KEY_Gb0 = 30;
	/** The key G in the first octave */
	public static final int KEY_G0 = 31;
	/** The key Ab in the first octave */
	public static final int KEY_Ab0 = 32;
	/** The key A in the first octave */
	public static final int KEY_A0 = 33;
	/** The key Bb in the first octave */
	public static final int KEY_Bb0 = 34;
	/** The key B in the first octave */
	public static final int KEY_B0 = 35;
	/** The key C in the second octave */
	public static final int KEY_C1 = 36;
	/** The key Db in the second octave */
	public static final int KEY_Db1 = 37;
	/** The key D in the second octave */
	public static final int KEY_D1 = 38;
	/** The key Eb in the second octave */
	public static final int KEY_Eb1 = 39;
	/** The key E in the second octave */
	public static final int KEY_E1 = 40;
	/** The key F in the second octave */
	public static final int KEY_F1 = 41;
	/** The key Gb in the second octave */
	public static final int KEY_Gb1 = 42;
	/** The key G in the second octave */
	public static final int KEY_G1 = 43;
	/** The key Ab in the second octave */
	public static final int KEY_Ab1 = 44;
	/** The key A in the second octave */
	public static final int KEY_A1 = 45;
	/** The key Bb in the second octave */
	public static final int KEY_Bb1 = 46;
	/** The key B in the second octave */
	public static final int KEY_B1 = 47;
	/** The key C in the third octave */
	public static final int KEY_C2 = 48;
	/** The key Db in the third octave */
	public static final int KEY_Db2 = 49;
	/** The key D in the third octave */
	public static final int KEY_D2 = 50;
	/** The key Eb in the third octave */
	public static final int KEY_Eb2 = 51;
	/** The key E in the third octave */
	public static final int KEY_E2 = 52;
	/** The key F in the third octave */
	public static final int KEY_F2 = 53;
	/** The key Gb in the third octave */
	public static final int KEY_Gb2 = 54;
	/** The key G in the third octave */
	public static final int KEY_G2 = 55;
	/** The key Ab in the third octave */
	public static final int KEY_Ab2 = 56;
	/** The key A in the third octave */
	public static final int KEY_A2 = 57;
	/** The key Bb in the third octave */
	public static final int KEY_Bb2 = 58;
	/** The key B in the third octave */
	public static final int KEY_B2 = 59;
	/** The key C in the fourth octave */
	public static final int KEY_C3 = 60;
	/** The key Db in the fourth octave */
	public static final int KEY_Db3 = 61;
	/** The key D in the fourth octave */
	public static final int KEY_D3 = 62;
	/** The key Eb in the fourth octave */
	public static final int KEY_Eb3 = 63;
	/** The key E in the fourth octave */
	public static final int KEY_E3 = 64;
	/** The key F in the fourth octave */
	public static final int KEY_F3 = 65;
	/** The key Gb in the fourth octave */
	public static final int KEY_Gb3 = 66;
	/** The key G in the fourth octave */
	public static final int KEY_G3 = 67;
	/** The key Ab in the fourth octave */
	public static final int KEY_Ab3 = 68;
	/** The key A in the fourth octave */
	public static final int KEY_A3 = 69;
	/** The key Bb in the fourth octave */
	public static final int KEY_Bb3 = 70;
	/** The key B in the fourth octave */
	public static final int KEY_B3 = 71;
	/** The key C in the fifth octave */
	public static final int KEY_C4 = 72;
	/** The key Db in the fifth octave */
	public static final int KEY_Db4 = 73;
	/** The key D in the fifth octave */
	public static final int KEY_D4 = 74;
	/** The key Eb in the fifth octave */
	public static final int KEY_Eb4 = 75;
	/** The key E in the fifth octave */
	public static final int KEY_E4 = 76;
	/** The key F in the fifth octave */
	public static final int KEY_F4 = 77;
	/** The key Gb in the fifth octave */
	public static final int KEY_Gb4 = 78;
	/** The key G in the fifth octave */
	public static final int KEY_G4 = 79;
	/** The key Ab in the fifth octave */
	public static final int KEY_Ab4 = 80;
	/** The key A in the fifth octave */
	public static final int KEY_A4 = 81;
	/** The key Bb in the fifth octave */
	public static final int KEY_Bb4 = 82;
	/** The key B in the fifth octave */
	public static final int KEY_B4 = 83;
	/** The key C in the sixth octave */
	public static final int KEY_C5 = 84;
	/** The key Db in the sixth octave */
	public static final int KEY_Db5 = 85;
	/** The key D in the sixth octave */
	public static final int KEY_D5 = 86;
	/** The key Eb in the sixth octave */
	public static final int KEY_Eb5 = 87;
	/** The key E in the sixth octave */
	public static final int KEY_E5 = 88;
	/** The key F in the sixth octave */
	public static final int KEY_F5 = 89;
	/** The key Gb in the sixth octave */
	public static final int KEY_Gb5 = 90;
	/** The key G in the sixth octave */
	public static final int KEY_G5 = 91;
	/** The key Ab in the sixth octave */
	public static final int KEY_Ab5 = 92;
	/** The key A in the sixth octave */
	public static final int KEY_A5 = 93;
	/** The key Bb in the sixth octave */
	public static final int KEY_Bb5 = 94;
	/** The key B in the sixth octave */
	public static final int KEY_B5 = 95;
	/** The key C in the seventh octave */
	public static final int KEY_C6 = 96;
	/** The key Db in the seventh octave */
	public static final int KEY_Db6 = 97;
	/** The key D in the seventh octave */
	public static final int KEY_D6 = 98;
	/** The key Eb in the seventh octave */
	public static final int KEY_Eb6 = 99;
	/** The key E in the seventh octave */
	public static final int KEY_E6 = 100;
	/** The key F in the seventh octave */
	public static final int KEY_F6 = 101;
	/** The key Gb in the seventh octave */
	public static final int KEY_Gb6 = 102;
	/** The key G in the seventh octave */
	public static final int KEY_G6 = 103;
	/** The key Ab in the seventh octave */
	public static final int KEY_Ab6 = 104;
	/** The key A in the seventh octave */
	public static final int KEY_A6 = 105;
	/** The key Bb in the seventh octave */
	public static final int KEY_Bb6 = 106;
	/** The key B in the seventh octave */
	public static final int KEY_B6 = 107;
	/** The key C in the eight octave */
	public static final int KEY_C7 = 108;
	/** The key Db in the eight octave */
	public static final int KEY_Db7 = 109;
	/** The key D in the eight octave */
	public static final int KEY_D7 = 110;
	/** The key Eb in the eight octave */
	public static final int KEY_Eb7 = 111;
	/** The key E in the eight octave */
	public static final int KEY_E7 = 112;
	/** The key F in the eight octave */
	public static final int KEY_F7 = 113;
	/** The key Gb in the eight octave */
	public static final int KEY_Gb7 = 114;
	/** The key G in the eight octave */
	public static final int KEY_G7 = 115;
	/** The key Ab in the eight octave */
	public static final int KEY_Ab7 = 116;
	/** The key A in the eight octave */
	public static final int KEY_A7 = 117;
	/** The key Bb in the eight octave */
	public static final int KEY_Bb7 = 118;
	/** The key B in the eight octave */
	public static final int KEY_B7 = 119;
	/** The key C in the ninth octave */
	public static final int KEY_C8 = 120;
	/** The key Db in the ninth octave */
	public static final int KEY_Db8 = 121;
	/** The key D in the ninth octave */
	public static final int KEY_D8 = 122;
	/** The key Eb in the ninth octave */
	public static final int KEY_Eb8 = 123;
	/** The key E in the ninth octave */
	public static final int KEY_E8 = 124;
	/** The key F in the ninth octave */
	public static final int KEY_F8 = 125;
	/** The key Gb in the ninth octave */
	public static final int KEY_Gb8 = 126;
	/** The key G in the ninth octave */
	public static final int KEY_G8 = 127;
	/** The key Ab in the ninth octave */
	public static final int KEY_Ab8 = 128;
	
	private MidiDeviceCommunicator communicator;

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
		
		this.communicator = new MidiDeviceCommunicator(device.getDeviceInfo().getName(), device.getTransmitters().get(0));
	}
	
	/**
	 * Adds a listener for events from the device
	 * @param listener The listener to add
	 */
	public void addDeviceListener(MidiDeviceListener listener){
		this.communicator.addDeviceListener(listener);
	}
	/**
	 * Removes a listener from the device
	 * @param listener The listener to remove
	 */
	public void removeDeviceListener(MidiDeviceListener listener){
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

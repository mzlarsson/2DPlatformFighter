package edu.chalmers.brawlbuddies.controller.input.midi;

import java.util.ArrayList;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;

/**
 * Class for finding MIDI devices and connect to their Transmissions.
 * @author Matz Larsson
 * @version 1.0
 *
 */

public class MidiDeviceFinder {
	
    private ArrayList<MidiDevice> devices = new ArrayList<MidiDevice>();

    /**
     * Creates a new DeviceFinder and reads in all data.
     */
	public MidiDeviceFinder() {
		init();
	}
	
	/**
	 * Iniatiates all the MIDI devices in the system and opens
	 * one transmission to those devices who accepts this.
	 */
	public void init(){
		MidiDevice device;
        Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (int i = 0; i < infos.length; i++) {
            try {
	            device = MidiSystem.getMidiDevice(infos[i]);
	
	            //Try to open a transmission
	            Transmitter trans = device.getTransmitter();
	            if(trans != null){
	            	device.open();
	            	devices.add(device);
	            }
	        } catch (MidiUnavailableException e) {}
        }
	}
	
	/**
	 * Searches for new MIDI devices and updates the list of
	 * all connected MIDI devices.
	 */
	public void updateDevices(){
		this.close();		
		init();
	}
	
	/**
	 * Retrieves the number of recognized devices
	 * @return The number of recognized devices
	 */
	public int getCount(){
		return this.devices.size();
	}
	
	/**
	 * Returns the MIDI device on the given slot index
	 * @param index Index of the wanted MIDI device
	 * @return The MIDI device in slot with given index
	 */
	public MidiDevice getDevice(int index){
		if(index>=0 && index<getCount()){
			return this.devices.get(index);
		}else{
			return null;
		}
	}

	/**
	 * Returns the transmitter of the MIDI device on the given slot index
	 * @param index Index to the the wanted MIDI device
	 * @return The transmitter of the MIDI device in slot with given index
	 */
	public Transmitter getTransmitter(int index){
		if(index>=0 && index<getCount()){
			return this.devices.get(index).getTransmitters().get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * Closes all current communications with devices and empties
	 * all data from this object.
	 */
	public void close(){
		for(MidiDevice device : this.devices){
			device.close();
		}
		this.devices.clear();
	}
}

package edu.chalmers.brawlbuddies.controller.input;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import edu.chalmers.brawlbuddies.controller.input.InputHandler;
import edu.chalmers.brawlbuddies.controller.input.midi.MidiDeviceFinder;
import edu.chalmers.brawlbuddies.controller.input.midi.MidiDeviceListener;
import edu.chalmers.brawlbuddies.controller.input.midi.SynthInput;
import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;

/**
 * Class for handling input from synths and MIDI-devices
 * @author Matz Larsson
 * @version 1.0
 *
 */

public class SynthInputHandler implements InputHandler, MidiDeviceListener {

	private SynthInput input;
	private Map<GameKey, Integer> keys;
	private Position mousePos = new Position(0, 0);
	private float mouseAngle = 0.0f;
	
	/**
	 * Creates a new SynthInputHandler which uses the first MIDI-device it can find
	 * @throws IllegalArgumentException If no synth was found at all
	 */
	public SynthInputHandler(){
		this(0);
	}
	
	/**
	 * Creates a new SynthInputHandler which uses the MIDI-device with ID as given
	 * @param deviceID The ID of the MIDI-device (the ID is based on the order of connected MIDI-devices)
	 * @throws IllegalArgumentException If the synth was not found
	 */
	public SynthInputHandler(int deviceID){
		MidiDeviceFinder df = new MidiDeviceFinder();
		if(deviceID>=0 && deviceID<df.getCount()){
			this.input = new SynthInput(df.getDevice(deviceID));
			this.input.addDeviceListener(this);
			this.keys = new TreeMap<GameKey, Integer>();
			resetDefault();
		}else{
			throw new IllegalArgumentException("No Synth found on given slot");
		}
	}
	
	/**
	 * Creates a SynthInputHandler with the use of a previous one. This
	 * constructor creates a clone of the given handler.
	 * @param handler The handler to use data from
	 */
	public SynthInputHandler(SynthInputHandler handler){
		GameKey[] hKeys = handler.getAllControlNames();
		for(int i = 0; i<hKeys.length; i++){
			this.setValue(hKeys[i], handler.getValue(hKeys[i]));
		}
		
		this.input = handler.input;
	}

	/**
	 * Resets all control settings to default
	 */
	public void resetDefault() {
		this.setValue(GameKey.UP, SynthInput.KEY_Db3);
		this.setValue(GameKey.DOWN, SynthInput.KEY_C3);
		this.setValue(GameKey.RIGHT, SynthInput.KEY_D3);
		this.setValue(GameKey.LEFT, SynthInput.KEY_B2);
		this.setValue(GameKey.JUMP, SynthInput.KEY_C4);
		this.setValue(GameKey.SKILL1, SynthInput.KEY_F4);
		this.setValue(GameKey.SKILL2, SynthInput.KEY_G4);
		this.setValue(GameKey.SKILL3, SynthInput.KEY_A4);
		this.setValue(GameKey.SKILL4, SynthInput.KEY_B4);
	}
	
	/**
	 * Retrieves the names of all the GameKeys that is set
	 * @return The names of the GameKeys that is set
	 */
	private GameKey[] getAllControlNames(){
		Object[] keys = this.keys.keySet().toArray();
		return Arrays.copyOf(keys, keys.length, GameKey[].class);
	}

	/**
	 * Gets the numeric key value (current settings) of a certain GameKey
	 * @param key The GameKey to get value for
	 * @return The numeric current key value for the GameKey
	 */
	public int getValue(GameKey key) {
		Integer keyValue = this.keys.get(key);
		if(keyValue != null){
			return (int)keyValue;
		}else{
			return -1;
		}
	}

	/**
	 * Sets a given GameKey to a new custom value
	 * NOTE: See public variables of the SynthInput class for predefined values
	 * @param key The GameKey to set
	 * @param value The value to use for the given GameKey
	 */
	public void setValue(GameKey key, int value) {
		this.keys.put(key, value);
	}
	
	/**
	 * Determines if this GameKey has been activated.
	 * E.g. keys are activated when they are pressed, not held down.
	 * @param key The GameKey to check
	 * @return <code>true</code> if the GameKey is activated, <code>false</code> otherwise
	 */
	public boolean isActivated(GameKey key){
		return this.input.isPressed(this.getValue(key));
	}

	/**
	 * Determines if the GameKey currently is active
	 * E.g. keys are active when they are held down
	 * @param key The GameKey to check
	 * @return <code>true</code> if the GameKey is active, <code>false</code> otherwise
	 */
	public boolean isActive(GameKey key) {
		return this.input.isHeldDown(this.getValue(key));
	}

	/**
	 * Calculates a Direction depending on the directional GameKeys (LEFT, RIGHT, UP, DOWN)
	 * @return A move Direction depending on current input state
	 */
	public Direction getDirection() {
		Direction dir = Direction.NONE;
		if (this.isActive(GameKey.UP)) {
			dir = dir.add(Direction.UP);
		}
		if (this.isActive(GameKey.DOWN)) {
			dir = dir.add(Direction.DOWN);
		}
		if (this.isActive(GameKey.RIGHT)) {
			dir = dir.add(Direction.RIGHT);
		}
		if (this.isActive(GameKey.LEFT)) {
			dir = dir.add(Direction.LEFT);
		}
		
		return dir;
	}

	/**
	 * Determines the position of the virtual mouse of this input
	 * @return The position of the virtual mouse
	 */
	public Position getMousePosition() {
		return new Position((float)Math.cos(Math.toRadians(this.mouseAngle))*100, (float)Math.sin(Math.toRadians(this.mouseAngle))*100);
	}
	
	/**
	 * Determines whether the method getMousePosition() returns a relative or absolute position.
	 * NOTE: This method will always return true since the synth does not have a mouse.
	 * @return <code>true</code> if the position is relative, <code>false</code> if it is absolute
	 */
	public boolean isMousePositionRelative() {
		return true;
	}
	
	/**
	 * Sets up the position of the virtual mouse depending on directional GameKeys
	 */
	private void setupMouse(){
		float mouseAngle = this.mouseAngle;
		if(isActive(GameKey.LEFT)){
			if(isActive(GameKey.DOWN)){
				mouseAngle = 135.0f;
			}else if(isActive(GameKey.UP)){
				mouseAngle = 225.0f;
			}else{
				mouseAngle = 180.0f;
			}
		}else if(isActive(GameKey.RIGHT)){
			if(isActive(GameKey.DOWN)){
				mouseAngle = 45.0f;
			}else if(isActive(GameKey.UP)){
				mouseAngle = 315.0f;
			}else{
				mouseAngle = 0.0f;
			}
		}else if(isActive(GameKey.UP)){
			mouseAngle = 270.0f;
		}else if(isActive(GameKey.DOWN)){
			mouseAngle = 90.0f;
		}else{
			if(mouseAngle>=90.0f && mouseAngle<=270.0f){
				mouseAngle = 180.0f;
			}else{
				mouseAngle = 0.0f;
			}
		}
		
		//Do not save value if ONLY up/down is pressed.
		if(mouseAngle != 90.0f && mouseAngle != 270.0f){
			this.mouseAngle = mouseAngle;
		}
		
		//Setup aim position
		this.mousePos.set((float)Math.cos(Math.toRadians(mouseAngle))*100, (float)Math.sin(Math.toRadians(mouseAngle))*100);
	}

	/**
	 * Determines if the given key is connected to a directional GameKey
	 * @param key The value to test
	 * @return <code>true</code> if the given key is connected to a directional GameKey, <code>false</code> otherwise
	 */
	public boolean isDirectionKey(int key){
		return key == getValue(GameKey.LEFT) || 
			   key == getValue(GameKey.RIGHT) || 
			   key == getValue(GameKey.UP) || 
			   key == getValue(GameKey.DOWN);
	}
	
	/**
	 * Triggered when a key is pressed
	 * @param key The key which is pressed
	 */
	public void keyPressed(int key){
		if(isDirectionKey(key)){
			setupMouse();
		}
	}

	/**
	 * Triggered when a key is released
	 * @param key The key which is pressed
	 */
	public void keyReleased(int key) {
		if(isDirectionKey(key)){
			setupMouse();
		}
	}

	/**
	 * Triggered when a key is held (10 ms intervals).
	 * NOTE: This method is currently not used.
	 * @param key The key which is pressed
	 */
	public void keyHeld(int key) {
	}
	
	/**
	 * Returns a formatted string describing this object
	 */
	public String toString(){
		String ans = "SynthInputHandler [";
		
		boolean firstDone = false;
		for(GameKey key : this.getAllControlNames()){
			ans += (firstDone?", ":"")+key+"="+this.getValue(key);
			firstDone = true;
		}
		
		ans += "]";
		return ans;
	}
}

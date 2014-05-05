package edu.chalmers.brawlbuddies.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import edu.chalmers.brawlbuddies.controller.midi.MidiDeviceFinder;
import edu.chalmers.brawlbuddies.controller.midi.MidiDeviceListener;
import edu.chalmers.brawlbuddies.controller.midi.SynthInput;
import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;

public class SynthInputHandler implements InputHandler, MidiDeviceListener {

	private SynthInput input;
	private Map<GameKey, Integer> keys;
	private Position mousePos = new Position(0, 0);
	private float mouseAngle = 0.0f;
	
	public SynthInputHandler(){
		this(0);
	}
	
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
	
	public SynthInputHandler(SynthInputHandler handler){
		GameKey[] hKeys = handler.getAllControlNames();
		for(int i = 0; i<hKeys.length; i++){
			this.setValue(hKeys[i], handler.getValue(hKeys[i]));
		}
		
		this.input = handler.input;
	}

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
	
	private GameKey[] getAllControlNames(){
		Object[] keys = this.keys.keySet().toArray();
		return Arrays.copyOf(keys, keys.length, GameKey[].class);
	}

	public int getValue(GameKey key) {
		Integer keyValue = this.keys.get(key);
		if(keyValue != null){
			return (int)keyValue;
		}else{
			return -1;
		}
	}

	public void setValue(GameKey key, int value) {
		this.keys.put(key, value);
	}
	
	public boolean isActivated(GameKey key){
		return this.input.isPressed(this.getValue(key));
	}

	public boolean isActive(GameKey key) {
		return this.input.isHeldDown(this.getValue(key));
	}

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

	public Position getMousePosition() {
		return new Position((float)Math.cos(Math.toRadians(this.mouseAngle))*100, (float)Math.sin(Math.toRadians(this.mouseAngle))*100);
	}
	
	public boolean isMousePositionRelative() {
		return true;
	}
	
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

	public boolean isDirectionKey(int key){
		return key == getValue(GameKey.LEFT) || 
			   key == getValue(GameKey.RIGHT) || 
			   key == getValue(GameKey.UP) || 
			   key == getValue(GameKey.DOWN);
	}
	
	public void keyPressed(int key){
		if(isDirectionKey(key)){
			setupMouse();
		}
	}

	public void keyReleased(int key) {
		if(isDirectionKey(key)){
			setupMouse();
		}
	}

	public void keyHeld(int key) {
	}
}

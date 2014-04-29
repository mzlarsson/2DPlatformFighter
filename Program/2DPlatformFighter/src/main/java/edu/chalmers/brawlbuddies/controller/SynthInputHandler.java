package edu.chalmers.brawlbuddies.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import edu.chalmers.brawlbuddies.controller.midi.MidiDeviceFinder;
import edu.chalmers.brawlbuddies.controller.midi.SynthInput;
import edu.chalmers.brawlbuddies.model.Direction;

public class SynthInputHandler implements InputHandler {

	private SynthInput input;
	private Map<GameKey, Integer> keys;
	
	public SynthInputHandler(){
		this(0);
	}
	
	public SynthInputHandler(int deviceID){
		MidiDeviceFinder df = new MidiDeviceFinder();
		if(deviceID>=0 && deviceID<df.getCount()){
			this.input = new SynthInput(df.getDevice(deviceID));
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

}

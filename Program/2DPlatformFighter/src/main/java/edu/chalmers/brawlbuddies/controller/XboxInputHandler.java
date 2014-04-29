package edu.chalmers.brawlbuddies.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import edu.chalmers.brawlbuddies.controller.xbox.XboxFinder;
import edu.chalmers.brawlbuddies.controller.xbox.XboxInput;
import edu.chalmers.brawlbuddies.model.Direction;

public class XboxInputHandler implements InputHandler {

	private XboxInput input;
	private Map<GameKey, Integer> keys;
	
	public XboxInputHandler() {
		this(0);
	}
	
	public XboxInputHandler(int deviceID){
		XboxFinder xf = new XboxFinder();
		if(deviceID>=0 && deviceID<xf.getCount()){
			this.input = new XboxInput(xf.getController(deviceID));
			this.keys = new TreeMap<GameKey, Integer>();
			resetDefault();
		}else{
			throw new IllegalArgumentException("No Xbox controller found on given slot");
		}
	}
	
	public XboxInputHandler(XboxInputHandler handler){
		GameKey[] hKeys = handler.getAllControlNames();
		for(int i = 0; i<hKeys.length; i++){
			this.setValue(hKeys[i], handler.getValue(hKeys[i]));
		}
		
		this.input = handler.input;
	}

	public void resetDefault() {
		this.setValue(GameKey.UP, XboxInput.AXIS_Y_NEG);
		this.setValue(GameKey.DOWN, XboxInput.AXIS_Y_POS);
		this.setValue(GameKey.RIGHT, XboxInput.AXIS_X_POS);
		this.setValue(GameKey.LEFT, XboxInput.AXIS_X_NEG);
		this.setValue(GameKey.JUMP, XboxInput.BUTTON_A);
		this.setValue(GameKey.SKILL1, XboxInput.BUTTON_RB);
		this.setValue(GameKey.SKILL2, XboxInput.BUTTON_Y);
		this.setValue(GameKey.SKILL3, XboxInput.BUTTON_LB);
		this.setValue(GameKey.SKILL4, XboxInput.BUTTON_RB);
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

	public boolean isActivated(GameKey key) {
		int index = this.getValue(key);
		if(this.input.isButton(index)){
			return this.input.isButtonPressed(index);
		}else{
			return this.input.axisIsActivated(index);
		}
	}

	public boolean isActive(GameKey key) {
		int index = this.getValue(key);
		if(this.input.isButton(index)){
			return this.input.isButtonHeld(index);
		}else{
			return this.input.axisIsActive(index);
		}
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

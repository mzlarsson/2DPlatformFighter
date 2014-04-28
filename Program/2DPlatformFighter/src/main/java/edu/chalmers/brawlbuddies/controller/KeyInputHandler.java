package edu.chalmers.brawlbuddies.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.Input;

import edu.chalmers.brawlbuddies.model.Direction;

/**
 * Class for handling input control settings.
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class KeyInputHandler implements InputHandler{
	
	private Map<GameKey, Integer> keys;

	public KeyInputHandler() {
		this.keys = new TreeMap<GameKey, Integer>();
		resetDefault();
	}
	
	public KeyInputHandler(KeyInputHandler handler){
		GameKey[] hKeys = handler.getAllControlNames();
		for(int i = 0; i<hKeys.length; i++){
			this.setValue(hKeys[i], handler.getValue(hKeys[i]));
		}
	}
	
	public void resetDefault(){
		this.setValue(GameKey.UP, Input.KEY_W);
		this.setValue(GameKey.DOWN, Input.KEY_S);
		this.setValue(GameKey.RIGHT, Input.KEY_D);
		this.setValue(GameKey.LEFT, Input.KEY_A);
		this.setValue(GameKey.JUMP, Input.KEY_SPACE);
		this.setValue(GameKey.SKILL1, Input.KEY_1);
		this.setValue(GameKey.SKILL2, Input.KEY_2);
		this.setValue(GameKey.SKILL3, Input.KEY_3);
		this.setValue(GameKey.SKILL4, Input.KEY_4);
	}
	
	private GameKey[] getAllControlNames(){
		Object[] keys = this.keys.keySet().toArray();
		return Arrays.copyOf(keys, keys.length, GameKey[].class);
	}
	
	public int getValue(GameKey key){
		return (int)this.keys.get(key);
	}
	
	public void setValue(GameKey key, int ch){
		this.keys.put(key, ch);
	}
	
	public boolean isActive(Input input, GameKey key){
		return input.isKeyDown(this.getValue(key));
	}
	
	public Direction getDirection(Input input){
		Direction dir = Direction.NONE;
		if (input.isKeyDown(this.getValue(GameKey.UP))) {
			dir = dir.add(Direction.UP);
		}
		if (input.isKeyDown(this.getValue(GameKey.DOWN))) {
			dir = dir.add(Direction.DOWN);
		}
		if (input.isKeyDown(this.getValue(GameKey.RIGHT))) {
			dir = dir.add(Direction.RIGHT);
		}
		if (input.isKeyDown(this.getValue(GameKey.LEFT))) {
			dir = dir.add(Direction.LEFT);
		}
		
		return dir;
	}
}

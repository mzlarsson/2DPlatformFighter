package edu.chalmers.brawlbuddies.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;

/**
 * Class for handling input control settings.
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class KeyInputHandler implements InputHandler, KeyListener{
	
	public static final int MOUSE_LEFT_BUTTON = 1000+Input.MOUSE_LEFT_BUTTON;
	public static final int MOUSE_RIGHT_BUTTON = 1000+Input.MOUSE_RIGHT_BUTTON;
	public static final int MOUSE_MIDDLE_BUTTON = 1000+Input.MOUSE_MIDDLE_BUTTON;
	
	private Map<GameKey, Integer> keys;
	private Input input;
	private Position mousePos = new Position(0, 0);
	private float mouseAngle = 0.0f;
	private boolean useMouse = true;
	
	public KeyInputHandler(){
		this(true);
	}
	
	public KeyInputHandler(boolean useMouse){
		this.keys = new TreeMap<GameKey, Integer>();
		this.useMouse = useMouse;
		this.setInput(null);
		resetDefault();
	}

	public KeyInputHandler(Input input){
		this(input, true);
	}
	
	public KeyInputHandler(Input input, boolean useMouse) {
		this.keys = new TreeMap<GameKey, Integer>();
		this.useMouse = useMouse;
		this.setInput(input);
		resetDefault();
	}
	
	public KeyInputHandler(KeyInputHandler handler){
		GameKey[] hKeys = handler.getAllControlNames();
		for(int i = 0; i<hKeys.length; i++){
			this.setValue(hKeys[i], handler.getValue(hKeys[i]));
		}
		
		this.setInput(input);
		this.useMouse = handler.useMouse;
	}
	
	public void resetDefault(){
		this.setValue(GameKey.UP, Input.KEY_W);
		this.setValue(GameKey.DOWN, Input.KEY_S);
		this.setValue(GameKey.RIGHT, Input.KEY_D);
		this.setValue(GameKey.LEFT, Input.KEY_A);
		this.setValue(GameKey.JUMP, Input.KEY_SPACE);
		this.setValue(GameKey.SKILL1, KeyInputHandler.MOUSE_LEFT_BUTTON);
		this.setValue(GameKey.SKILL2, KeyInputHandler.MOUSE_RIGHT_BUTTON);
		this.setValue(GameKey.SKILL3, Input.KEY_Q);
		this.setValue(GameKey.SKILL4, Input.KEY_E);
	}

	public Input getInput(){
		return this.input;
	}
	
	public void setInput(Input input){
		if(this.input != null){
			this.input.removeKeyListener(this);
		}
		this.input = input;
		if(this.input != null){
			this.input.addKeyListener(this);

			if(!this.useMouse){
				setupMouse();
			}
		}
	}
	
	private GameKey[] getAllControlNames(){
		Object[] keys = this.keys.keySet().toArray();
		return Arrays.copyOf(keys, keys.length, GameKey[].class);
	}
	
	public int getValue(GameKey key){
		Integer keyValue = this.keys.get(key);
		if(keyValue != null){
			return (int)keyValue;
		}else{
			return -1;
		}
	}
	
	public void setValue(GameKey key, int ch){
		this.keys.put(key, ch);
	}
	
	public void clearValue(GameKey key){
		this.keys.remove(key);
	}
	
	public boolean isActivated(GameKey key){
		if(this.isMouseButton(this.getValue(key))){
			return this.input.isMousePressed(this.getMouseIndex(this.getValue(key)));
		}else{
			return this.input.isKeyPressed(this.getValue(key));
		}
	}
	
	public boolean isActive(GameKey key){
		if(this.isMouseButton(this.getValue(key))){
			return this.input.isMouseButtonDown(this.getMouseIndex(this.getValue(key)));
		}else{
			return this.input.isKeyDown(this.getValue(key));
		}
	}
	
	public Direction getDirection(){
		Direction dir = Direction.NONE;
		if (this.input.isKeyDown(this.getValue(GameKey.UP))) {
			dir = dir.add(Direction.UP);
		}
		if (this.input.isKeyDown(this.getValue(GameKey.DOWN))) {
			dir = dir.add(Direction.DOWN);
		}
		if (this.input.isKeyDown(this.getValue(GameKey.RIGHT))) {
			dir = dir.add(Direction.RIGHT);
		}
		if (this.input.isKeyDown(this.getValue(GameKey.LEFT))) {
			dir = dir.add(Direction.LEFT);
		}
		
		return dir;
	}

	public Position getMousePosition() {
		if(!this.isMousePositionRelative()){
			return new Position(this.input.getMouseX(), this.input.getMouseY());
		}else{
			return this.mousePos;
		}
	}
	
	public boolean isMouseButton(int index){
		return index>=1000;
	}
	public int getMouseIndex(int index){
		return index-1000;
	}
	
	public boolean isMousePositionRelative(){
		return !this.useMouse;
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

	public void keyPressed(int key, char ch) {
		if(isMousePositionRelative() && isDirectionKey(key)){
			setupMouse();
		}
	}

	public void keyReleased(int key, char ch) {
		if(isMousePositionRelative() && isDirectionKey(key)){
			setupMouse();
		}
	}
	
	public void inputEnded() {}
	public void inputStarted() {}
	public boolean isAcceptingInput() {
		return true;
	}
}

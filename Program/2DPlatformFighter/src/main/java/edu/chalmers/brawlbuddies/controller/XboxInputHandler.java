package edu.chalmers.brawlbuddies.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.Timer;

import edu.chalmers.brawlbuddies.controller.xbox.XboxFinder;
import edu.chalmers.brawlbuddies.controller.xbox.XboxInput;
import edu.chalmers.brawlbuddies.controller.xbox.XboxListener;
import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Vector;

public class XboxInputHandler implements InputHandler, XboxListener {

	private XboxInput input;
	private Map<GameKey, Integer> keys;
	private Timer mouseTimer;
	private Position mousePos;
	
	public XboxInputHandler() {
		this(0);
	}
	
	public XboxInputHandler(int deviceID){
		XboxFinder xf = new XboxFinder();
		if(deviceID>=0 && deviceID<xf.getCount()){
			this.input = new XboxInput(xf.getController(deviceID));
			this.input.addXboxListener(this);
			this.keys = new TreeMap<GameKey, Integer>();
			resetDefault();
			initMouseTimer();
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
		this.input.addXboxListener(this);
		initMouseTimer();
	}

	public void resetDefault() {
		this.setValue(GameKey.UP, XboxInput.AXIS_Y_NEG);
		this.setValue(GameKey.DOWN, XboxInput.AXIS_Y_POS);
		this.setValue(GameKey.RIGHT, XboxInput.AXIS_X_POS);
		this.setValue(GameKey.LEFT, XboxInput.AXIS_X_NEG);
		this.setValue(GameKey.JUMP, XboxInput.BUTTON_LT);
		this.setValue(GameKey.SKILL1, XboxInput.BUTTON_RT);
		this.setValue(GameKey.SKILL2, XboxInput.BUTTON_RB);
		this.setValue(GameKey.SKILL3, XboxInput.BUTTON_LB);
		this.setValue(GameKey.SKILL4, XboxInput.BUTTON_LEFTSTICK);
	}
	
	public void initMouseTimer(){
		mousePos = new Position(0, 0);
		this.input.setDeadZone(XboxInput.AXIS_ROTATE_X, 0.0f);
		this.input.setMinumumAxisValue(XboxInput.AXIS_ROTATE_X, 0.3f);
		this.input.setDeadZone(XboxInput.AXIS_ROTATE_Y, 0.0f);
		this.input.setMinumumAxisValue(XboxInput.AXIS_ROTATE_Y, 0.3f);
		mouseTimer = new Timer(10, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				float x = input.getAxisValue(XboxInput.AXIS_ROTATE_X);
				float y = input.getAxisValue(XboxInput.AXIS_ROTATE_Y);
				mousePos = (Position)(new Position(x, y).getNormalized());
				mousePos = mousePos.scale(100.0f);
			}
		});
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
		if(XboxInput.isButton(index)){
			return this.input.isButtonPressed(index);
		}else{
			return this.input.axisIsActivated(index);
		}
	}

	public boolean isActive(GameKey key) {
		int index = this.getValue(key);
		if(XboxInput.isButton(index)){
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

	public Position getMousePosition() {
		return this.mousePos;
	}

	public boolean isMousePositionRelative() {
		return true;
	}

	
	public void buttonPressed(int buttonIndex) {
	}
	
	public void buttonReleased(int buttonIndex) {
	}

	public void axisChanged(int axisIndex) {
	}

	public void axisRestored(int axisIndex) {
		if((XboxInput.axisEquals(axisIndex, XboxInput.AXIS_ROTATE_X) && !input.axisIsActive(XboxInput.AXIS_ROTATE_Y)) ||
		   (XboxInput.axisEquals(axisIndex, XboxInput.AXIS_ROTATE_Y) && !input.axisIsActive(XboxInput.AXIS_ROTATE_X))){
			mouseTimer.stop();
			if(isActive(GameKey.LEFT)){
				this.mousePos.set(-100.0f, 0.0f);
			}else if(isActive(GameKey.RIGHT)){
				this.mousePos.set(100.0f, 0.0f);
			}else{
				this.mousePos.set((this.mousePos.getX()<0?-100.0f:100.0f), 0.0f);
			}
		}
	}

	public void axisUnrestored(int axisIndex) {
		if(XboxInput.axisEquals(axisIndex, XboxInput.AXIS_ROTATE_X) ||
		   XboxInput.axisEquals(axisIndex, XboxInput.AXIS_ROTATE_Y)){
			mouseTimer.start();
		}
	}

}

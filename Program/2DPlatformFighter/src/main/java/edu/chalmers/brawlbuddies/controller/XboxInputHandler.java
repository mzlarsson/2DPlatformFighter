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

/**
 * Class for handling input from XBOX 360 controllers
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class XboxInputHandler implements InputHandler, XboxListener {

	private XboxInput input;
	private Map<GameKey, Integer> keys;
	private Timer mouseTimer;
	private Position mousePos;
	
	/**
	 * Creates a new XboxInputHandler which uses the first XBOX 360 control it can find
	 * @throws IllegalArgumentException If no control was found at all
	 */
	public XboxInputHandler() {
		this(0);
	}

	/**
	 * Creates a new XboxInputHandler which uses the XBOX 360 control with ID as given
	 * @param deviceID The ID of the control (the ID is based on the order of connected controls)
	 * @throws IllegalArgumentException If the control was not found
	 */
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
	
	/**
	 * Creates a XboxInputHandler with the use of a previous one. This
	 * constructor creates a clone of the given handler.
	 * @param handler The handler to use data from
	 */
	public XboxInputHandler(XboxInputHandler handler){
		GameKey[] hKeys = handler.getAllControlNames();
		for(int i = 0; i<hKeys.length; i++){
			this.setValue(hKeys[i], handler.getValue(hKeys[i]));
		}
		
		this.input = handler.input;
		this.input.addXboxListener(this);
		initMouseTimer();
	}

	/**
	 * Resets all GameKeys to default values
	 */
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
	
	/**
	 * Inits the timer which controls the logic for the virtual mouse
	 */
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
	
	/**
	 * Retrieve the names of the set GameKeys for this handler
	 * @return The names of the set GameKeys for this handler
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
	 * NOTE: See public variables of the XboxInput class for predefined values
	 * @param key The GameKey to set
	 * @param value The value to use for the given GameKey
	 */
	public void setValue(GameKey key, int value) {
		this.keys.put(key, value);
	}
	/**
	 * Determines if this GameKey has been activated.
	 * E.g. buttons are activated when they are pressed, not held down.
	 * E.g. an axis is activated when it is moved from the standard state
	 * @param key The GameKey to check
	 * @return <code>true</code> if the GameKey is activated, <code>false</code> otherwise
	 */
	public boolean isActivated(GameKey key) {
		int index = this.getValue(key);
		if(XboxInput.isButton(index)){
			return this.input.isButtonPressed(index);
		}else{
			return this.input.axisIsActivated(index);
		}
	}

	/**
	 * Determines if the GameKey currently is active
	 * E.g. buttons are active when they are held down
	 * E.g. an axis is active when it is not in standard state
	 * @param key The GameKey to check
	 * @return <code>true</code> if the GameKey is active, <code>false</code> otherwise
	 */
	public boolean isActive(GameKey key) {
		int index = this.getValue(key);
		if(XboxInput.isButton(index)){
			return this.input.isButtonHeld(index);
		}else{
			return this.input.axisIsActive(index);
		}
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
		return this.mousePos;
	}

	/**
	 * Determines whether the method getMousePosition() returns a relative or absolute position.
	 * NOTE: This method will always return true since the controller mouse is abstract
	 * @return <code>true</code> if the position is relative, <code>false</code> if it is absolute
	 */
	public boolean isMousePositionRelative() {
		return true;
	}

	/**
	 * Triggered when a button has been pressed
	 * NOTE: Currently not used.
	 * @param buttonIndex The index of the button that is pressed
	 */
	public void buttonPressed(int buttonIndex) {
	}

	/**
	 * Triggered when a button has been released
	 * NOTE: Currently not used.
	 * @param buttonIndex The index of the button that is released
	 */
	public void buttonReleased(int buttonIndex) {
	}

	/**
	 * Triggered when an axis has changed its value
	 * NOTE: Currently not used.
	 * @param axisIndex The real index of the axis that is released.
	 * NOTE: Use XboxInput.axisEquals(int, int) to check real axis indexes against virtual ones
	 */
	public void axisChanged(int axisIndex) {
	}

	/**
	 * Triggered when axis is restored (back to standard state)
	 * @param axisIndex The real index of the axis that is restored.
	 * NOTE: Use XboxInput.axisEquals(int, int) to check real axis indexes against virtual ones
	 */
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

	/**
	 * Triggered when axis is unrestored (pulled away from standard state)
	 * @param axisIndex The real index of the axis that is unrestored.
	 * NOTE: Use XboxInput.axisEquals(int, int) to check real axis indexes against virtual ones
	 */
	public void axisUnrestored(int axisIndex) {
		if(XboxInput.axisEquals(axisIndex, XboxInput.AXIS_ROTATE_X) ||
		   XboxInput.axisEquals(axisIndex, XboxInput.AXIS_ROTATE_Y)){
			mouseTimer.start();
		}
	}

}

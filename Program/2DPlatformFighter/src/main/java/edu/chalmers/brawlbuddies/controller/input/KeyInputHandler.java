package edu.chalmers.brawlbuddies.controller.input;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.view.SideScroller;

/**
 * Class for handling input control settings from keyboard and mouse.
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class KeyInputHandler implements InputHandler, KeyListener{
	
	/** ID of the left mouse button */
	public static final int MOUSE_LEFT_BUTTON = 1000+Input.MOUSE_LEFT_BUTTON;
	/** ID of the right mouse button */
	public static final int MOUSE_RIGHT_BUTTON = 1000+Input.MOUSE_RIGHT_BUTTON;
	/** ID of the middle mouse button */
	public static final int MOUSE_MIDDLE_BUTTON = 1000+Input.MOUSE_MIDDLE_BUTTON;
	
	private Map<GameKey, Integer> keys;
	private Input input;
	private Position mousePos = new Position(0, 0);
	private float mouseAngle = 0.0f;
	private boolean useMouse = true;
	
	private SideScroller scroller;
	
	/**
	 * Creates a new KeyInputHandler which uses the mouse to aim
	 * NOTE: No input is connected to this handler while using this constructor.
	 * @see #setInput(Input)
	 */
	public KeyInputHandler(){
		this(true);
	}
	
	/**
	 * Creates a new KeyInputHandler which may use the mouse to aim
	 * @param useMouse If the mouse should be used for aiming
	 * NOTE: No input is connected to this handler while using this constructor.
	 * @see #setInput(Input)
	 */
	public KeyInputHandler(boolean useMouse){
		this.keys = new TreeMap<GameKey, Integer>();
		this.useMouse = useMouse;
		this.setInput(null);
		resetDefault();
	}

	/**
	 * Creates a new KeyInputHandler which uses the mouse for aiming
	 * @param input The input object to fetch data from
	 */
	public KeyInputHandler(Input input){
		this(input, true);
	}
	
	/**
	 * Creates a new KeyInputHandler which may use the mouse for aiming
	 * @param input The input object to fetch data from
	 * @param useMouse If the mouse should be used for aiming
	 */
	public KeyInputHandler(Input input, boolean useMouse) {
		this.keys = new TreeMap<GameKey, Integer>();
		this.useMouse = useMouse;
		this.setInput(input);
		resetDefault();
	}
	
	/**
	 * Constructs a new KeyInputHandler in the same way as the given handler
	 * @param handler The KeyInputHandler to fetch data from
	 */
	public KeyInputHandler(KeyInputHandler handler){
		GameKey[] hKeys = handler.getAllControlNames();
		for(int i = 0; i<hKeys.length; i++){
			this.setValue(hKeys[i], handler.getValue(hKeys[i]));
		}
		
		this.setInput(input);
		this.useMouse = handler.useMouse;
	}
	
	/**
	 * Resets all input settings to default
	 */
	public void resetDefault(){
		this.setValue(GameKey.UP, Input.KEY_W);
		this.setValue(GameKey.DOWN, Input.KEY_S);
		this.setValue(GameKey.RIGHT, Input.KEY_D);
		this.setValue(GameKey.LEFT, Input.KEY_A);
		this.setValue(GameKey.JUMP, Input.KEY_SPACE);
		if(!this.isUsingMouse()){
			this.setValue(GameKey.SKILL1, Input.KEY_T);
			this.setValue(GameKey.SKILL2, Input.KEY_Y);
			this.setValue(GameKey.SKILL3, Input.KEY_U);
			this.setValue(GameKey.SKILL4, Input.KEY_I);
		}else{
			this.setValue(GameKey.SKILL1, KeyInputHandler.MOUSE_LEFT_BUTTON);
			this.setValue(GameKey.SKILL2, KeyInputHandler.MOUSE_RIGHT_BUTTON);
			this.setValue(GameKey.SKILL3, Input.KEY_Q);
			this.setValue(GameKey.SKILL4, Input.KEY_E);
		}
	}

	/**
	 * Returns the input this object fetches data from
	 * @return The input this object fetches data from
	 */
	public Input getInput(){
		return this.input;
	}
	
	/**
	 * Removes the previous input and sets the given input as the new one
	 * @param input The input object to use
	 */
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
	
	/**
	 * Sets the scroller used on the screen
	 * @param scroller The scroller used
	 */
	public void setScroller(SideScroller scroller){
		this.scroller = scroller;
	}
	
	/**
	 * Set if this handler should consider the mouse as aiming device
	 * @param useMouse <code>true</code> if the mouse should be used for aiming, <code>false</code> otherwise
	 */
	public void useMouse(boolean useMouse){
		this.useMouse = useMouse;
	}
	
	/**
	 * Returns the names of the GameKeys that is set in this object
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
	public int getValue(GameKey key){
		Integer keyValue = this.keys.get(key);
		if(keyValue != null){
			return (int)keyValue;
		}else{
			return -1;
		}
	}
	
	/**
	 * Sets a given GameKey to a new custom value
	 * NOTE: For setting mouse controls, see public variables of this class
	 * NOTE: For setting key controls, see public variables of the Input class
	 * @param key The GameKey to set
	 * @param value The value to use for the given GameKey
	 */
	public void setValue(GameKey key, int value){
		this.keys.put(key, value);
	}
	
	/**
	 * Determines if this GameKey has been activated.
	 * E.g. keys are activated when they are pressed, not held down.
	 * @param key The GameKey to check
	 * @return <code>true</code> if the GameKey is activated, <code>false</code> otherwise
	 */
	public boolean isActivated(GameKey key){
		if(this.isMouseButton(this.getValue(key))){
			return this.input.isMousePressed(this.getMouseIndex(this.getValue(key)));
		}else{
			return this.input.isKeyPressed(this.getValue(key));
		}
	}

	/**
	 * Determines if the GameKey currently is active
	 * E.g. keys are active when they are held down
	 * @param key The GameKey to check
	 * @return <code>true</code> if the GameKey is active, <code>false</code> otherwise
	 */
	public boolean isActive(GameKey key){
		if(this.isMouseButton(this.getValue(key))){
			return this.input.isMouseButtonDown(this.getMouseIndex(this.getValue(key)));
		}else{
			return this.input.isKeyDown(this.getValue(key));
		}
	}
	
	/**
	 * Calculates a Direction depending on the directional GameKeys (LEFT, RIGHT, UP, DOWN)
	 * @return A move Direction depending on current input state
	 */
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

	/**
	 * Determines the position of the virtual mouse of this input
	 * @return The position of the virtual mouse
	 */
	public Position getMousePosition() {
		if(!this.isMousePositionRelative()){
			if(scroller == null){
				return new Position(this.input.getMouseX(), this.input.getMouseY());
			}else{
				int x = (int)(-scroller.getX()+this.input.getMouseX()/scroller.getScale());
				int y = (int)(-scroller.getY()+this.input.getMouseY()/scroller.getScale());
				return new Position(x, y);
			}
		}else{
			return this.mousePos;
		}
	}
	
	/**
	 * Determines if the given index is connected to a mouse button
	 * @param index The index to check
	 * @return <code>true</code> if the index is connected to a mouse button, <code>false</code> otherwise
	 */
	public boolean isMouseButton(int index){
		return index>=1000;
	}
	
	/**
	 * Converts an KeyInputHandler mouse index to a Slick mouse index
	 * @param index The KeyInputHandler index as given 
	 * @return The Slick mouse index
	 */
	private int getMouseIndex(int index){
		return index-1000;
	}
	
	/**
	 * Determines whether this handler uses the mouse
	 * @return <code>true</code> if this handler uses the mouse, <code>false</code> otherwise
	 */
	public boolean isUsingMouse(){
		return this.useMouse;
	}
	
	/**
	 * Determines whether the method getMousePosition() returns a relative or absolute position
	 * @return <code>true</code> if the position is relative, <code>false</code> if it is absolute
	 */
	public boolean isMousePositionRelative(){
		return !this.isUsingMouse();
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
	 * @param ch The char the pressed key represents
	 */
	public void keyPressed(int key, char ch) {
		if(isMousePositionRelative() && isDirectionKey(key)){
			setupMouse();
		}
	}

	/**
	 * Triggered when a key is released
	 * @param key The key which is pressed
	 * @param ch The char the pressed key represents
	 */
	public void keyReleased(int key, char ch) {
		if(isMousePositionRelative() && isDirectionKey(key)){
			setupMouse();
		}
	}
	
	/**
	 * Triggered when the input object has been stopped.
	 * NOTE: Not used.
	 */
	public void inputEnded() {}
	
	/**
	 * Triggered when the input object has been started
	 * NOTE: Not used.
	 */
	public void inputStarted() {}
	
	/**
	 * Determines whether this handler accepts inputs.
	 * @return <code>true</code> if the handler accepts inputs, <code>false</code> otherwise
	 */
	public boolean isAcceptingInput() {
		return true;
	}
	
	/**
	 * Returns a formatted string describing this object
	 */
	public String toString(){
		String ans = "KeyInputHandler [";
		
		boolean firstDone = false;
		for(GameKey key : this.getAllControlNames()){
			ans += (firstDone?", ":"")+key+"="+this.getValue(key);
			firstDone = true;
		}
		
		ans += "]";
		return ans;
	}
}

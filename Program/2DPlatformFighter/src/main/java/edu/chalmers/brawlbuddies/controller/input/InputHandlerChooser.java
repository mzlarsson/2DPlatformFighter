package edu.chalmers.brawlbuddies.controller.input;

import java.util.LinkedHashMap;
import java.util.Map;

import org.newdawn.slick.Input;

import edu.chalmers.brawlbuddies.controller.input.midi.MidiDeviceFinder;
import edu.chalmers.brawlbuddies.controller.input.xbox.XboxFinder;

/**
 * Class for handling the choice of control inputs.
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class InputHandlerChooser {
	
	private static InputHandlerChooser instance;
	
	private XboxFinder xboxFinder;
	private MidiDeviceFinder midiFinder;
	private Map<String, Integer> controllers;

	/**
	 * Creates a new chooser and fetches data from all connected devices
	 */
	private InputHandlerChooser() {
		controllers = new LinkedHashMap<String, Integer>();
		updateHandlers();
	}
	
	/**
	 * Returns an instance of this chooser
	 * @return An instance of this class
	 */
	public static InputHandlerChooser getInstance(){
		if(instance == null){
			instance = new InputHandlerChooser();
		}
		
		return instance;
	}
	
	/**
	 * Updates the list of connected handlers
	 */
	public void updateHandlers(){
		xboxFinder = new XboxFinder();
		midiFinder = new MidiDeviceFinder();
		
		this.controllers.clear();
		this.controllers.put("KeyboardWithMouse", 1);
		this.controllers.put("KeyboardOnly", 2);
		this.controllers.put("XboxControl", xboxFinder.getCount());
		this.controllers.put("MidiDevice", midiFinder.getCount());
	}
	
	/**
	 * Returns the number of external controllers
	 * @return The number of external controllers
	 */
	public int countExternalControllers(){
		return xboxFinder.getCount() + midiFinder.getCount();
	}
	
	/**
	 * Returns the found input handlers and how many there are of each
	 * @return The amount of available input handlers of each type
	 */
	public Map<String, Integer> getCountByType(){
		return this.controllers;
	}

	/**
	 * Retrieves an input handler from given parameters
	 * @param index The order nbr of the controller have if all controllers were to be listed
	 * @param useAsSecondPlayer If the player is used as second player. This affects keyboard settings
	 * @return An instance of the chosen input handler
	 */
	public InputHandler getInputHandler(int index, boolean useAsSecondPlayer){
		int num = 0, start = 0;
		for(String name : this.controllers.keySet()){
			for(int i = 0; i<this.controllers.get(name); i++){
				if(num == index){
					if(name.equals("KeyboardWithMouse")){
						return new KeyInputHandler();
					}else if(name.equals("KeyboardOnly")){
						KeyInputHandler handler = new KeyInputHandler(false);
						if(useAsSecondPlayer){
							setAsPlayer2(handler);
						}
						return handler;
					}else if(name.equals("XboxControl")){
						return new XboxInputHandler(num-start);
					}else if(name.equals("MidiDevice")){
						return new SynthInputHandler(num-start);
					}else{
						System.out.println("Input type not recognized");
					}
				}
				num++;
			}
			
			start += this.controllers.get(name);
		}
		
		return null;
	}
	
	/**
	 * Sets the keys of the given player to match a player 2 default setting
	 * @param handler The handler to set as player 2
	 */
	public void setAsPlayer2(KeyInputHandler handler){
		if(!handler.isUsingMouse()){
			handler.setValue(GameKey.DOWN, Input.KEY_DOWN);
			handler.setValue(GameKey.UP, Input.KEY_UP);
			handler.setValue(GameKey.LEFT, Input.KEY_LEFT);
			handler.setValue(GameKey.RIGHT, Input.KEY_RIGHT);
			handler.setValue(GameKey.JUMP, Input.KEY_NUMPAD0);
			handler.setValue(GameKey.SKILL1, Input.KEY_NUMPAD7);
			handler.setValue(GameKey.SKILL2, Input.KEY_NUMPAD8);
			handler.setValue(GameKey.SKILL3, Input.KEY_NUMPAD9);
			handler.setValue(GameKey.SKILL4, Input.KEY_ADD);
		}else{
			handler.setValue(GameKey.DOWN, Input.KEY_NUMPAD5);
			handler.setValue(GameKey.UP, Input.KEY_NUMPAD8);
			handler.setValue(GameKey.LEFT, Input.KEY_NUMPAD4);
			handler.setValue(GameKey.RIGHT, Input.KEY_NUMPAD6);
			handler.setValue(GameKey.JUMP, Input.KEY_NUMPADENTER);
			handler.setValue(GameKey.SKILL1, KeyInputHandler.MOUSE_LEFT_BUTTON);
			handler.setValue(GameKey.SKILL2, KeyInputHandler.MOUSE_RIGHT_BUTTON);
			handler.setValue(GameKey.SKILL3, Input.KEY_NUMPAD7);
			handler.setValue(GameKey.SKILL4, Input.KEY_NUMPAD9);
		}
	}
}

package edu.chalmers.brawlbuddies.controller.xbox;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

/**
 * Class for handling connections towards XBOX 360 controllers.
 * @author Matz Larsson
 * @version 0.1
 *
 */

public class XboxFinder {
	
	private List<Controller> controllers = new ArrayList<Controller>();

	/**
	 * Initiates a new Xbox Finder and gets the current connections of
	 * connected XBOX 360 controls.
	 */
	public XboxFinder() {
		init();
	}
	
	/**
	 * Detects all connected XBOX 360 devices and initiates
	 * a connection with them
	 */
	public void init(){
		try {
			Controllers.create();
		} catch (LWJGLException e) {}
		Controllers.poll();
		
		for(int i = 0; i<Controllers.getControllerCount(); i++){
			Controller controller = Controllers.getController(i);
			if(controller.getName().contains("XBOX 360")){
				this.controllers.add(controller);
			}
		}
	}

	/**
	 * Refreshes the list of the connected devices
	 */
	public void updateDevices(){
		this.close();		
		init();
	}
	
	/**
	 * Retrieves the number of XBOX 360 controllers found
	 * @return The number of controllers found
	 */
	public int getCount(){
		return this.controllers.size();
	}
	
	/**
	 * Returns the controller with the given index
	 * @param index The index of the controller, starting with zero
	 * @return The controller who correpsonds to the given index
	 */
	public Controller getController(int index){
		return this.controllers.get(index);
	}
	
	/**
	 * Closes all current communications with devices and empties
	 * all data from this object.
	 */
	public void close(){
		Controllers.destroy();
		this.controllers.clear();
	}

}

package edu.chalmers.brawlbuddies.controller.xbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

public class XboxCommunicator {
	
	private Controller controller;
	private boolean[] buttonPressed;
	private boolean[] buttonHeld;
	private float[] axis;
	private boolean[] axisChanged;
	private Timer updateTimer;
	
	private List<XboxListener> listeners;

	public XboxCommunicator(Controller controller) {
		this(controller, 20);
	}
	
	public XboxCommunicator(Controller controller, int updateInterval){
		this(controller, updateInterval, 0.3f);
	}
	
	public XboxCommunicator(Controller controller, int updateInterval, float deadZone){
		this.controller = controller;
		this.buttonPressed = new boolean[controller.getButtonCount()+4];
		this.buttonHeld = new boolean[controller.getButtonCount()+4];
		this.axis = new float[controller.getAxisCount()];
		this.axisChanged = new boolean[controller.getAxisCount()];
		for(int i = 0; i<this.controller.getAxisCount(); i++){
			this.controller.setDeadZone(i, deadZone);
		}
		
		listeners = new ArrayList<XboxListener>();
		
		updateTimer = new Timer(updateInterval, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				updateData();
			}
		});
		updateTimer.start();
	}

	/**
	 * Adds a listener to retrieve updates about pressed/released/changed controls
	 * @param listener The listener to add
	 */
	public void addXboxListener(XboxListener listener){
		if(listener != null){
			this.listeners.add(listener);
		}
	}
	
	/**
	 * Removes a listener which no longer gets updates about pressed/released/changed controls
	 * @param listener The listener to remove
	 */
	public void removeXboxListener(XboxListener listener){
		if(listener != null){
			this.listeners.remove(listener);
		}
	}
	
	/**
	 * Returns the name of the communication (name of controller)
	 * @return The name of the communication
	 */
	public String getName(){
		return this.controller.getName();
	}

	/**
	 * Retrieves the current value of the axis with given index
	 * @param axisIndex The index of the axis
	 * @return The value of the axis
	 * @throws IllegalArgumentException If axis don't exist
	 */
	public float getAxisValue(int axisIndex){
		if(axisIndex>=0 && axisIndex<this.axis.length){
			return this.axis[axisIndex];
		}else{
			throw new IllegalArgumentException("Axis does not exist ("+axisIndex+")");
		}
	}
	
	/**
	 * Returns whether this axis is 'pressed' (just activated)
	 * @param axisIndex The index of the axis
	 * @return <code>true</code> if the axis is 'pressed', <code>false</code> otherwise
	 */
	public boolean hasAxisChanged(int axisIndex){
		if(axisIndex>=0 && axisIndex<this.axisChanged.length){
			boolean changed = this.axisChanged[axisIndex];
			this.axisChanged[axisIndex] = false;
			return changed;
		}else{
			throw new IllegalArgumentException("Button does not exist");
		}
	}
	
	/**
	 * Returns whether this button is pressed
	 * @param buttonIndex The index of the button
	 * @return <code>true</code> if the button is pressed, <code>false</code> otherwise
	 * @see isButtonHeld(int buttonIndex)
	 */
	public boolean isButtonPressed(int buttonIndex){
		if(buttonIndex>=0 && buttonIndex<this.buttonPressed.length){
			boolean pressed = this.buttonPressed[buttonIndex];
			this.buttonPressed[buttonIndex] = false;
			return pressed;
		}else{
			throw new IllegalArgumentException("Button does not exist");
		}
	}

	/**
	 * Returns whether this button is held down
	 * @param buttonIndex The index of the button
	 * @return <code>true</code> if the button is held down, <code>false</code> otherwise
	 * @see isButtonPressed(int buttonIndex)
	 */
	public boolean isButtonHeld(int buttonIndex){
		if(buttonIndex>=0 && buttonIndex<this.buttonHeld.length){
			return this.buttonHeld[buttonIndex];
		}else{
			throw new IllegalArgumentException("Button does not exist");
		}
	}
	
	/**
	 * Sets the interval time the updater is using
	 * @param updateInterval Update interval time (in milliseconds)
	 */
	public void setUpdateInterval(int updateInterval){
		this.updateTimer.setDelay(updateInterval);
	}
	
	/**
	 * Stops listening for any new data
	 */
	public void close() {
		System.out.println("Closing XboxCommunicator");
		updateTimer.stop();
	}
	
	/**
	 * Creates a representative String for this object
	 * @return A representative String containing information about this object.
	 */
	public String toString(){
		return "-- XboxCommunicator: "+this.getName()+" ("+(this.updateTimer.isRunning()?"on":"off")+") --";
	}
	
	

	/**
	 * Notifies all listeners about an axis change
	 * @param axisIndex The index of the axis which has been changed
	 */
	private void notifyAll(int axisIndex){
		for(XboxListener listener : this.listeners){
			listener.axisChanged(axisIndex);
		}
	}
	
	/**
	 * Notifies all listeners about an axis restore/unrestore
	 * @param axisIndex The index of the axis which has been restored/unrestored
	 */
	private void axisNotifyAll(int axisIndex, boolean restored){
		for(XboxListener listener : this.listeners){
			if(restored){
				listener.axisRestored(axisIndex);
			}else{
				listener.axisUnrestored(axisIndex);
			}
		}
	}

	/**
	 * Notifies all listeners about a button change (press/release)
	 * @param buttonIndex The index of the button which has been changed
	 * @param pressed <code>true</code> if button is pressed, <code>false</code> if released
	 */
	private void buttonNotifyAll(int buttonIndex, boolean pressed){
		for(XboxListener listener : this.listeners){
			if(pressed){
				listener.buttonPressed(buttonIndex);
			}else{
				listener.buttonReleased(buttonIndex);
			}
		}
	}
	
	/**
	 * Updates all data in this class
	 */
	private void updateData(){
		Controllers.poll();
		updateButtons();
		updateArrowButtons();
		updateAxis();
	}
	
	/**
	 * Updates the status of all buttons
	 */
	private void updateButtons(){
		boolean currentState = false, prevState = false;
		for(int i = 0; i<this.controller.getButtonCount(); i++){
			currentState = this.controller.isButtonPressed(i);
			prevState = this.buttonHeld[i];
			
			//Update only if not set to true, since the user may not fetch data
			//with the same interval as this update runs
			if(!this.buttonPressed[i]){
				this.buttonPressed[i] = !prevState && currentState;
			}
			this.buttonHeld[i] = currentState;
			
			//Has anything changed?
			if(prevState != currentState){
				//Notify listeners
				this.buttonNotifyAll(i, currentState);
			}
		}
	}
	
	/**
	 * Updates the arrow buttons
	 */
	private void updateArrowButtons(){
		float px = controller.getPovX(), py = controller.getPovY();
		int numButtons = controller.getButtonCount();
		//RIGHT
		if((px != 1 && buttonHeld[numButtons]) || (px == 1 && !buttonHeld[numButtons])){
			this.buttonPressed[numButtons] = px==1;
			this.buttonNotifyAll(numButtons, px==1);
		}
		//DOWN
		if((py != 1 && buttonHeld[numButtons+1]) || (py == 1 && !buttonHeld[numButtons+1])){
			this.buttonPressed[numButtons+1] = py==1;
			this.buttonNotifyAll(numButtons+1, py==1);
		}
		//LEFT
		if((px != -1 && buttonHeld[numButtons+2]) || (px == -1 && !buttonHeld[numButtons+2])){
			this.buttonPressed[numButtons+2] = px==-1;
			this.buttonNotifyAll(numButtons+2, px==-1);
		}
		//UP
		if((py != -1 && buttonHeld[numButtons+3]) || (py == -1 && !buttonHeld[numButtons+3])){
			this.buttonPressed[numButtons+3] = py==-1;
			this.buttonNotifyAll(numButtons+3, py==-1);
		}

		this.buttonHeld[numButtons] = px==1;
		this.buttonHeld[numButtons+1] = py==1;
		this.buttonHeld[numButtons+2] = px==-1;
		this.buttonHeld[numButtons+3] = py==-1;
	}
	
	/**
	 * Updates the state of all the axis
	 */
	private void updateAxis(){
		for(int i = 0; i<this.axis.length; i++){
			if(this.axis[i] != controller.getAxisValue(i)){
				this.notifyAll(i);
				
				if(this.axis[i] == 0){
					this.axisChanged[i] = true;
					this.axisNotifyAll(i, false);
				}else if(controller.getAxisValue(i) == 0){
					this.axisNotifyAll(i, true);
				}
			}
			
			this.axis[i] = controller.getAxisValue(i);
		}
	}
	
}

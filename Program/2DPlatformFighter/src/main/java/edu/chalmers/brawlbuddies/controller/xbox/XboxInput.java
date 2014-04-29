package edu.chalmers.brawlbuddies.controller.xbox;

import org.lwjgl.input.Controller;

public class XboxInput {

	public static final int BUTTON_A = 0;
	public static final int BUTTON_X = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_Y = 3;
	public static final int BUTTON_LB = 4;
	public static final int BUTTON_RB = 5;
	public static final int BUTTON_BACK = 6;
	public static final int BUTTON_START = 7;
	public static final int BUTTON_LEFTSTICK = 8;
	public static final int BUTTON_RIGHTSTICK = 9;
	public static final int BUTTON_DPAD_RIGHT = 10;
	public static final int BUTTON_DPAD_DOWN = 11;
	public static final int BUTTON_DPAD_LEFT = 12;
	public static final int BUTTON_DPAD_UP = 13;

	public static final int AXIS_Y_POS = 14;
	public static final int AXIS_Y_NEG = 15;
	public static final int AXIS_X_POS = 16;
	public static final int AXIS_X_NEG = 17;
	public static final int AXIS_ROTATE_Y_POS = 18;
	public static final int AXIS_ROTATE_Y_NEG = 19;
	public static final int AXIS_ROTATE_X_POS = 20;
	public static final int AXIS_ROTATE_X_NEG = 21;
	public static final int AXIS_Z_POS = 22;
	public static final int AXIS_Z_NEG = 23;
	
	private static final int nbrOfButtons = 14;
	
	private XboxCommunicator communicator;
	
	public XboxInput(Controller controller){
		this(controller, 0.7f);
	}

	public XboxInput(Controller controller, float deadZone) {
		this.communicator = new XboxCommunicator(controller, 20, deadZone);
	}

	/**
	 * Adds a listener to retrieve updates about pressed/released/changed controls
	 * @param listener The listener to add
	 */
	public void addXboxListener(XboxListener listener){
		this.communicator.addXboxListener(listener);
	}
	
	/**
	 * Removes a listener which no longer gets updates about pressed/released/changed controls
	 * @param listener The listener to remove
	 */
	public void removeXboxListener(XboxListener listener){
		this.communicator.addXboxListener(listener);
	}
	
	public boolean isButtonPressed(int buttonIndex){
		return this.communicator.isButtonPressed(buttonIndex);
	}
	
	public boolean isButtonHeld(int buttonIndex){
		return this.communicator.isButtonHeld(buttonIndex);
	}
	
	public boolean axisIsActivated(int index){
		return axisIsActive(index) && this.communicator.hasAxisChanged(buttonToAxis(index));
	}
	
	public boolean axisIsActive(int index){
		return axisIsMoving(index) && (index%2==0?axisIsPositive(index):!axisIsPositive(index));
	}
	
	public boolean axisIsMoving(int index){
		return getAxisValue(index) != 0;
	}
	
	public boolean axisIsPositive(int index){
		return getAxisValue(index)>0;
	}
	
	public float getAxisValue(int index){
		int axisIndex = buttonToAxis(index);
		return this.communicator.getAxisValue(axisIndex);
	}
	
	private int buttonToAxis(int index){
		return (index-nbrOfButtons)/2;
	}
	
	public boolean isButton(int index){
		return index<nbrOfButtons && index>=0;
	}
	
	public boolean isAxis(int index){
		return index>=nbrOfButtons;
	}
	
	public void close(){
		this.communicator.close();
	}

}

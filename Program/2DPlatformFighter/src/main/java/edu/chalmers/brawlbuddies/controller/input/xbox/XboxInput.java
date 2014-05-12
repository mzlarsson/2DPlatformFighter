package edu.chalmers.brawlbuddies.controller.input.xbox;

import org.lwjgl.input.Controller;

/**
 * Class for handling data in a more general way than the XboxCommunicator class.
 * This should be used as an adapter between some logics and the entire
 * edu.chalmers.brawlbuddies.controller.xbox package.
 * @author Matz
 *
 */

public class XboxInput {

	/** The A-button on a XBOX 360 control */
	public static final int BUTTON_A = 0;
	/** The X-button on a XBOX 360 control */
	public static final int BUTTON_X = 1;
	/** The B-button on a XBOX 360 control */
	public static final int BUTTON_B = 2;
	/** The Y-button on a XBOX 360 control */
	public static final int BUTTON_Y = 3;
	/** The LB-button on a XBOX 360 control */
	public static final int BUTTON_LB = 4;
	/** The RB-button on a XBOX 360 control */
	public static final int BUTTON_RB = 5;
	/** The BACK-button on a XBOX 360 control */
	public static final int BUTTON_BACK = 6;
	/** The START-button on a XBOX 360 control */
	public static final int BUTTON_START = 7;
	/** The button on the left axis stick on a XBOX 360 control */
	public static final int BUTTON_LEFTSTICK = 8;
	/** The button on the right axis stick on a XBOX 360 control */
	public static final int BUTTON_RIGHTSTICK = 9;
	/** The right button on the dpad on a XBOX 360 control */
	public static final int BUTTON_DPAD_RIGHT = 10;
	/** The down button on the dpad on a XBOX 360 control */
	public static final int BUTTON_DPAD_DOWN = 11;
	/** The left button on the dpad on a XBOX 360 control */
	public static final int BUTTON_DPAD_LEFT = 12;
	/** The up button on the dpad on a XBOX 360 control */
	public static final int BUTTON_DPAD_UP = 13;

	/** The LT-button on a XBOX 360 control */
	public static final int BUTTON_LT = 22;
	/** The RT-button on a XBOX 360 control */
	public static final int BUTTON_RT = 23;

	/** The positive y-axis as a button (left axis, up) */
	public static final int AXIS_Y_POS = 14;
	/** The negative y-axis as a button (left axis, down) */
	public static final int AXIS_Y_NEG = 15;
	/** The positive x-axis as a button (left axis, right) */
	public static final int AXIS_X_POS = 16;
	/** The negative x-axis as a button (left axis, left) */
	public static final int AXIS_X_NEG = 17;
	/** The positive rotation y-axis as a button (right axis, up) */
	public static final int AXIS_ROTATE_Y_POS = 18;
	/** The negative rotation y-axis as a button (right axis, down) */
	public static final int AXIS_ROTATE_Y_NEG = 19;
	/** The positive rotation x-axis as a button (right axis, right) */
	public static final int AXIS_ROTATE_X_POS = 20;
	/** The negative rotation x-axis as a button (right axis, left) */
	public static final int AXIS_ROTATE_X_NEG = 21;
	/** The positive z-axis as a button (same as LT) */
	public static final int AXIS_Z_POS = 22;
	/** The negative z-axis as a button (same as RT) */
	public static final int AXIS_Z_NEG = 23;
	
	/** The y-axis */
	public static final int AXIS_Y = 24;
	/** The x-axis */
	public static final int AXIS_X = 25;
	/** The rotation y-axis */
	public static final int AXIS_ROTATE_Y = 26;
	/** The rotation x-axis */
	public static final int AXIS_ROTATE_X = 27;
	/** The z-axis */
	public static final int AXIS_Z = 28;
	
	private static final int nbrOfButtons = 14;
	private static final int nbrOfAxis = 5;
	
	private XboxCommunicator communicator;
	
	/**
	 * Sets up an input controller with the given controller.
	 * Dead zone is set to 0.7
	 * @param controller The controller to use
	 */
	public XboxInput(Controller controller){
		this(controller, 0.7f);
	}

	/**
	 * Sets up an input controller with given controller and dead zone
	 * @param controller The controller to use
	 * @param deadZone The minimum change that will be reckoned for an axis
	 */
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
	
	/**
	 * Sets the dead zone of a certain axis
	 * @param index The index of the axis
	 * @param deadZone The minimum change that will be reckoned for an axis
	 * @throws IllegalArgumentException If the given index is not a valid abstract axis index
	 */
	public void setDeadZone(int index, float deadZone){
		if(isAxis(index)){
			this.communicator.setDeadZone(buttonToAxis(index), deadZone);
		}else{
			throw new IllegalArgumentException("The given ID was not an axis");
		}
	}
	
	/**
	 * Sets the minimum value that an axis may be. All values
	 * under this value is considered to be 0.
	 * @param index The index of the axis
	 * @param minimumValue The minimum value to use
	 * @throws IllegalArgumentException If the given index is not a valid abstract axis index
	 */
	public void setMinumumAxisValue(int index, float minimumValue){
		if(isAxis(index)){
			this.communicator.setMinimumAxisValue(buttonToAxis(index), minimumValue);
		}else{
			throw new IllegalArgumentException("The given ID was not an axis");
		}
	}
	
	/**
	 * Determines whether the given button is pressed or not
	 * @param buttonIndex The index of the button
	 * @return <code>true</code> if the button is pressed, <code>false</code> otherwise
	 */
	public boolean isButtonPressed(int buttonIndex){
		return this.communicator.isButtonPressed(buttonIndex);
	}
	
	/**
	 * Determines whether the given button is held down or not
	 * @param buttonIndex The index of the button
	 * @return <code>true</code> if the button is held down, <code>false</code> otherwise
	 */
	public boolean isButtonHeld(int buttonIndex){
		return this.communicator.isButtonHeld(buttonIndex);
	}
	
	/**
	 * Determines whether the given axis has been unrestored (works like pressed for buttons)
	 * @param index The index of the axis
	 * @return <code>true</code> if the axis is activated, <code>false</code> otherwise
	 */
	public boolean axisIsActivated(int index){
		return axisIsActive(index) && this.communicator.hasAxisChanged(buttonToAxis(index));
	}
	
	/**
	 * Determines whether the given axis is being held into the correct direction, 
	 * in either a positive or negative way (works like held down for buttons).
	 * NOTE: This method takes positive/negative values in consideration. For just testing
	 * if the axis is not 0, use axisIsMoving(index).
	 * @param index The index of the axis
	 * @return <code>true</code> if the axis is active, <code>false</code> otherwise
	 */
	public boolean axisIsActive(int index){
		return axisIsMoving(index) && (index%2==0?axisIsPositive(index):!axisIsPositive(index));
	}
	
	/**
	 * Determines whether the axis is not held in standard mode
	 * @param index The index of the axis
	 * @return <code>true</code> if the axis is not held in standard mode, <code>false</code> otherwise
	 */
	public boolean axisIsMoving(int index){
		return getAxisValue(index) != 0;
	}
	
	/**
	 * Determines if the axis has an positive value
	 * @param index The index of the axis
	 * @return <code>true</code> if the value of axis is positive (axis held up), <code>false</code> otherwise
	 */
	public boolean axisIsPositive(int index){
		return getAxisValue(index)>0;
	}
	
	/**
	 * Retrieves the value of the given axis
	 * @param index The index of the axis
	 * @return The value of the given axis
	 */
	public float getAxisValue(int index){
		int axisIndex = buttonToAxis(index);
		return this.communicator.getAxisValue(axisIndex);
	}
	
	/**
	 * Checks whether an abstract axis index equals a real index
	 * @param axisIndex The abstract axis index as declared in public variables in XboxInput
	 * @param index The real index
	 * @return <code>true</code> if the indexes connects to the same axis, <code>false</code> otherwise
	 */
	public static boolean axisEquals(int axisIndex, int index){
		return axisIndex == buttonToAxis(index);
	}
	
	/**
	 * Converts an abstract axis index (here named buttonIndex) to a real axis index.
	 * @param index The abstract axis index
	 * @return The real index of the axis
	 */
	private static int buttonToAxis(int index){
		if(isAxisButton(index)){
			return (index-nbrOfButtons)/2;
		}else if(isAxis(index)){
			return index-nbrOfButtons-2*nbrOfAxis;
		}else{
			return -1;
		}
	}
	
	/**
	 * Checks whether the given index belongs to a button
	 * @param index The index to test
	 * @return <code>true</code> if the index represent a button, <code>false</code> otherwise
	 */
	public static boolean isButton(int index){
		return index<nbrOfButtons && index>=0;
	}

	/**
	 * Checks whether the given index belongs to an axis used as a button
	 * @param index The index to test
	 * @return <code>true</code> if the index represent an axis used as a button, <code>false</code> otherwise
	 */
	public static boolean isAxisButton(int index){
		return index>=nbrOfButtons && index<nbrOfButtons+nbrOfAxis*2;
	}

	/**
	 * Checks whether the given index belongs to an axis used as an axis
	 * @param index The index to test
	 * @return <code>true</code> if the index represent an axis used as an axis, <code>false</code> otherwise
	 */
	public static boolean isAxis(int index){
		int firstIndex = nbrOfButtons+nbrOfAxis*2;
		return index>=firstIndex && index<firstIndex+nbrOfAxis;
	}
	
	/**
	 * Closes the communications
	 */
	public void close(){
		this.communicator.close();
	}

}

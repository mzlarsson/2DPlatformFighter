package edu.chalmers.brawlbuddies.controller.input.xbox;

/**
 * Interface for accessing listener methods for XBOX 360 controllers
 * @author Matz Larsson
 * @version 1.0
 *
 */

public interface XboxListener {

	/**
	 * Triggered when a button on the Xbox control is pressed
	 * @param buttonIndex The index of the pressed button
	 */
	public void buttonPressed(int buttonIndex);

	/**
	 * Triggered when a button on the Xbox control is released
	 * @param buttonIndex The index of the pressed button
	 */
	public void buttonReleased(int buttonIndex);
	
	
	/**
	 * Triggered when the value of an axis has been changed
	 * @param axisIndex The index of the axis
	 */
	public void axisChanged(int axisIndex);
	
	/**
	 * Triggered when the value of an axis has become 0 again
	 * @param axisIndex The index of the axis
	 */
	public void axisRestored(int axisIndex);

	/**
	 * Triggered when the value of an axis has gone from 0 to another value
	 * @param axisIndex The index of the axis
	 */
	public void axisUnrestored(int axisIndex);

}

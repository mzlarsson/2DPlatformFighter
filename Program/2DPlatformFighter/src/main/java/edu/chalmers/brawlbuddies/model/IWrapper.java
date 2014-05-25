package edu.chalmers.brawlbuddies.model;
/***
 * A interface to describe wrapper objects
 * @author Lisa Lipkin
 * @version 1.0
 *
 */
public interface IWrapper {
	/**
	 * Get the type ID of the wrapper object
	 * @return int - the type ID of the wrapper object
	 */
	public int getTypeID();
	/**
	 * Get the unique ID of the wrapper object
	 * @return int - the unique ID of the wrapper object
	 */
	public int getUniqeID();

}

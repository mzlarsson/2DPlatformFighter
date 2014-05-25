package edu.chalmers.brawlbuddies.eventbus;

/**
 * A class that creates an Event that is used by the EventBus and it's
 * subscribers.
 * @author Lisa Lipkin
 * @version 1.0
 * 
 */
public class EventBusEvent {
	private String name;
	private Object recipient;
	private Object actor;

	/**
	 * Constructor to create an event.
	 * 
	 * @param name
	 *            a string that contains the name of the type of the event. The
	 *            name must be a viable event name.
	 * @param recipient
	 *            the object affected by the event if neccecary. Null otherwise.
	 * @param actor
	 *            the object that is the source of the event if neccecary. Null
	 *            otherwise.
	 */
	public EventBusEvent(String name, Object recipient, Object actor) {
		this.name = name;
		this.recipient = recipient;
		this.actor = actor;
	}

	/**
	 * Getter for the name of the event
	 * 
	 * @return the name of the event.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the recipient of the event
	 * 
	 * @return the recipient
	 */
	public Object getRecipient() {
		return recipient;
	}

	/**
	 * Getter fot the actor of the event
	 * 
	 * @return the actor
	 */
	public Object getActor() {
		return actor;
	}

}

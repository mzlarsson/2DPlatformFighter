package edu.chalmers.brawlbuddies.eventbus;

/**
 * Interface for EventBusSubscribers.
 * 
 * @author Lisa
 * 
 */
public interface IEventBusSubscriber {
	/**
	 * Method called when an event has ben forwarded to the subscriber by the
	 * EventBus.
	 * 
	 * @param event
	 *            the event that has been performed.
	 */
	public void eventPerformed(EventBusEvent event);

}

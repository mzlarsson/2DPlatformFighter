package edu.chalmers.brawlbuddies.eventbus;

import java.util.List;

/**
 * A singelton EventBuss that forwards events between the view and model.
 * 
 * @author Lisa Lipkin
 * 
 */
public class EventBus {
	private static EventBus instance;
	private List<IEventBusSubscriber> subscribers;

	/**
	 * Private constructor for singelton EventBus
	 */
	private EventBus() {
		instance = this;
	}

	/**
	 * A getter method for the singelton instance. If the instance is null a new
	 * EventBus is created.
	 * 
	 * @return the instance
	 */
	public static EventBus getInstance() {
		if (instance == null) {
			instance = new EventBus();
		}
		return instance;
	}

	/**
	 * Adds a subscriber to the EventsBus' subscriber list.
	 * 
	 * @param subscriber
	 *            that implements the IEventBusSubscriber interface.
	 */
	public void addSubscriber(IEventBusSubscriber subscriber) {
		subscribers.add(subscriber);
	}

	/**
	 * Removes a subscriber from the EventBus' subscriber list.
	 * 
	 * @param subscriber
	 *            that implements the IEentBusSubscriber interface.
	 */
	public void removeSubscriber(IEventBusSubscriber subscriber) {
		subscribers.remove(subscriber);
	}

	/**
	 * Calls the EventPerformed in the subscribers and forwards the event to all
	 * subscribers.
	 * 
	 * @param event
	 *            that has been performed.
	 */
	public void fireEvent(EventBusEvent event) {
		for (IEventBusSubscriber subscriber : subscribers) {
			subscriber.eventPerformed(event);
		}
	}
	public List<IEventBusSubscriber> getSubscribers(){
		return subscribers;
	}
}

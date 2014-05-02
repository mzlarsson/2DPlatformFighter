package edu.chalmers.brawlbuddies.eventbus;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.chalmers.brawlbuddies.view.View;

public class EventBusTest {

	@Test
	public void testGetIntstance() {
		System.out.println("Test the getInstance method.");
		EventBus instance = EventBus.getIntstance();
		assertTrue(instance == EventBus.getIntstance());
	}

	@Test
	public void testAddSubscriber() {
		System.out.println("Test the addSubscriber method.");
		EventBus instance = EventBus.getIntstance();
		View view = new View();
		instance.addSubscriber(view);
		assertTrue(instance.getSubscribers().contains(view));
		
	}

	@Test
	public void testRemoveSubscriber() {
		System.out.println("Test the removeSubscriber method");
		EventBus instance = EventBus.getIntstance();
		View view = new View();
		instance.addSubscriber(view);
		instance.removeSubscriber(view);
		assertTrue(!(instance.getSubscribers().contains(view)));
	}

	//@Test
	//public void testFireEvent() {
		//System.out.println("Test the removeSubscriber method");
		//EventBus instance = EventBus.getIntstance();
		//View view = new View();
		//instance.addSubscriber(view);
		
	//}

}

package edu.chalmers.brawlbuddies.controller.menu;

public class MenuEvent {
	
	private String name;
	private String value;

	public MenuEvent(String value) {
		this(null, value);
	}
	
	public MenuEvent(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getValue(){
		return this.value;
	}

}

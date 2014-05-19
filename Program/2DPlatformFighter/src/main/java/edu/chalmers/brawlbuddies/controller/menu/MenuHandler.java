package edu.chalmers.brawlbuddies.controller.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import edu.chalmers.brawlbuddies.view.menu.MenuItem;
import edu.chalmers.brawlbuddies.view.menu.MenuView;
import edu.chalmers.brawlbuddies.view.menu.MultiChoiceMenuItem;

public class MenuHandler {

	private MenuView menuView;
	private Map<Integer, Integer> cooldown;
	private List<MenuListener> listeners;
	
	public MenuHandler(MenuView view) {
		this.menuView = view;
		this.cooldown = new TreeMap<Integer, Integer>();
		this.listeners = new ArrayList<MenuListener>();
	}
	
	public void addMenuListener(MenuListener listener){
		this.listeners.add(listener);
	}
	
	public void removeMenuListener(MenuListener listener){
		this.listeners.remove(listener);
	}
	
	public void notifyAll(MenuItem item){
		for(MenuListener listener : this.listeners){
			listener.menuActivated(new MenuEvent(item));
		}
	}
	
	
	public void update(GameContainer gc, int delta){
		updateCooldown(delta);
		
		Input input = gc.getInput();
		for(MenuItem item : menuView.getMenuItems()){
			if(item.isWithin((float)input.getMouseX(), (float)input.getMouseY())){
				if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
					if(menuView.getSelectedItem() instanceof MultiChoiceMenuItem){
						MultiChoiceMenuItem mcmItem = ((MultiChoiceMenuItem)menuView.getSelectedItem());
						if(mcmItem.isWithinRight((float)input.getMouseX(), (float)input.getMouseY())){
							mcmItem.nextItem();
						}else{
							mcmItem.prevItem();
						}
					}
					this.notifyAll(item);
				}
				menuView.setSelectedItem(item);
			}
		}

		if(input.isKeyDown(Input.KEY_DOWN) && !hasCooldown(Input.KEY_DOWN)){
			menuView.nextItem();
			setCooldown(Input.KEY_DOWN, 200);
		}
		if(input.isKeyDown(Input.KEY_UP) && !hasCooldown(Input.KEY_UP)){
			menuView.prevItem();
			setCooldown(Input.KEY_UP, 200);
		}
		if(input.isKeyDown(Input.KEY_RIGHT) && !hasCooldown(Input.KEY_RIGHT)){
			if(menuView.getSelectedItem() instanceof MultiChoiceMenuItem){
				((MultiChoiceMenuItem)menuView.getSelectedItem()).nextItem();
				this.notifyAll(menuView.getSelectedItem());
				setCooldown(Input.KEY_RIGHT, 300);
			}
		}
		if(input.isKeyDown(Input.KEY_LEFT) && !hasCooldown(Input.KEY_LEFT)){
			if(menuView.getSelectedItem() instanceof MultiChoiceMenuItem){
				((MultiChoiceMenuItem)menuView.getSelectedItem()).prevItem();
				this.notifyAll(menuView.getSelectedItem());
				setCooldown(Input.KEY_LEFT, 300);
			}
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			if(menuView.get("gotoMain") != null){
				this.notifyAll(menuView.get("gotoMain"));
			}
		}
		
		if(input.isKeyPressed(Input.KEY_ENTER) && !hasCooldown(Input.KEY_ENTER)){
			this.notifyAll(menuView.getSelectedItem());
			setCooldown(Input.KEY_ENTER, 500);
		}
	}
	
	
	private void updateCooldown(int delta){
		for(Integer key : this.cooldown.keySet()){
			setCooldown(key, cooldown.get(key)-delta);
		}
	}
	
	private void setCooldown(int key, int value){
		this.cooldown.put(key, value);
	}
	
	private boolean hasCooldown(int key){
		return this.cooldown.get(key) != null && this.cooldown.get(key)>0;
	}

}

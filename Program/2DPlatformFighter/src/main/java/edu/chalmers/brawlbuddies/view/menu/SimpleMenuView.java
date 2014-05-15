package edu.chalmers.brawlbuddies.view.menu;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class SimpleMenuView implements MenuView{
	
	private List<MenuItem> items;
	private int selectedIndex = 0;

	public SimpleMenuView() {
		items = new ArrayList<MenuItem>();
	}

	public void render(GameContainer gc, Graphics g) {
		for(MenuItem item : this.items){
			item.render(gc, g);
		}
	}
	
	public void add(MenuItem item){
		if(item != null){
			this.items.add(item);
		}
	}
	
	public List<MenuItem> getMenuItems(){
		return this.items;
	}

	public MenuItem getSelectedItem(){
		return this.items.get(selectedIndex);
	}
	
	public void setSelectedItem(MenuItem item){
		for(int i = 0; i<items.size(); i++){
			if(items.get(i) == item){
				setSelection(i);
			}
		}
	}
	
	public void update(){
		setSelection(0);
		for(MenuItem item : this.items){
			item.recalculate();
		}
	}
	
	public void nextItem(){
		setSelection((this.selectedIndex+1)%items.size());
	}
	
	public void prevItem(){
		setSelection((this.selectedIndex-1+items.size())%items.size());
	}
	
	private void setSelection(int selectedIndex){
		this.items.get(this.selectedIndex).setActive(false);
		this.selectedIndex = selectedIndex;
		this.items.get(this.selectedIndex).setActive(true);
	}
}

package edu.chalmers.brawlbuddies.view.menu;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


import edu.chalmers.brawlbuddies.util.ResourceLoader;
//import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.view.SideScroller;

/**
 * A general description of a graphical representation of a menu view
 * @author Matz Larsson
 * @version 1.0
 *
 */

public class SimpleMenuView implements MenuView{

	public static final float REFERENCE_SIZE_X = 1920.0f;
	public static final float REFERENCE_SIZE_Y = 1080.0f;
	
	private List<MenuItem> items;
	private int selectedIndex = 0;
	private Image background;

	public SimpleMenuView() {
		items = new ArrayList<MenuItem>();
	}
	
	public void setBackground(String path){
		this.background = ResourceLoader.getImage(path);
	}

	public void render(GameContainer gc, Graphics g){
		g.scale(gc.getWidth()/1920.0f, gc.getHeight()/1080.0f);
		if(background != null){
			g.drawImage(background, 0, 0);
		}

		for(MenuItem item : this.items){
			item.render(gc, g);
		}
	}
	
	public void add(MenuItem item){
		if(item != null){
			this.items.add(item);
		}
	}
	
	public void clearItems(){
		if(items != null){
			this.items.clear();
		}
	}
	
	public List<MenuItem> getMenuItems(){
		return this.items;
	}
	
	public MenuItem get(String itemName){
		for(MenuItem item : this.items){
			if(item.getItemName().equals(itemName)){
				return item;
			}
		}
		
		return null;
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
	
	public void updateContents(){
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
		if(selectedIndex>=0 && selectedIndex<this.items.size()){
			this.items.get(this.selectedIndex).setActive(false);
			this.selectedIndex = selectedIndex;
			this.items.get(this.selectedIndex).setActive(true);
		}
	}

	public SideScroller getScroller() {
		return null;
	}

	public void close() {
	}

	public void update(int delta) {
	}
}

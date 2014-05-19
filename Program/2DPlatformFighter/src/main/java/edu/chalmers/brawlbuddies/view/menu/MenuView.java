package edu.chalmers.brawlbuddies.view.menu;

import java.util.List;

import edu.chalmers.brawlbuddies.view.IView;

public interface MenuView extends IView{

	public List<MenuItem> getMenuItems();
	public MenuItem getSelectedItem();
	public MenuItem get(String itemName);
	public void setSelectedItem(MenuItem item);
	
	public void nextItem();
	public void prevItem();
	
	public void updateContents();
	public void update(int delta);
	
}

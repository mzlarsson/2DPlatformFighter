package edu.chalmers.brawlbuddies.view.menu;

public class MainMenuView extends SimpleMenuView{

	public MainMenuView() {
		String[] names = {"Start game", "Options", "Credits", "Exit"};
		for(int i = 0; i<names.length; i++){
			this.add(new SimpleMenuItem(names[i], 170+115*i));
		}
	}
}

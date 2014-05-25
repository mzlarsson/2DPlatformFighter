package edu.chalmers.brawlbuddies.view.menu;

import edu.chalmers.brawlbuddies.Constants;
/**
 * A class to describe the main menu in the menu system
 * @author Matz Larsson
 *
 */
public class MainMenuView extends SimpleMenuView{
	/**
	 * Creates a new MainMenuView
	 */
	public MainMenuView() {
		String[] names = {"Start game", "Options", "Credits", "Exit"};
		String[] itemNames = {"startGame", "options", "credits", "exit"};
		for(int i = 0; i<names.length; i++){
			this.add(new SimpleMenuItem(itemNames[i], names[i], 250+115*i));
		}
		this.setBackground(Constants.MENU_IMAGES + "menu_main.png");
	}
}

package edu.chalmers.brawlbuddies.view.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import edu.chalmers.brawlbuddies.Constants;

public class CreditsMenuView extends SimpleMenuView{

	public CreditsMenuView() {
		this.add(new SimpleMenuItem("gotoMain", "Back", 640));

		this.setBackground(Constants.IMAGES + "menus/menu_credits.png");
	}
	
	@Override
	public void render(GameContainer gc, Graphics g){
		super.render(gc, g);
		
		g.setColor(SimpleMenuItem.getActiveColor());
		String[] lines = {"Brawl Buddies", "Made by Small Electric Boar Riot", "Nano   Volt   Boarman   Nika"};
		for(int i = 0; i<lines.length; i++){
			g.drawString(lines[i], (gc.getWidth()-g.getFont().getWidth(lines[i]))/2, 300+40*i);
		}
	}

}

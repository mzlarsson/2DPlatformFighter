package edu.chalmers.brawlbuddies.view.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.util.FontCreator;

public class EndScreenView extends SimpleMenuView {

	private String winnerName;
	
	public EndScreenView() {
		this.winnerName = "Not yet defined";
		this.add(new SimpleMenuItem("gotoMain", "Ok", 640));
		
		this.setBackground(Constants.MENU_IMAGES + "menu_endscreen.png");
	}
	
	public void setWinner(String winnerName) {
		this.winnerName = winnerName;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g){
		super.render(gc, g);
		
		g.setColor(SimpleMenuItem.getActiveColor());
		g.setFont(FontCreator.getFont(FontCreator.MEDIUM));
		String[] lines = {"The Winner Is:", winnerName};
		g.setFont(FontCreator.getFont(FontCreator.BIG));
		for(int i = 0; i<lines.length; i++){
			g.drawString(lines[i], (SimpleMenuView.REFERENCE_SIZE_X-g.getFont().getWidth(lines[i]))/2, 300+40*i);
		}
	}
}

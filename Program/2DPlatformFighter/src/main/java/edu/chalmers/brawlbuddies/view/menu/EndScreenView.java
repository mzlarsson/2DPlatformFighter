package edu.chalmers.brawlbuddies.view.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class EndScreenView extends SimpleMenuView {

	private String winnerName;
	
	public EndScreenView() {
		this.winnerName = "Not yet defined";
		this.add(new SimpleMenuItem("Ok", 640));
	}
	
	public void setWinner(String winnerName) {
		this.winnerName = winnerName;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g){
		super.render(gc, g);
		
		g.setColor(Color.white);
		String[] lines = {"The Winner Is:", winnerName};
		for(int i = 0; i<lines.length; i++){
			g.drawString(lines[i], (gc.getWidth()-g.getFont().getWidth(lines[i]))/2, 300+40*i);
		}
	}
}

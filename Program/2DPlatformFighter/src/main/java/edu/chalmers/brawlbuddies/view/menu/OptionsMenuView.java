package edu.chalmers.brawlbuddies.view.menu;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class OptionsMenuView extends SimpleMenuView {
	
	private TrueTypeFont font = new TrueTypeFont(new Font("Arial", Font.BOLD, 24), false, null);

	public OptionsMenuView() {
		this.add(new MultiChoiceMenuItem("Music", new String[]{"Off", "On"}, 170));
		this.add(new MultiChoiceMenuItem("Sound", new String[]{"Off", "On"}, 285));
		this.add(new MultiChoiceMenuItem("Resolution", new String[]{"800x600", "1280x720", "1366x768", "1600x900", "1680x1050", "1920x1080"}, 400));
		this.add(new MultiChoiceMenuItem("Fullscreen", new String[]{"Off", "On"}, 515));
		this.add(new SimpleMenuItem("Back", 640));
	}
	
	@Override
	public void render(GameContainer gc, Graphics g){
		super.render(gc, g);
		g.setColor(Color.white);
		String typeName = "Options";
		g.setFont(font);
		g.drawString(typeName, (gc.getWidth()-g.getFont().getWidth(typeName))/2, 100);
	}
}

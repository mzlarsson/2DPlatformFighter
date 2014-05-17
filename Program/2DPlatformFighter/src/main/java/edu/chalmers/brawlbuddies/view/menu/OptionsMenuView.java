package edu.chalmers.brawlbuddies.view.menu;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class OptionsMenuView extends SimpleMenuView {
	
	private TrueTypeFont font = new TrueTypeFont(new Font("Arial", Font.BOLD, 24), false, null);

	public OptionsMenuView() {
		List<MultiChoiceOption> onOff = new ArrayList<MultiChoiceOption>();
		onOff.add(new MultiChoiceOption("false", "Off"));
		onOff.add(new MultiChoiceOption("true", "On"));
		
		this.add(new MultiChoiceMenuItem("music", "Music", onOff, 170));
		this.add(new MultiChoiceMenuItem("sound", "Sound", onOff, 285));
		this.add(new MultiChoiceMenuItem("resolution", "Resolution", MultiChoiceOption.stringToMultiChoice(new String[]{"800x600", "1280x720", "1366x768", "1600x900", "1680x1050", "1920x1080"}), 400));
		this.add(new MultiChoiceMenuItem("fullscreen", "Fullscreen", onOff, 515));
		this.add(new SimpleMenuItem("gotoMain", "Back", 640));
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

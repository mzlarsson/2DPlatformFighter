package edu.chalmers.brawlbuddies.view.menu;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.controller.menu.Settings;

public class OptionsMenuView extends SimpleMenuView {
	
	private TrueTypeFont font = new TrueTypeFont(new Font("Arial", Font.BOLD, 24), false, null);

	public OptionsMenuView(Map<String, String> settings) {
		List<MultiChoiceOption> onOff = new ArrayList<MultiChoiceOption>();
		onOff.add(new MultiChoiceOption("false", "Off"));
		onOff.add(new MultiChoiceOption("true", "On"));
		
		MultiChoiceMenuItem music = new MultiChoiceMenuItem("music", "Music", onOff, 170);
		music.setItem(settings.get("music"));
		this.add(music);
		MultiChoiceMenuItem sound = new MultiChoiceMenuItem("sound", "Sound", onOff, 285);
		sound.setItem(settings.get("sound"));
		this.add(sound);
		MultiChoiceMenuItem resolution = new MultiChoiceMenuItem("resolution", "Resolution", MultiChoiceOption.stringToMultiChoice(new String[]{"800x600", "1280x720", "1366x768", "1600x900", "1680x1050", "1920x1080"}), 400);
		resolution.setItem(settings.get("resolution"));
		this.add(resolution);
		MultiChoiceMenuItem fullscreen = new MultiChoiceMenuItem("fullscreen", "Fullscreen", onOff, 515);
		fullscreen.setItem(settings.get("fullscreen"));
		this.add(fullscreen);
		
		this.add(new SimpleMenuItem("gotoMain", "Back", 640));

		this.setBackground(Constants.IMAGES + "menus/menu_options.png");
	}
	
	@Override
	public void render(GameContainer gc, Graphics g){
		super.render(gc, g);
		g.setColor(SimpleMenuItem.getActiveColor());
		String typeName = "Options";
		g.setFont(font);
		g.drawString(typeName, (gc.getWidth()-g.getFont().getWidth(typeName))/2, 100);
	}
}

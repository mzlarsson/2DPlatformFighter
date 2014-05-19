package edu.chalmers.brawlbuddies.view.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.chalmers.brawlbuddies.Constants;

public class OptionsMenuView extends SimpleMenuView {

	public OptionsMenuView(Map<String, String> settings) {
		List<MultiChoiceOption> onOff = new ArrayList<MultiChoiceOption>();
		onOff.add(new MultiChoiceOption("false", "Off"));
		onOff.add(new MultiChoiceOption("true", "On"));
		
		MultiChoiceMenuItem music = new MultiChoiceMenuItem("music", "Music", onOff, 220);
		music.setItem(settings.get("music"));
		this.add(music);
		MultiChoiceMenuItem sound = new MultiChoiceMenuItem("sound", "Sound", onOff, 335);
		sound.setItem(settings.get("sound"));
		this.add(sound);
		MultiChoiceMenuItem resolution = new MultiChoiceMenuItem("resolution", "Resolution", getResolutionOptions(), 450);
		resolution.setItem(settings.get("resolution"));
		this.add(resolution);
		MultiChoiceMenuItem fullscreen = new MultiChoiceMenuItem("fullscreen", "Fullscreen", onOff, 565);
		fullscreen.setItem(settings.get("fullscreen"));
		this.add(fullscreen);
		
		this.add(new SimpleMenuItem("gotoMain", "Save and go back", 690));

		this.setBackground(Constants.MENU_IMAGES + "menu_options.png");
	}
	
	private List<MultiChoiceOption> getResolutionOptions(){
		List<MultiChoiceOption> options = new ArrayList<MultiChoiceOption>();
		options.add(new MultiChoiceOption("1280x720", "1280x720"));
		options.add(new MultiChoiceOption("1366x768", "1366x768"));
		options.add(new MultiChoiceOption("1600x900", "1600x900"));
		options.add(new MultiChoiceOption("1680x1050", "1680x1050"));
		options.add(new MultiChoiceOption("1920x1080", "1920x1080 [Real HD]"));
		return options;
	}
}

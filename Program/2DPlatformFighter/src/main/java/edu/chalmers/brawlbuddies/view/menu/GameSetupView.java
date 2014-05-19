package edu.chalmers.brawlbuddies.view.menu;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.controller.input.InputHandlerChooser;
import edu.chalmers.brawlbuddies.model.world.CharacterFactory;
import edu.chalmers.brawlbuddies.model.world.GameMapFactory;

public class GameSetupView extends SimpleMenuView{
	
	private boolean loaded = false;
	private TrueTypeFont font = new TrueTypeFont(new Font("Arial", Font.BOLD, 24), false, null);
	private static Dimension standardSize = new Dimension(400, 60);
	private int centerOffset = 50;
	private int topOffset = 0;

	private List<MultiChoiceOption> characters;
	private List<MultiChoiceOption> controllers;
	private List<MultiChoiceOption> maps;

	public GameSetupView(){
	}
	
	public void setData(Map<String, String> characters, Map<String, String> controllers, Map<String, String> maps){
		this.characters = MultiChoiceOption.stringToMultiChoice(characters);
		this.controllers = MultiChoiceOption.stringToMultiChoice(controllers);
		this.maps = MultiChoiceOption.stringToMultiChoice(maps);
	}
	
	public void load(GameContainer gc){
		this.topOffset = (gc.getHeight()-780)/2;
		
		//Player1
		int x = (int)(gc.getWidth()/2-centerOffset-standardSize.getWidth());
		this.add(new MultiChoiceMenuItem("p1_character", "Character", characters, new Position(x, 150+topOffset), standardSize, false));
		this.add(new MultiChoiceMenuItem("p1_control", "Control", controllers, new Position(x, 265+topOffset), standardSize, false));
		//Player2
		x = (int)(gc.getWidth()/2+centerOffset);
		this.add(new MultiChoiceMenuItem("p2_character", "Character", characters, new Position(x, 150+topOffset), standardSize, false));
		MultiChoiceMenuItem p2_control = new MultiChoiceMenuItem("p2_control", "Control", controllers, new Position(x, 265+topOffset), standardSize, false);
		p2_control.setItem(controllers.get(1).getCodeValue());
		p2_control.enable(controllers.get(0).getCodeValue(), false);
		this.add(p2_control);
		//Map choice
		this.add(new MultiChoiceMenuItem("map", "Map", maps, 400+topOffset, standardSize));
		//Type choice
		this.add(getChoiceLiveMode());
		this.add(getChoiceTimeMode());
		//Submit
		x = (int)(gc.getWidth()/2-centerOffset-500);
		this.add(new SimpleMenuItem("gotoMain", "Back", new Position(x, 670+topOffset)));
		x = (int)(gc.getWidth()/2+centerOffset);
		this.add(new SimpleMenuItem("startGame", "Start game", new Position(x, 670+topOffset)));
		this.setSelectedItem(this.getMenuItems().get(0));

		this.setBackground(Constants.MENU_IMAGES + "menu_gamesetup.png");
	}
	
	private MultiChoiceMenuItem getChoiceLiveMode(){
		List<MultiChoiceOption> options = new ArrayList<MultiChoiceOption>();
		options.add(new MultiChoiceOption("-1", "No lives"));
		for(int i = 1; i<=10; i++){
			options.add(new MultiChoiceOption(""+i, ""+i));
		}
		
		MultiChoiceMenuItem mcmItem = new MultiChoiceMenuItem("mode_lives", "Lives", options, 480+topOffset, standardSize);
		mcmItem.setItem("3");
		return mcmItem;
	}
	
	private MultiChoiceMenuItem getChoiceTimeMode(){
		List<MultiChoiceOption> options = new ArrayList<MultiChoiceOption>();
		int[] times = {30, 60, 120, 180, 300, 450, 600, 900, 1200, 1800};
		options.add(new MultiChoiceOption("-1", "No time"));
		for(int i = 0; i<times.length; i++){
			int min = times[i]/60;
			int sec = times[i]%60;
			options.add(new MultiChoiceOption(times[i]+"", (min>0?(min+":"+(sec<10?"0"+sec:""+sec)+" minuter"):sec+" sekunder")));
		}
		
		MultiChoiceMenuItem mcmItem = new MultiChoiceMenuItem("mode_time", "Time", options, 560+topOffset, standardSize);
		return mcmItem;
	}

	@Override
	public void render(GameContainer gc, Graphics g){
		if(!loaded){
			this.clearItems();
			load(gc);
			super.updateContents();
			loaded = true;
		}
		
		super.render(gc, g);
		g.setColor(SimpleMenuItem.getActiveColor());
		g.setFont(font);
		String output = "Player1";
		g.drawString(output, (int)(gc.getWidth()/2-centerOffset-standardSize.getWidth()/2-g.getFont().getWidth(output)/2), 90+topOffset);
		output = "Player2";
		g.drawString(output, (int)(gc.getWidth()/2+centerOffset+standardSize.getWidth()/2-g.getFont().getWidth(output)/2), 90+topOffset);
	}
	
	@Override
	public void updateContents(){
		this.loaded = false;
	}
}

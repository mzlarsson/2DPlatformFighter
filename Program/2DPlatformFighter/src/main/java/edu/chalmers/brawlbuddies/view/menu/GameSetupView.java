package edu.chalmers.brawlbuddies.view.menu;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import edu.chalmers.brawlbuddies.controller.input.InputHandlerChooser;
import edu.chalmers.brawlbuddies.model.world.CharacterFactory;
import edu.chalmers.brawlbuddies.model.world.GameMapFactory;

public class GameSetupView extends SimpleMenuView{
	
	private boolean loaded = false;
	private TrueTypeFont font = new TrueTypeFont(new Font("Arial", Font.BOLD, 24), false, null);
	private static Dimension standardSize = new Dimension(400, 60);
	private int centerOffset = 50;
	private int topOffset = 0;

	public GameSetupView() {
	}
	
	public void load(GameContainer gc){
		this.topOffset = (gc.getHeight()-700)/2;
		List<MultiChoiceOption> characters = MultiChoiceOption.stringToMultiChoice(CharacterFactory.getAvailableCharacters());
		List<MultiChoiceOption> controllers = MultiChoiceOption.stringToMultiChoice(InputHandlerChooser.getInstance().getControllerNames());
		List<MultiChoiceOption> maps = MultiChoiceOption.stringToMultiChoice(GameMapFactory.getAvailableMaps());
		List<MultiChoiceOption> modes = new ArrayList<MultiChoiceOption>();
		modes.add(new MultiChoiceOption("lives", "Lives"));
		modes.add(new MultiChoiceOption("time", "Time"));
		modes.add(new MultiChoiceOption("timelives", "Time & Lives"));
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
		this.add(new MultiChoiceMenuItem("mode", "Mode", modes, 480+topOffset, standardSize));
		//Submit
		x = (int)(gc.getWidth()/2-centerOffset-500);
		this.add(new SimpleMenuItem("gotoMain", "Back", new Position(x, 590+topOffset)));
		x = (int)(gc.getWidth()/2+centerOffset);
		this.add(new SimpleMenuItem("startGame", "Start game", new Position(x, 590+topOffset)));
		this.setSelectedItem(this.getMenuItems().get(0));
	}

	@Override
	public void render(GameContainer gc, Graphics g){
		if(!loaded){
			this.clearItems();
			load(gc);
			super.update();
			loaded = true;
		}
		
		super.render(gc, g);
		g.setColor(Color.white);
		g.setFont(font);
		String output = "Player1";
		g.drawString(output, (int)(gc.getWidth()/2-centerOffset-standardSize.getWidth()/2-g.getFont().getWidth(output)/2), 90+topOffset);
		output = "Player2";
		g.drawString(output, (int)(gc.getWidth()/2+centerOffset+standardSize.getWidth()/2-g.getFont().getWidth(output)/2), 90+topOffset);
	}
	
	@Override
	public void update(){
		this.loaded = false;
	}
}
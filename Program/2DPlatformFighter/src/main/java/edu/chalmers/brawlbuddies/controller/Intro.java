package edu.chalmers.brawlbuddies.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Intro extends BasicGameState {
	
	private Image bg;
	private Image[] members;
	private Image logo;
	int time = 0;

	public Intro() {
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//Load images
		String path = "res/imgs/intro/";
		try {
			bg = new Image(path+"background.png").getScaledCopy(gc.getWidth(), gc.getHeight());
			members = new Image[4];
			members[0] = new Image(path+"nano.png").getScaledCopy(gc.getWidth(), gc.getHeight());
			members[1] = new Image(path+"volt.png").getScaledCopy(gc.getWidth(), gc.getHeight());
			members[2] = new Image(path+"boarman.png").getScaledCopy(gc.getWidth(), gc.getHeight());
			members[3] = new Image(path+"nika.png").getScaledCopy(gc.getWidth(), gc.getHeight());
			logo = new Image(path+"logo.png").getScaledCopy(gc.getWidth(), gc.getHeight());
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if(time<members.length*1800){
			int tmpTime = Math.min(time%1800, 1200);
			float scale = (float)tmpTime/2000;
			g.drawImage(this.bg, 0, 0);
			g.scale(scale, scale);
			g.translate(-tmpTime/6+500, -tmpTime/4+500);
			g.drawImage(this.members[time/1800], 0, 0);
		}else{
			if(time<members.length*1800+3000){
				g.scale(1.0f, 1.0f);
				g.translate(0.0f, 0.0f);
				g.drawImage(this.logo, 0, 0);
			}else{
				sbg.enterState(Constants.GAMESTATE_MAIN_MENU);
			}
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		this.time += delta;
	}

	@Override
	public int getID() {
		return Constants.GAMESTATE_INTRO;
	}

}

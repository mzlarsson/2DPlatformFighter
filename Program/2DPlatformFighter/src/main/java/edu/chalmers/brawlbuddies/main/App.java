package edu.chalmers.brawlbuddies.main;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class App extends BasicGame implements KeyListener
{
	int groundPosY = 460;
	double origPosX = 300;
	double origPosY = groundPosY;
	double posX = origPosX;
	double posY = groundPosY;
	double yGrav = 0.0000001;
	double verticalspeed = 0;
	double walkingspeed = 0.005;
	double jumpspeed = -0.011;
	int maxjumps = 2;
	int jumpsLeft = maxjumps;
	long airtime = System.nanoTime();
	long walktime = System.nanoTime();
	boolean airborne = true;
	boolean movingLeft = false;
	boolean movingRight = false;
	boolean jumpHeld = false;
	
	Sound sound = null;
	
	public App(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setVSync(true);
		sound = new Sound("res/steps.wav");
		
		Music music = new Music("res/song.ogg");
		music.loop(1.0f, 0.5f);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		// X-Movement
		if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
			if(!sound.playing())sound.loop(3.0f, 1.0f);
			if (!movingLeft) {
				movingLeft = true;
				movingRight = false;
				walktime = System.nanoTime();
				origPosX = posX;
			}
			long timeDiff = (System.nanoTime()-walktime)/10000;
			posX = origPosX + -1*walkingspeed*timeDiff;
		} else if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
			if(!sound.playing())sound.loop(3.0f, 1.0f);
			if (!movingRight) {
				movingLeft = false;
				movingRight = true;
				walktime = System.nanoTime();
				origPosX = posX;
			}
			long timeDiff = (System.nanoTime()-walktime)/10000;
			posX = origPosX + walkingspeed*timeDiff;
		} else if (movingLeft || movingRight) {
			origPosX = posX;
			movingLeft = false;
			movingRight = false;
			sound.stop();
		}
		
		
		// Y-Movement
		if (gc.getInput().isKeyDown(Input.KEY_SPACE)) {
			System.out.println("Space held");
			if (!jumpHeld && canJump()) {
				System.out.println("Jumping");
				airtime = System.nanoTime();
				verticalspeed = jumpspeed;
				jumpHeld = true;
				airborne = true;
			}
		}
		if (airborne) {
			long timeDiff = (System.nanoTime()-airtime)/10000;
			double speedDiff = verticalspeed + yGrav*timeDiff;
			if (!(gc.getInput().isKeyDown(Input.KEY_SPACE)) && (speedDiff<-0.1)) {
				verticalspeed = -0.1;
				airtime = System.nanoTime();
			}
			posY = origPosY + (verticalspeed + yGrav*timeDiff)*timeDiff;
			if (posY>=groundPosY) {
				posY = groundPosY;
				origPosY = posY;
				airborne = false;
			}
		} else {
			if (gc.getInput().isKeyDown(Input.KEY_SPACE)) {
				airtime = System.nanoTime();
				verticalspeed = jumpspeed;
				airborne = true;
			}
		}
	}

	public boolean canJump() {
		return jumpsLeft>0;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawOval((int)posX-15, (int)posY-15, 30, 30);
	}
	
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new App("Simple Slick Game"));
			appgc.setDisplayMode(1920, 1080, true);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
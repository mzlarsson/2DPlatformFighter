package edu.chalmers.platformfighter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class App extends BasicGame
{
	double origPosY = 200;
	double posY = 200;
	double yGrav = 0.0005;
	double speed = 0;
	Date timer = new Date();	
	long airtime = timer.getTime();
	public App(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	public void render(GameContainer gc, Graphics g) throws SlickException {
//		System.out.println((timer.getTime()-airtime));
//		System.out.println(timer.getTime());
//		posY = origPosY + (speed+yGrav*(timer.getTime()-airtime))*(timer.getTime()-airtime);
		speed += yGrav;
		posY += speed;
		if (posY>=480) {
			System.out.println("Boing!");
			posY = 480;
			origPosY = 480;
			airtime = timer.getTime();
			speed = -0.5;
		}
		g.drawString("Howdy!", 300, (int)posY);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new App("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
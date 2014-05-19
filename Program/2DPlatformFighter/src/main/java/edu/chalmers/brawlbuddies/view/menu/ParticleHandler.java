package edu.chalmers.brawlbuddies.view.menu;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class ParticleHandler {
	
	private ParticleSystem system;
	
	public ParticleHandler(String image) throws SlickException{
		this(new Image(image));
	}

	public ParticleHandler(Image image) {
		system = new ParticleSystem(image, 10000);
	}
	
	public void addEmitter(String xmlFile){
		this.addEmitter(xmlFile, 0, 0);
	}
	
	public void addEmitter(String xmlFile, int x, int y){
		try {
			File file = new File(xmlFile);
			ConfigurableEmitter emitter;
			emitter = ParticleIO.loadEmitter(file);
			emitter.setPosition(x, y);
			system.addEmitter(emitter);
		} catch (IOException e) {
			System.out.println("Could not load emitter: "+xmlFile);
			System.out.println(e.getMessage());
		}
	}
	
	public void update(int delta){
		system.update(delta);
	}
	
	public void render(){
		system.render();
	}

}

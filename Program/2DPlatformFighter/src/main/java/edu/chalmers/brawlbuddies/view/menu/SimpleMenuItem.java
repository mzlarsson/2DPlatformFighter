package edu.chalmers.brawlbuddies.view.menu;

import java.awt.Dimension;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class SimpleMenuItem implements MenuItem{
	
	private boolean recalculate = true;
	private boolean active = false;
	private boolean error = false;
	
	private int vertPos = 0;
	private int horizPos = 0;
	private String value = "";
	private String name = "";
	
	private Color opaqueColor = new Color(255, 255, 255, 60);
	private static Color errorColor = Color.red;
	private static Color activeColor = Color.black;
	private static Color inactiveColor = Color.gray;
	
	private Dimension size;
	private Position pos;
	
	public SimpleMenuItem(String name, String value, int vertPos){
		this(name, value, vertPos, new Dimension(500, 75));
	}
	
	public SimpleMenuItem(String name, String value, Position pos){
		this(name, value, pos, new Dimension(500, 75));
	}

	public SimpleMenuItem(String name, String value, int vertPos, Dimension size){
		this(name, value, new Position(-1, vertPos), size);
	}
	
	public SimpleMenuItem(String name, String value, Position pos, Dimension size) {
		this.name = name;
		this.value = value;
		this.horizPos = (int)(pos==null?0:pos.getX());
		this.vertPos = (int)(pos==null?0:pos.getY());
		this.size = size;
	}

	public void render(GameContainer gc, Graphics g) {
		renderSelection(gc, g);
		String val = (this.value==null?"":this.value);
		
		int stringWidth = g.getFont().getWidth(val);
		int stringHeight = g.getFont().getHeight(val);
		g.setColor(this.isActive()?activeColor:inactiveColor);
		g.drawString(val, (int)(pos.getX()+(getSize().getWidth()-stringWidth)/2), (int)(vertPos+(size.getHeight()-stringHeight)/2));
	}
	
	protected void renderSelection(GameContainer gc, Graphics g){
		Position pos = this.getPosition(gc);
		if(isActive()){
			g.setColor(this.opaqueColor);
			g.fillRect(pos.getX(), pos.getY(), (float)size.getWidth(), (float)size.getHeight());
		}
		g.setColor((this.error?errorColor:(this.isActive()?activeColor:inactiveColor)));
		g.drawRect(pos.getX(), pos.getY(), (float)size.getWidth(), (float)size.getHeight());
	}
	
	protected void setValue(String value){
		this.value = value;
	}
	
	public void setError(boolean hasError){
		this.error = hasError;
	}
	
	public void recalculate(){
		this.recalculate = true;
	}
	
	protected Position getPosition(GameContainer gc){
		if(pos == null || this.recalculate){
			if(this.horizPos>=0){
				this.pos = new Position(horizPos, vertPos);
				this.recalculate = false;
			}else if(gc != null){
				int x = (int)((gc.getWidth()-size.getWidth())/2);
				int y = vertPos;
				this.pos = new Position(x, y);
				this.recalculate = false;
			}else{
				return new Position(0, vertPos);
			}
		}
		
		return this.pos;
	}
	
	protected Dimension getSize(){
		return this.size;
	}
	
	public boolean isWithin(float x, float y){
		Position pos = this.getPosition(null);
		if(pos != null){
			if(x>pos.getX() && x<pos.getX()+this.size.getWidth()){
				if(y>pos.getY() && y<pos.getY()+this.size.getHeight()){
					return true;
				}
			}
		}

		return false;
	}
	
	public String getItemName(){
		return this.name;
	}

	public String getValue(){
		return this.value;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return this.active;
	}
	
	public static Color getErrorColor(){
		return errorColor;
	}
	public static Color getActiveColor(){
		return activeColor;
	}
	public static Color getInactiveColor(){
		return inactiveColor;
	}
}

package edu.chalmers.brawlbuddies.view.menu;

import java.awt.Dimension;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import edu.chalmers.brawlbuddies.model.Position;

public class MultiChoiceMenuItem extends SimpleMenuItem {
	
	private String name;
	
	private String[] values;
	private boolean[] enabled;
	private int valueIndex = 0;
	
	private Polygon arrowLeft;
	private Polygon arrowRight;
	private float arrowLength = 0;
	
	public MultiChoiceMenuItem(String name, String[] values, int vertPos){
		this(name, values, vertPos, new Dimension(500, 75));
	}

	public MultiChoiceMenuItem(String name, String[] values, int vertPos, Dimension size) {
		super((values.length>0?values[0]:""), vertPos, size);
		
		this.name = name;
		this.values = values;
		this.enabled = new boolean[this.values.length];
		for(int i = 0; i<this.enabled.length; i++){
			this.enabled[i] = true;
		}
		
		initArrows();
	}
	
	private void initArrows(){
		Dimension size = this.getSize();
		arrowLength = (float)size.getHeight()/4;

		arrowLeft = new Polygon();
		arrowLeft.addPoint(16, arrowLength*2);
		arrowLeft.addPoint(16+arrowLength*2, arrowLength/2*3);
		arrowLeft.addPoint(16+arrowLength*2, arrowLength/2*5);
		
		arrowRight = new Polygon();
		arrowRight.addPoint(arrowLength*2, arrowLength*2);
		arrowRight.addPoint(0, arrowLength/2*3);
		arrowRight.addPoint(0, arrowLength/2*5);
	}
	
	public void enable(String name, boolean enable){
		for(int i = 0; i<values.length; i++){
			if(name.equals(values[i])){
				enabled[i] = enable;
			}
		}
	}
	
	public boolean isEnabled(String name){
		for(int i = 0; i<values.length; i++){
			if(name.equals(values[i])){
				return isEnabled(i);
			}
		}
		
		return false;
	}
	
	private boolean isEnabled(int index){
		return index>=0 && index<enabled.length && enabled[index];
	}
	
	public void nextItem(){
		setItem(valueIndex+1);
	}
	
	public void prevItem(){
		setItem(valueIndex-1);
	}
	
	public void setItem(String value){
		if(value != null){
			for(int i = 0; i<values.length; i++){
				if(value.equals(values[i])){
					setItem(i);
				}
			}
		}
	}
	
	private void setItem(int index){
		if(index>=0 && index<values.length){
			this.valueIndex = index;
			this.setValue(values[valueIndex]);
		}
	}
	
	private String getValueContent(){
		return super.getValue();
	}
	
	@Override
	public String getValue(){
		if(!isEnabled(this.valueIndex)){
			return null;
		}else{
			return super.getValue();
		}
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean isWithinRight(float x, float y){
		return this.isWithin(x, y) && x>this.getPosition(null).getX()+this.getSize().getWidth()/2;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g){
		//Draw square around
		super.renderSelection(gc, g);
		
		//Draw value content
		String val = (this.isEnabled(this.valueIndex)?"":"[Disabled] ")+(this.getValueContent()==null?"":this.getValueContent());
		int stringWidth = g.getFont().getWidth(val);
		int stringHeight = g.getFont().getHeight(val);
		g.setColor(this.isEnabled(this.valueIndex)?Color.white:Color.gray);
		g.drawString(val, (gc.getWidth()-stringWidth)/2, (int)(this.getPosition(gc).getY()+(getSize().getHeight()-stringHeight)/2));
		
		//Update arrow positions
		Position pos = this.getPosition(gc);
		arrowLeft.setLocation(pos.getX(), pos.getY());
		arrowRight.setLocation((float)(pos.getX()+this.getSize().getWidth()-this.arrowLength-34), pos.getY());
		
		//Draw name and arrows
		g.setColor(this.isActive()?Color.white:Color.gray);
		g.drawString(name, pos.getX()-g.getFont().getWidth(name)-50, (float)(pos.getY()+(getSize().getHeight()-g.getFont().getHeight(name))/2));
		if(this.valueIndex>0){
			g.draw(arrowLeft);
		}
		if(this.valueIndex<this.values.length-1){
			g.draw(arrowRight);
		}
	}

}

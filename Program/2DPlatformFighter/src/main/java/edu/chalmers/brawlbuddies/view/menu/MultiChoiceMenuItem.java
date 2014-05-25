package edu.chalmers.brawlbuddies.view.menu;

import java.awt.Dimension;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import edu.chalmers.brawlbuddies.util.FontCreator;
/**
 * A menu item that have multiple choice
 * @author Matz Larsson
 *
 */
public class MultiChoiceMenuItem extends SimpleMenuItem {
	
	private String name;
	private boolean showName;
	
	private List<MultiChoiceOption> values;
	private boolean[] enabled;
	private int valueIndex = 0;
	
	private Polygon arrowLeft;
	private Polygon arrowRight;
	private float arrowLength = 0;
	
	/**
	 * Creates a new MultiChoiceMenuItem with a item name, name , values and vertical position
	 * @param itemName - the item name
	 * @param name - the name to be shown
	 * @param values - the values
	 * @param vertPos - the vertical position
	 */
	public MultiChoiceMenuItem(String itemName, String name, List<MultiChoiceOption> values, int vertPos){
		this(itemName, name, values, vertPos, new Dimension(500, 75));
	}
	/**
	 * Creates a new MultiChoiceMenuItem with a item name, name, values and position
	 * @param itemName - the item name
	 * @param name - the name to be shown
	 * @param values - the values
	 * @param pos - the position
	 */
	public MultiChoiceMenuItem(String itemName, String name, List<MultiChoiceOption> values, Position pos){
		this(itemName, name, values, pos, new Dimension(500, 75));
	}
	/**
	 * Creates a new MultiChoiceMenuItem with a item name, name, values, vertical position
	 * size
	 * @param itemName
	 * @param name
	 * @param values
	 * @param vertPos
	 * @param size
	 */
	public MultiChoiceMenuItem(String itemName, String name, List<MultiChoiceOption> values, int vertPos, Dimension size){
		this(itemName, name, values, new Position(-1, vertPos), size);
	}
	/**
	 * Creates a new MultiChoiceMenutItem with a item name, name, values, position and size
	 * @param itemName
	 * @param name
	 * @param values
	 * @param pos
	 * @param size
	 */
	public MultiChoiceMenuItem(String itemName, String name, List<MultiChoiceOption> values, Position pos, Dimension size) {
		this(itemName, name, values, pos, size, true);
	}
	/**
	 * Creates a new MultiChoiceMenuItem with a item name, name, values, position, 
	 * size and boolean to decide if to show name.
	 * @param itemName
	 * @param name
	 * @param values
	 * @param pos
	 * @param size
	 * @param showName 
	 */
	public MultiChoiceMenuItem(String itemName, String name, List<MultiChoiceOption> values, Position pos, Dimension size, boolean showName) {
		super(itemName, (values.size()>0?values.get(0).getValue():""), pos, size);
		
		this.name = name;
		this.values = values;
		this.showName = showName;
		this.enabled = new boolean[this.values.size()];
		for(int i = 0; i<this.enabled.length; i++){
			this.enabled[i] = true;
		}
		
		initArrows();
	}
	/**
	 * 
	 */
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
		for(int i = 0; i<values.size(); i++){
			if(name.equals(values.get(i).getCodeValue())){
				enabled[i] = enable;
			}
		}
		this.setError(!isEnabled(valueIndex));
	}
	public boolean isEnabled(String name){
		for(int i = 0; i<values.size(); i++){
			if(name.equals(values.get(i).getCodeValue())){
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
			for(int i = 0; i<values.size(); i++){
				if(value.equals(values.get(i).getCodeValue())){
					setItem(i);
				}
			}
		}
	}
	private void setItem(int index){
		if(index>=0 && index<values.size()){
			this.valueIndex = index;
			this.setValue(values.get(valueIndex).getValue());
			this.setError(!isEnabled(valueIndex));
		}
	}
	
	private String getTextValue(){
		return super.getValue();
	}
	
	public List<MultiChoiceOption> getOptions(){
		return this.values;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getValue(){
		if(!isEnabled(this.valueIndex)){
			return null;
		}else{
			return values.get(valueIndex).getCodeValue();
		}
	}
	public String getName(){
		return this.name;
	}
	public boolean isWithinRight(float x, float y){
		return this.isWithin(x, y) && x>this.getRealPosition(null).getX()+this.getRealSize().getWidth()/2;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer gc, Graphics g){
		//Draw square around
		super.renderSelection(gc, g);
		g.setFont(FontCreator.getFont(FontCreator.MEDIUM));
		
		//Update arrow positions
		Position pos = this.getPosition(gc);
		Dimension size = this.getSize();
		arrowLeft.setLocation(pos.getX(), pos.getY());
		arrowRight.setLocation((float)(pos.getX()+size.getWidth()-this.arrowLength-34), pos.getY());
		
		//Draw value content
		String val = (this.isEnabled(this.valueIndex)?"":"[Disabled] ")+(this.getTextValue()==null?"":this.getTextValue());
		int stringWidth = g.getFont().getWidth(val);
		int stringHeight = g.getFont().getHeight(val);
		g.setColor(this.isEnabled(this.valueIndex)&&this.isActive()?getActiveColor():getInactiveColor());
		g.drawString(val, (int)(pos.getX()+(size.getWidth()-stringWidth)/2), (int)(pos.getY()+(size.getHeight()-stringHeight)/2));
		
		
		//Draw name and arrows
		if(showName){
			g.setColor(this.isActive()?getActiveColor():getInactiveColor());
			g.drawString(name, pos.getX()-g.getFont().getWidth(name)-50, (float)(pos.getY()+(size.getHeight()-g.getFont().getHeight(name))/2));
		}
		if(this.valueIndex>0){
			g.draw(arrowLeft);
		}
		if(this.valueIndex<this.values.size()-1){
			g.draw(arrowRight);
		}
	}

}

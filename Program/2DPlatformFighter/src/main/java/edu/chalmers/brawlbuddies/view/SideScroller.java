package edu.chalmers.brawlbuddies.view;

import java.awt.Dimension;
import java.util.Map;
import java.util.TreeMap;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.eventbus.IEventBusSubscriber;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.ICharacter;

public class SideScroller implements IEventBusSubscriber{
	
	private int viewportX, viewportY;
	private int offsetX, offsetY;
	private float scale;
	private double ratio = 1.0f;
	private Dimension viewportSize;
	private Dimension minimumSize;
	private Dimension resolution;
	private Dimension mapSize;
	
	private Map<Integer, Position> positions;

	public SideScroller(Dimension resolution, Dimension mapSize, int offsetX, int offsetY, double ratio){
		EventBus.getInstance().addSubscriber(this);
		this.positions = new TreeMap<Integer, Position>();

		this.ratio = ratio;

		if(this.resolution != null){
			this.resolution = new Dimension(resolution);
			this.minimumSize = new Dimension((int)(resolution.getWidth()/ratio), (int)(resolution.getHeight()/ratio));
			this.viewportSize = new Dimension(resolution);
		}
		if(this.mapSize != null){
			this.mapSize = new Dimension(mapSize);
		}
		
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.scale = 1.0f;
	}
	
	public int getX(){
		return -this.viewportX;
	}
	
	public int getY(){
		return -this.viewportY;
	}
	
	public float getScale(){
		return this.scale;
	}
	
	public int getTopX(){
		if(this.mapSize.getWidth() == this.viewportSize.getWidth()){
			return this.viewportX;
		}else{
			return 0;
		}
	}
	
	public int getTopY(){
		if(this.mapSize.getHeight() == this.viewportSize.getHeight()){
			return this.viewportY;
		}else{
			return 0;
		}
	}
	
	public void setMapSize(int x, int y){
		if(this.mapSize != null){
			this.mapSize.setSize(x, y);
		}else{
			this.mapSize = new Dimension(x, y);
		}
	}
	
	public void setResolution(int x, int y){
		if(this.resolution != null){
			this.resolution.setSize(x, y);
		}else{
			this.resolution = new Dimension(x, y);
		}
		this.viewportSize = new Dimension(this.resolution);
		this.minimumSize = new Dimension((int)(resolution.getWidth()/ratio), (int)(resolution.getHeight()/ratio));
	}
	
	private void updateData(ICharacter character){
		int uniqueID = character.getID();
		this.positions.remove(uniqueID);
		this.positions.put(uniqueID, character.getCenterPosition());
	}
	
	private void update(){
		if(this.resolution != null && this.mapSize != null){
			//Initiate variables
			int minX = 0, minY = 0;
			int maxX = -1, maxY = -1;
			//Collect data from positions
			for(Position pos : this.positions.values()){
				if(minX <= 0 || pos.getX()<minX){
					minX = (int)Math.round(pos.getX());
				}
				if(minY <= 0 || pos.getY()<minY){
					minY = (int)Math.round(pos.getY());
				}
				if(pos.getX()>maxX){
					maxX = (int)Math.round(pos.getX());
				}
				if(pos.getY()>maxY){
					maxY = (int)Math.round(pos.getY());
				}
			}
			
			//Fix offsets
			minX -= offsetX;
			minY -= offsetY;
			maxX += offsetX;
			maxY += offsetY;
			
			//Calculate size of viewport
			setupViewportSize(minX, minY, maxX, maxY);
			
			//Set up scale
			this.scale = Math.min((float)(resolution.getWidth()/viewportSize.getWidth()), (float)(resolution.getHeight()/viewportSize.getHeight()));
	
			//Set up x position
			if(minX<=0){
				this.viewportX = 0;
			}else{
				if(this.viewportSize.getWidth() == this.mapSize.getWidth()){
					this.viewportX = -(int)(resolution.getWidth()-viewportSize.getWidth()*scale)/2+5;
				}else{
					this.viewportX = minX;
					this.viewportX = (int)Math.min(Math.max(0, this.viewportX), mapSize.getWidth()-viewportSize.getWidth());
				}
			}
			//Set up y position
			if(minY<=0){
				this.viewportY = 0;
			}else{
				if(this.viewportSize.getHeight() == this.mapSize.getHeight()){
					this.viewportY = -(int)(resolution.getHeight()-viewportSize.getHeight()*scale)/2+5;
				}else{
					this.viewportY = minY;
					this.viewportY = (int)Math.min(Math.max(0, this.viewportY), mapSize.getHeight()-viewportSize.getHeight());
				}
			}
		}
	}
	
	private void setupViewportSize(int minX, int minY, int maxX, int maxY){
		//Calculate center point between characters
		double tmpSizeX = maxX-minX;
		double tmpSizeY = maxY-minY;
		
		//Make sure the scale is the same against resolution.
		if(tmpSizeX/this.resolution.getWidth()>tmpSizeY/this.resolution.getHeight()){
			tmpSizeY = tmpSizeX/this.resolution.getWidth()*this.resolution.getHeight();
		}else{
			tmpSizeX = tmpSizeY/this.resolution.getHeight()*this.resolution.getWidth();
		}
		
		//Make sure the window does not zoom to much.
		if(tmpSizeX<this.minimumSize.getWidth() || tmpSizeY<this.minimumSize.getHeight()){
			tmpSizeX = this.minimumSize.getWidth();
			tmpSizeY = this.minimumSize.getHeight();
		}
		
		//Make sure the viewport does not try to extend more than the map size.
		if(tmpSizeX>this.mapSize.getWidth()){
			tmpSizeX = this.mapSize.getWidth();
		}
		if(tmpSizeY>this.mapSize.getHeight()){
			tmpSizeY = this.mapSize.getHeight();
		}
		
		//Set the viewport size variable.
		this.viewportSize.setSize(tmpSizeX, tmpSizeY);
	}

	public void eventPerformed(EventBusEvent event) {
		if(event.getRecipient() instanceof ICharacter){
			updateData((ICharacter)event.getRecipient());
			update();
		}
	}
}

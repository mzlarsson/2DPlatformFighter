package edu.chalmers.brawlbuddies.view;

import java.awt.Dimension;
import java.util.Collection;
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
	private Dimension viewportSize;
	private Dimension minimumSize;
	private Dimension resolution;
	private Dimension mapSize;
	
	private Map<Integer, Position> positions;

	public SideScroller(Dimension resolution, Dimension mapSize, int offsetX, int offsetY, double ratio){
		EventBus.getInstance().addSubscriber(this);
		this.positions = new TreeMap<Integer, Position>();

		this.resolution = new Dimension(resolution);
		this.minimumSize = new Dimension((int)(resolution.getWidth()/ratio), (int)(resolution.getHeight()/ratio));
		this.viewportSize = new Dimension(resolution);
		this.mapSize = new Dimension(mapSize);
		
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
	
	public int getWidth(){
		return (int)this.viewportSize.getWidth();
	}
	
	public int getHeight(){
		return (int)this.viewportSize.getHeight();
	}

	public int getScreenWidth(){
		return (int)this.resolution.getWidth();
	}
	
	public int getScreenHeight(){
		return (int)this.resolution.getHeight();
	}
	
	public float getScale(){
		return this.scale;
	}
	
	public Collection<Position> getPos(){
		return this.positions.values();
	}
	
	private void updateData(ICharacter character){
		int uniqueID = character.getID();
		this.positions.remove(uniqueID);
		this.positions.put(uniqueID, character.getCenterPosition());
	}
	
	private void update(){
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
		
		//Calculate size of viewport
		setupViewportSize(minX, minY, maxX, maxY);
		
		//Set up scale
		float mapScale = (float)(mapSize.getWidth()/viewportSize.getWidth());
		this.scale = (float)(resolution.getWidth()/viewportSize.getWidth());// * mapScale;

		//Set up position
		this.viewportX = (int)((minX+maxX)/2 - (resolution.getWidth()/2 + this.offsetX)/viewportSize.getWidth());
		this.viewportX = (int)Math.min(Math.max(0, this.viewportX), mapSize.getWidth()-viewportSize.getWidth());
		this.viewportY = (int)((minY+maxY)/2 - (resolution.getHeight()/2 + this.offsetY)/viewportSize.getHeight());
		this.viewportY = (int)Math.min(Math.max(0, this.viewportY), mapSize.getHeight()-viewportSize.getHeight());
	}
	
	private void setupViewportSize(int minX, int minY, int maxX, int maxY){
		double tmpSizeX = maxX-minX+2*this.offsetX;
		double tmpSizeY = maxY-minY+2*this.offsetY;
		if(tmpSizeX/this.mapSize.getWidth()>tmpSizeY/this.mapSize.getHeight()){
			tmpSizeY = tmpSizeX/this.mapSize.getWidth()*this.mapSize.getHeight();
		}else{
			tmpSizeX = tmpSizeY/this.mapSize.getHeight()*this.mapSize.getWidth();
		}
		
		if(tmpSizeX<this.minimumSize.getWidth() || tmpSizeY<this.minimumSize.getHeight()){
			tmpSizeX = this.minimumSize.getWidth();
			tmpSizeY = this.minimumSize.getHeight();
		}
		if(tmpSizeX>this.mapSize.getWidth() || tmpSizeY>this.mapSize.getHeight()){
			tmpSizeX = this.mapSize.getWidth();
			tmpSizeY = this.mapSize.getHeight();
		}
		
		this.viewportSize.setSize(tmpSizeX, tmpSizeY);
	}

	public void eventPerformed(EventBusEvent event) {
		if(event.getRecipient() instanceof ICharacter){
			updateData((ICharacter)event.getRecipient());
			update();
		}
	}
}

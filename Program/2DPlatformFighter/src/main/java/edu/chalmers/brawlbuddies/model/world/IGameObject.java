package edu.chalmers.brawlbuddies.model.world;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;

public interface IGameObject{

	public Velocity getTotalVelocity();
	public Position getPosition();
	public void setPosition(Position pos);
	public Position getCenterPosition();
	public void setCenterPosition(Position pos);
	public Shape getShape();
	public void setShape(Shape shape);

	public Position update(int delta);
	public boolean isDestroyed();
	public void onCollision(IGameObject object, Movement.Alignment alignment);
	
	public int getID();
	public int getTypeID();
	
}

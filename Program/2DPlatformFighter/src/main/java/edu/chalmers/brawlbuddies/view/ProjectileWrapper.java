package edu.chalmers.brawlbuddies.view;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.world.IGameObject;
import edu.chalmers.brawlbuddies.model.world.IProjectile;
import edu.chalmers.brawlbuddies.model.world.Movement.Alignment;

public class ProjectileWrapper implements IWrapper, IProjectile {
	
	public int getTypeID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getUniqeID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Velocity getTotalVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	public Position getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPosition(Position pos) {
		// TODO Auto-generated method stub
		
	}

	public Position getCenterPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCenterPosition(Position pos) {
		// TODO Auto-generated method stub
		
	}

	public Shape getShape() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setShape(Shape shape) {
		// TODO Auto-generated method stub
		
	}

	public void transform(Transform transform) {
		// TODO Auto-generated method stub
		
	}

	public Position update(int delta) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void onCollision(IGameObject object, Alignment alignment) {
		// TODO Auto-generated method stub
		
	}

	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getTheta() {
		// TODO Auto-generated method stub
		return 0;
	}

}

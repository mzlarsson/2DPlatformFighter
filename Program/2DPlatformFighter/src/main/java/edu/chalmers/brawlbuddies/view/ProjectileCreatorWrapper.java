package edu.chalmers.brawlbuddies.view;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.world.IProjectile;
import edu.chalmers.brawlbuddies.model.world.IProjectileCreator;
import edu.chalmers.brawlbuddies.model.world.ProjectileCreator;

public class ProjectileCreatorWrapper implements IWrapper, IProjectileCreator {

	ProjectileCreator pc;
	
	public ProjectileCreatorWrapper(ProjectileCreator pc) {
		this.pc = pc;
		EventBus.getInstance().fireEvent(new EventBusEvent("createAnimation", this, null));
	}

	public int getTypeID() {
		return pc.getTypeID();
	}

	public int getUniqeID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setCreatorID(int a) {
		pc.setCreatorID(a);
	}

	public IProjectile fire(Position pos, Aim aim) {
		return new ProjectileWrapper(pc.fire(pos, aim));
	}
}

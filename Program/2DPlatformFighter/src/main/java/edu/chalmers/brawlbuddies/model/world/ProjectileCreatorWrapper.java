package edu.chalmers.brawlbuddies.model.world;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.IWrapper;
import edu.chalmers.brawlbuddies.model.Position;
/**
 * A wrapper class for ProjectileCreator
 * @author Lisa Lipkin
 * @version 1.0
 *
 */
public class ProjectileCreatorWrapper implements IWrapper, IProjectileCreator {

	ProjectileCreator pc;
	/**
	 * Creates a new ProjectileCreatorWrapper with a ProjectileCreator.
	 * @param pc - the projectilecreator the wrapper will contain
	 */
	public ProjectileCreatorWrapper(ProjectileCreator pc) {
		this.pc = pc;
		EventBus.getInstance().fireEvent(new EventBusEvent("createAnimation", this, null));
	}
	/**
	 * {@inheritDoc}
	 */
	public int getTypeID() {
		return pc.getTypeID();
	}
	/**
	 * {@inheritDoc}
	 */
	public int getUniqeID() {
		return 0;
	}
	/**
	 * {@inheritDoc}
	 */
	public void setCreatorID(int a) {
		pc.setCreatorID(a);
	}
	/**
	 * {@inheritDoc}
	 */
	public IProjectile fire(Position pos, Aim aim) {
		return new ProjectileWrapper(pc.fire(pos, aim));
	}
}

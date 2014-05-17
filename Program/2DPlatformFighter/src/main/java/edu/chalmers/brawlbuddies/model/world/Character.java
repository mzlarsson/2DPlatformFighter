package edu.chalmers.brawlbuddies.model.world;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Shape;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.GameListener;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.skills.ISkill;
import edu.chalmers.brawlbuddies.model.statuseffects.IStatusEffect;
import edu.chalmers.brawlbuddies.model.statuseffects.StatusEffectList;
import edu.chalmers.brawlbuddies.model.world.Movement.Alignment;

/**
 * A class to represent a player-controlled character.
 * 
 * @author Patrik Haar
 * @version 0.4
 * @revised David Gustafsson
 * @revised Matz Larsson
 */
@XStreamAlias("character")
public class Character extends GameObject implements ICharacter {

	private int typeID;
	private StatusEffectList statusEffectList = new StatusEffectList();

	private String name;
	private String bio;
	private IHealth health;

	private ISkill[] skills;
	private Position projOffset;

	private Aim aim = new Aim(1, 0);

	private List<GameListener> listeners;

	/**
	 * Creates a Character.
	 * 
	 * @param cd
	 *            The data from which the character gets its attributes from.
	 * @param player
	 *            The Player controlling the character.
	 */
	public Character(Shape shape, int id, Position projOffset) {
		super(new JumpMovement(), shape);
		this.typeID = id;
		this.projOffset = projOffset;
		this.listeners = new ArrayList<GameListener>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * Set the skills for the character
	 * 
	 * @param skills
	 */
	public void setSkills(ISkill[] skills) {
		this.skills = skills;
	}

	/**
	 * Activate a Skill in the skills array with the given positionNbr in the
	 * array if it exists
	 * 
	 * @param positionNbr
	 */
	public void activateSkill(int positionNbr) {
		if (skills != null && (positionNbr > -1)
				&& (positionNbr < skills.length) && skills[positionNbr] != null
				&& statusEffectList.canUseSkill()) {
			skills[positionNbr].activate(this);
		}
	}

	@Override
	public void setMovement(Movement movement) {
		if (!(movement instanceof JumpMovement)) {
			throw new IllegalArgumentException("Only JumpMovements accepted");
		} else {
			super.setMovement(movement);
		}
	}

	@Override
	public JumpMovement getMovement() {
		return (JumpMovement) super.getMovement();
	}

	public void setBaseSpeed(Velocity baseSpeed) {
		this.getMovement().setBaseSpeed(baseSpeed);
	}

	public void setBaseJumpSpeed(float baseJump) {
		this.getMovement().setBaseJumpSpeed(baseJump);
	}

	public void setMaxJumps(int maxJumps) {
		this.getMovement().setMaxJumps(maxJumps);
	}

	/**
	 * Updates the velocity and position of the Character and returns the new
	 * position.
	 * 
	 * @param delta
	 *            The time passed since last update in milliseconds.
	 * @return The position after the movement.
	 */
	public Position update(int delta) {
		statusEffectList.update(delta, (ICharacter) this);
		updateCooldowns(delta);
		return this.getMovement().nextPosition(this.getCenterPosition(), delta);
	}

	/**
	 * Makes the character move to the left/right/up/down depending on the
	 * Direction.
	 */
	public void move(Direction dir) {
		if (statusEffectList.canMove()) {
			this.getMovement().move(dir);
		} else {
			this.getMovement().move(Direction.NONE);
			this.cancelJump();
		}
	}

	/**
	 * Updates the cooldown of the skills with the time passed.
	 * 
	 * @param delta
	 *            Time past in milliseconds.
	 */
	private void updateCooldowns(int delta) {
		for (ISkill s : skills) {
			s.update(delta);
		}
	}

	/**
	 * Makes the character jump if able.
	 */
	public void makeJump() {
		if (statusEffectList.canJump()) {
			this.getMovement().jump();
		} else {
			this.cancelJump();
		}
	}

	/**
	 * Cancel a jump during the upwards movement.
	 */
	public void cancelJump() {
		this.getMovement().cancelJump();
	}

	public Position getProjFirePos() {
		return new Position(this.getCenterPosition().add(
				this.aim.getX() < 0 ? -projOffset.getX() : projOffset.getX(),
				projOffset.getY()));
	}

	public Aim getAim() {
		return this.aim;
	}

	public void setAim(Position aimPosition, boolean isRelative) {
		if (isRelative) {
			this.aim.set(aimPosition);
		} else {
			this.aim.set(aimPosition.subtract(this.getCenterPosition()));
		}
	}

	public void setMaxHealth(float a) {
		health.setMaxHealth(a);
	}

	public void setHealth(IHealth hlth) {
		this.health = hlth;
	}

	public void setHealth(float a) {
		this.health.setHealth(a);
	}

	public void takeDamage(float a) {
		health.takeDamage(a);
		if (isDead()) {
			characterKilled();
		}
	}

	public void heal(float a) {
		health.heal(a);
	}

	public float getHealth() {
		return health.getHealth();
	}

	public float getMaxHealth() {
		return health.getMaxHealth();
	}

	public void restoreHealth() {
		health.restoreHealth();
	}

	public boolean isDead() {
		return health.isDead();
	}

	public void push(Velocity v) {
		Movement mov = getMovement();
		mov.setOuterSpeed(mov.getOuterSpeed().getX() + v.getX(), mov
				.getOuterSpeed().getY() + v.getY());
	}

	@Override
	public int getTypeID() {
		return typeID;
	}

	public void onCollision(IGameObject object, Alignment alignment) {
		if (object == null || object instanceof Impassible) {
			this.getMovement().resetSpeed(alignment);
		}
	}

	public boolean isDestroyed() {
		return this.isDead();
	}

	public void applyStatusEffect(IStatusEffect effect) {
		statusEffectList.add(effect);

	}

	public void addScale(float scale) {
		super.getMovement().addScale(scale);

	}

	public void removeScale(float scale) {
		super.getMovement().removeScale(scale);
	}

	public void restoreScale() {
		super.getMovement().restoreScale();

	}

	public void resetGravity() {
		super.getMovement().resetGravity(Movement.Alignment.BOTH);
	}

	public void addSpeed(Velocity velocity) {
		super.getMovement().addSpeed(velocity);

	}

	public void removeSpeed(Velocity velocity) {
		super.getMovement().removeSpeed(velocity);
	}

	public Direction getDirection() {
		return this.getMovement().getDirection();
	}

	public boolean isInAir() {
		return this.getMovement().isInAir();
	}

	public void reset() {
		this.restoreHealth();
		this.getMovement().resetSpeed(Alignment.BOTH);
		this.restoreScale();
		this.statusEffectList.reset();
	}

	/**
	 * This method will send an event to all listeners that this character has
	 * been killed.
	 */
	private void characterKilled() {
		for (GameListener gl : listeners) {
			gl.gameEventPerformed("characterKilled", this);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void addGameListener(GameListener gl) {
		listeners.add(gl);
		gl.gameEventPerformed("characterAdded", this);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeGameListener(GameListener gl) {
		listeners.remove(gl);
	}
}

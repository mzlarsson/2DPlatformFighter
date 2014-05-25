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
 * @revised David Gustafsson
 * @revised Matz Larsson
 * @version 1.0
 */
@XStreamAlias("character")
public class Character extends GameObject implements ICharacter {

	private int typeID;
	private StatusEffectList statusEffectList = new StatusEffectList();

	private IHealth health;

	private ISkill[] skills;
	private Position projOffset;

	private Aim aim = new Aim(1, 0);

	private List<GameListener> listeners;

	/**
	 * Creates a Character with a shape, ID and projectile offset.
	 * The shape will decide the hitbox of the character.
	 * The ID will decide which ID the character will have.
	 * The Projectile offset adds a offset to all projectile a character fires.
	 * @param shape- the shape of the characters hitbox
	 * @param id - the ID of the Character
	 * @param projOffset - the offset of the character that will be added to a character projectiles
	 */
	public Character(Shape shape, int id, Position projOffset) {
		super(new JumpMovement(), shape);
		this.typeID = id;
		this.projOffset = projOffset;
		this.listeners = new ArrayList<GameListener>();
	}
	
	/**
	 * Set the skills for the character
	 * @param skills - the skill to set for the character
	 */
	public void setSkills(ISkill[] skills) {
		this.skills = skills;
	}

	/**
	 * Set the new Maximal amount of health a character have.
	 * @param maxHealth - the new amount of max health for the character.
	 */
	public void setMaxHealth(float maxHealth) {
		health.setMaxHealth(maxHealth);
	}

	/**
	 * Set a new Health object to a character
	 * @param health- the new health object of the character
	 */
	public void setHealth(IHealth health) {
		this.health = health;
	}

	/**
	 * Set health of a Character
	 * @param newHealth - the new health of a character
	 */
	public void setHealth(float newHealth) {
		this.health.setHealth(newHealth);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void setMovement(Movement movement) {
		if (!(movement instanceof JumpMovement)) {
			throw new IllegalArgumentException("Only JumpMovements accepted");
		} else {
			super.setMovement(movement);
		}
	}

	/**
	 * Set the base speed of the character
	 * @param baseSpeed - the new BaseSpeed of the character
	 */
	public void setBaseSpeed(Velocity baseSpeed) {
		this.getMovement().setBaseSpeed(baseSpeed);
	}
	
	/**
	 * Set the base jump speed of the character
	 * @param baseJump - the new base jump speed of the character
	 */
	public void setBaseJumpSpeed(float baseJump) {
		this.getMovement().setBaseJumpSpeed(baseJump);
	}
	
	/**
	 * Set the maximum amount of jumps a character can do in succession
	 * @param maxJumps - the maximum amount of jumps  
	 */
	public void setMaxJumps(int maxJumps) {
		this.getMovement().setMaxJumps(maxJumps);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setAim(Position aimPosition, boolean isRelative) {
		if (isRelative) {
			this.aim.set(aimPosition);
		} else {
			Position tmpPos = (Position)aimPosition.subtract(this.getCenterPosition()).getNormalized().scale(100);
			this.aim.set(tmpPos.getX(), tmpPos.getY());
		}
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public JumpMovement getMovement() {
		return (JumpMovement) super.getMovement();
	}

	/**
	 * {@inheritDoc}
	 */
	public Position getProjFirePos() {
		return new Position(this.getCenterPosition().add(
				this.aim.getX() < 0 ? -projOffset.getX() : projOffset.getX(),
				projOffset.getY()));
	}

	/**
	 * {@inheritDoc}
	 */
	public Aim getAim() {
		return this.aim;
	}
	
	/**
	 * Get the health of the character
	 * @return float - the health of the character
	 */
	public float getHealth() {
		return health.getHealth();
	}
	
	/**
	 * Get the max health of the character
	 * @return float - the max health of the Character
	 */
	public float getMaxHealth() {
		return health.getMaxHealth();
	}

	/**
	 * {@inheritDoc}
	 */
	public int getTypeID() {
		return typeID;
	}

	/**
	 * Activate a Skill in the skills on a given position in the
	 * if there exist is a skill on the given position. 
	 * 
	 * @param positionNbr- a number signifying a position in skills.
	 */
	public void activateSkill(int positionNbr) {
		if (skills != null && (positionNbr > -1)
				&& (positionNbr < skills.length) && skills[positionNbr] != null
				&& statusEffectList.canUseSkill()) {
			skills[positionNbr].activate(this);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Position update(int delta) {
		statusEffectList.update(delta, (ICharacter) this);
		updateCooldowns(delta);
		return this.getMovement().nextPosition(this.getCenterPosition(), delta);
	}

	/**
	 * {@inheritDoc}
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
	 * @param delta - The time past in milliseconds.
	 */
	private void updateCooldowns(int delta) {
		for (ISkill s : skills) {
			s.update(delta);
		}
	}

	/**
	 * {@inheritDoc}
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
	
	/**
	 * {@inheritDoc}
	 */
	public void takeDamage(float a) {
		a = statusEffectList.calculateDamage(a);
		health.takeDamage(a);
		if (isDestroyed()) {
			characterKilled();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void heal(float a) {
		health.heal(a);
	}

	/**
	 * {@inheritDoc}
	 */
	public void restoreHealth() {
		health.restoreHealth();
	}
	
	

	/**
	 * {@inheritDoc}
	 */
	public void push(Velocity v) {
		Movement mov = getMovement();
		mov.setOuterSpeed(mov.getOuterSpeed().getX() + v.getX(), mov
				.getOuterSpeed().getY() + v.getY());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void onCollision(IGameObject object, Alignment alignment) {
		if (object == null || object instanceof Impassible) {
			this.getMovement().resetSpeed(alignment);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isDestroyed() {
		return health.isDead();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void applyStatusEffect(IStatusEffect effect) {
		statusEffectList.add(effect);

	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addScale(float scale) {
		super.getMovement().addScale(scale);

	}
	
	/**
	 * {@inheritDoc}
	 */
	public void removeScale(float scale) {
		super.getMovement().removeScale(scale);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void restoreScale() {
		super.getMovement().restoreScale();

	}
	
	/**
	 * {@inheritDoc}
	 */
	public void resetGravity() {
		super.getMovement().resetGravity(Movement.Alignment.BOTH);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addSpeed(Velocity velocity) {
		super.getMovement().addSpeed(velocity);

	}
	
	/**
	 * {@inheritDoc}
	 */
	public void removeSpeed(Velocity velocity) {
		super.getMovement().removeSpeed(velocity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Direction getDirection() {
		return this.getMovement().getDirection();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isInAir() {
		return this.getMovement().isInAir();
	}
	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		this.restoreHealth();
		this.getMovement().reset();
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

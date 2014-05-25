package edu.chalmers.brawlbuddies.model.world;

import org.newdawn.slick.geom.Shape;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.GameListener;
import edu.chalmers.brawlbuddies.model.IWrapper;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.statuseffects.IStatusEffect;
import edu.chalmers.brawlbuddies.model.world.Movement.Alignment;

/**
 * A wrapper class for character that send events to the view when relevant changes occurs.
 * @author Lisa Lipkin
 * @revised Patrik Haar
 * @version 1.0
 *
 */
public class CharacterWrapper implements IWrapper, ICharacter {
	private Character character;
	
	/**
	 * Creates a new CharacterWrapper with a character
	 * @param character - the character the wrapper contains
	 */
	public CharacterWrapper(Character character) {
		this.character = character;
		EventBus.getInstance().fireEvent(new EventBusEvent("createHud", this, null));
		EventBus.getInstance().fireEvent(new EventBusEvent("createObject", this, null));
	}
	/**
	 * Creates a new CharacterWrapper with a character with a shape, ID and projectile offset
	 * @param shape - the shape of the character
	 * @param id - the ID of the character
	 * @param projOffset - the projectile offset of the character
	 */
	public CharacterWrapper(Shape shape, int id, Position projOffset) {
		this(new Character(shape, id, projOffset));
	}
	/**
	 * {@inheritDoc}
	 */
	public int getTypeID() {
		return character.getTypeID();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getUniqeID() {
		return character.getID();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public void heal(float a) {
		character.heal(a);

	}
	

	/**
	 * {@inheritDoc}
	 */
	public void takeDamage(float a) {
		character.takeDamage(a);

	}
	

	/**
	 * {@inheritDoc}
	 */
	public int getID() {
		return character.getID();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public Position getCenterPosition() {
		return character.getCenterPosition();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public Aim getAim() {
		return character.getAim();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public void push(Velocity v) {
		character.push(v);

	}
	

	/**
	 * {@inheritDoc}
	 */
	public Velocity getTotalVelocity() {
		return character.getTotalVelocity();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public Position getPosition() {
		return character.getPosition();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public void setPosition(Position pos) {
		character.setPosition(pos);
		EventBus.getInstance().fireEvent(new EventBusEvent("updateObject", this, null));

	}
	

	/**
	 * {@inheritDoc}
	 */
	public void setCenterPosition(Position pos) {
		character.setCenterPosition(pos);
		EventBus.getInstance().fireEvent(new EventBusEvent("updateObject", this, null));
	}
	

	/**
	 * {@inheritDoc}
	 */
	public Shape getShape() {
		return character.getShape();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public void setShape(Shape shape) {
		character.setShape(shape);

	}
	

	/**
	 * {@inheritDoc}
	 */
	public Position update(int delta) {
		return character.update(delta);
	}
	

	/**
	 * {@inheritDoc}
	 */
	public boolean isDestroyed() {
		return character.isDestroyed();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public void onCollision(IGameObject object, Alignment alignment) {
		character.onCollision(object, alignment);

	}
	

	/**
	 * {@inheritDoc}
	 */
	public void restoreHealth() {
		character.restoreHealth();

	}
	

	/**
	 * {@inheritDoc}
	 */
	public void move(Direction dir) {
		character.move(dir);
		EventBus eb = EventBus.getInstance();
		eb.fireEvent(new EventBusEvent("updateObject", this, null));

	}
	

	/**
	 * {@inheritDoc}
	 */
	public void makeJump() {
		character.makeJump();
		EventBus eb = EventBus.getInstance();
		eb.fireEvent(new EventBusEvent("updateObject", this, null));

	}
	

	/**
	 * {@inheritDoc}
	 */
	public void setAim(Position aimPosition, boolean isRelative) {
		character.setAim(aimPosition, isRelative);
		EventBus eb = EventBus.getInstance();
		eb.fireEvent(new EventBusEvent("updateObject", this, null));
	}
	

	/**
	 * {@inheritDoc}
	 */
	public void activateSkill(int skillIndex) {
		character.activateSkill(skillIndex);

	}
	

	/**
	 * {@inheritDoc}
	 */
	public Direction getDirection() {
		return character.getDirection();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public boolean isInAir() {
		return character.isInAir();
	}
	

	/**
	 * {@inheritDoc}
	 */
	public void applyStatusEffect(IStatusEffect copy) {
		character.applyStatusEffect(copy);

	}
	

	/**
	 * {@inheritDoc}
	 */
	public void addScale(float scale) {
		character.addScale(scale);

	}
	

	/**
	 * {@inheritDoc}
	 */
	public void removeScale(float scale) {
		character.removeScale(scale);

	}
	

	/**
	 * {@inheritDoc}
	 */
	public void restoreScale() {
		character.restoreScale();

	}
	

	/**
	 * {@inheritDoc}
	 */
	public void resetGravity() {
		character.resetGravity();

	}
	

	/**
	 * {@inheritDoc}
	 */
	public void addSpeed(Velocity velocity) {
		character.addSpeed(velocity);

	}
	

	/**
	 * {@inheritDoc}
	 */
	public void removeSpeed(Velocity velocity) {
		character.removeSpeed(velocity);

	}
	

	/**
	 * {@inheritDoc}
	 */
	public Position getProjFirePos() {
		return character.getProjFirePos();
	}
		

	/**
	 * {@inheritDoc}
	 */
	public void reset(){
		character.reset();
	}
		

	/**
	 * {@inheritDoc}
	 */
	public void addGameListener(GameListener gl) {
		character.addGameListener(gl);
	}
		

	/**
	 * {@inheritDoc}
	 */
	public void removeGameListener(GameListener gl) {
		character.removeGameListener(gl);
	}

}

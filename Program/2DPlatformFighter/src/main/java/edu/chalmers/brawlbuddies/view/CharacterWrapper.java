package edu.chalmers.brawlbuddies.view;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.statuseffects.IStatusEffect;
import edu.chalmers.brawlbuddies.model.world.ICharacter;
import edu.chalmers.brawlbuddies.model.world.IGameObject;
import edu.chalmers.brawlbuddies.model.world.Movement.Alignment;
import edu.chalmers.brawlbuddies.model.world.Character;

public class CharacterWrapper implements IWrapper, ICharacter {
	private Character character;

	public CharacterWrapper(Character character) {
		this.character = character;
		EventBus.getInstance().fireEvent(new EventBusEvent("createObject", this, null));
	}
	public CharacterWrapper(Shape shape, int id, Position projOffset) {
		this(new Character(shape, id, projOffset));
	}

	public int getTypeID() {
		return character.getTypeID();
	}

	public int getUniqeID() {
		return character.getID();
	}

	public void heal(float a) {
		character.heal(a);

	}

	public void takeDamage(float a) {
		character.takeDamage(a);

	}

	public int getID() {
		return character.getID();
	}

	public Position getCenterPosition() {
		return character.getCenterPosition();
	}

	public Aim getAim() {
		return character.getAim();
	}

	public void push(Velocity v) {
		character.push(v);

	}

	public Velocity getTotalVelocity() {
		return character.getTotalVelocity();
	}

	public Position getPosition() {
		return character.getPosition();
	}

	public void setPosition(Position pos) {
		character.setPosition(pos);
		EventBus.getInstance().fireEvent(new EventBusEvent("updateObject", this, null));

	}

	public void setCenterPosition(Position pos) {
		character.setCenterPosition(pos);
		EventBus.getInstance().fireEvent(new EventBusEvent("updateObject", this, null));
	}

	public Shape getShape() {
		return character.getShape();
	}

	public void setShape(Shape shape) {
		character.setShape(shape);

	}

	public void transform(Transform transform) {
		character.transform(transform);

	}

	public Position update(int delta) {
		return character.update(delta);
	}

	public boolean isDestroyed() {
		return character.isDestroyed();
	}
	public void onCollision(IGameObject object, Alignment alignment) {
		character.onCollision(object, alignment);

	}

	public void restoreHealth() {
		character.restoreHealth();

	}

	public void move(Direction dir) {
		character.move(dir);
		EventBus eb = EventBus.getInstance();
		eb.fireEvent(new EventBusEvent("updateObject", this, null));

	}

	public void makeJump() {
		character.makeJump();
		EventBus eb = EventBus.getInstance();
		eb.fireEvent(new EventBusEvent("updateObject", this, null));

	}

	public void setAim(Position aimPosition, boolean isRelative) {
		character.setAim(aimPosition, isRelative);
		EventBus eb = EventBus.getInstance();
		eb.fireEvent(new EventBusEvent("updateObject", this, null));
	}

	public void activateSkill(int skillIndex) {
		character.activateSkill(skillIndex);

	}

	public Direction getDirection() {
		return character.getDirection();
	}

	public boolean isInAir() {
		return character.isInAir();
	}

	public void applyStatusEffect(IStatusEffect copy) {
		character.applyStatusEffect(copy);

	}

	public void addScale(float scale) {
		character.addScale(scale);

	}

	public void removeScale(float scale) {
		character.removeScale(scale);

	}

	public void restoreScale() {
		character.restoreScale();

	}

	public void resetGravity() {
		character.resetGravity();

	}

	public void addSpeed(Velocity velocity) {
		character.addSpeed(velocity);

	}

	public void removeSpeed(Velocity velocity) {
		character.removeSpeed(velocity);

	}

	public Position getProjFirePos() {
		return character.getProjFirePos();
	}
	
	public void reset(){
		character.reset();
	}

}

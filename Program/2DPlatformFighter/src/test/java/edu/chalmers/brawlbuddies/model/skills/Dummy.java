package edu.chalmers.brawlbuddies.model.skills;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import edu.chalmers.brawlbuddies.model.Aim;
import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.GameListener;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.Velocity;
import edu.chalmers.brawlbuddies.model.statuseffects.IStatusEffect;
import edu.chalmers.brawlbuddies.model.world.ICharacter;
import edu.chalmers.brawlbuddies.model.world.IGameObject;
import edu.chalmers.brawlbuddies.model.world.Movement.Alignment;

/**
 * A class for testing Effects.
 * @author David Gustafsson
 *
 */
public class Dummy implements IGameObject, ICharacter {
	private float maxHealth;
	private float currentHealth;
	private Aim a = new Aim(0, 0);
	private Velocity v = new Velocity(0, 0);
	private int ID;
	private Position p = new Position(0, 0);
	private Velocity pushedVelocity = new Velocity(0,0);
	private List<IStatusEffect> statusEffectList = new ArrayList<IStatusEffect>();
	public Dummy(float a){
		maxHealth = a;
		currentHealth = a;
		
	}
	/**
	 * Get the current health of the HealthDummy
	 * @return float currentHealth
	 */
	public float getHealth(){
		return currentHealth;
	}
	public void setAim(Aim a){
		this.a = a;
	}
	public void takeDamage(float a) {
		if( currentHealth - a <= 0){
			currentHealth = 0;
		}
		currentHealth -= a;
		
	}
	public void setVelocity(Velocity v){
		this.v = v;
	}
	public void setID(int a){
		ID = a;
	}
	public Velocity getPushedVelocity(){
		return pushedVelocity;
	}
	public void resetPushedVelocity(){
		pushedVelocity = new Velocity(0,0);
	}
	public int getID() {
		return ID;
	}
	public Velocity getTotalVelocity() {
		return v;
	}
	public Position getPosition() {
		return p;
	}
	public void setPosition(Position pos) {
		p = pos;
	}
	public Position getCenterPosition() {
		return p;
	}
	public void setCenterPosition(Position pos) {
		p = pos;
	}
	public Shape getShape() {
		return null;
	}
	public void setShape(Shape shape) {
		
	}
	public void transform(Transform transform) {
		
	}
	public Position update(int delta) {
		return null;
	}
	public boolean isDestroyed() {
		return false;
	}
	public void onCollision(IGameObject object, Alignment alignment) {
		
	}
	public int getTypeID() {
		return 0;
	}
	public void heal(float healAmount) {
		if( currentHealth + healAmount > maxHealth){
			currentHealth = maxHealth;
		} else{
			currentHealth += healAmount;
		}
	}
	public void restoreHealth() {
		currentHealth = maxHealth;
	}
	public void push(Velocity v) {
		this.pushedVelocity = v;
	}
	public Aim getAim() {
		return a;
	}
	public Position getProjFirePos() {
		return null;
	}
	public void move(Direction dir) {
		
	}
	public void makeJump() {
		
	}
	public void setAim(Position aimPosition, boolean isRelative) {
		
	}
	public void activateSkill(int skillIndex) {
		
	}
	public void reset() {
		
	}
	public Direction getDirection() {
		return null;
	}
	public boolean isInAir() {
		return false;
	}
	public void applyStatusEffect(IStatusEffect copy) {
		statusEffectList.add(copy);
	}
	public List<IStatusEffect> getStatusEffects(){
		return statusEffectList;
	}
	public void addScale(float scale) {
		// TODO Auto-generated method stub
		
	}
	public void removeScale(float scale) {
		// TODO Auto-generated method stub
		
	}
	public void restoreScale() {
		// TODO Auto-generated method stub
		
	}
	public void resetGravity() {
		// TODO Auto-generated method stub
		
	}
	public void addSpeed(Velocity velocity) {
		// TODO Auto-generated method stub
		
	}
	public void removeSpeed(Velocity velocity) {
		// TODO Auto-generated method stub
		
	}
	public float getMissingHealth() {
		return maxHealth - currentHealth;
	}
	public void addGameListener(GameListener gl) {
		// TODO Auto-generated method stub
		
	}
	public void removeGameListener(GameListener gl) {
		// TODO Auto-generated method stub
		
	}

}

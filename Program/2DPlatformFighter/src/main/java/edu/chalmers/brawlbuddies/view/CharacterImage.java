package edu.chalmers.brawlbuddies.view;

import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.IWrapper;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.model.skills.SkillWrapper;
import edu.chalmers.brawlbuddies.model.world.CharacterWrapper;

/**
 * Creates an object of the character that can be drawn on the screen. Contains
 * animation and logic for the correct animation.
 * 
 * @author Lisa Lipkin
 * 
 */
public class CharacterImage implements IDrawable {
	private Position drawPos;
	private Position drawOffset;
	private Animation animation;
	private Map<String, Animation> mapAnimation;
	private Direction aimDirection;
	private Direction moveDirection;
	private boolean inAir;
	private String movementName = "idleLeft";
	private boolean isAttacking = false;
	private int id;
	private Circle crosshair;

	/**
	 * Constructor for the Character image. Copies neccesary information from
	 * character through character wrapper.
	 * 
	 * @param character
	 *            a character wrapper from which information is accessed.
	 */
	public CharacterImage(CharacterWrapper character) {
		id = character.getUniqeID();
		mapAnimation = AnimationMapFactory.create(character.getTypeID());
		animation = mapAnimation.get(movementName);
		drawOffset = new Position(
				(character.getShape().getWidth() - animation.getWidth()) / 2,
				character.getShape().getHeight() - animation.getHeight());
		drawPos = getDrawPos(character.getPosition());
		animation.start();
		aimDirection = character.getAim().getDirection();
		moveDirection = character.getDirection();
		crosshair = new Circle(0,0,10);
	}

	/**
	 * {@inheritDoc}
	 */
	public void render(GameContainer gc, Graphics g) {
		animation.draw(drawPos.getX(), drawPos.getY());
		g.setColor(Color.red);
		g.setLineWidth(3);
		g.draw(crosshair);
	}
	
	/**
	 * a method to give the correct format of a string for the use in the class
	 * 
	 * @param str
	 *            the string that is to be corrected
	 * @return str with capital first letter and the rest lowercase
	 */
	private String correctStringFormat(String str) {
		return java.lang.Character.toUpperCase(str.charAt(0))
				+ str.substring(1).toLowerCase();
	}

	/**
	 * Calculates the animations drawposition by adding the drawOffset to the
	 * given position.
	 * 
	 * @param pos
	 *            The upper left corner of the characters shape.
	 * @return The upper left corner of where the animation should be drawn.
	 */
	private Position getDrawPos(Position pos) {
		return new Position(pos.add(drawOffset));
	}

	/**
	 * method to handle a skill used event. Bulky. Would benefit from better
	 * idea.
	 * 
	 * @param wrapper
	 *            a skillwrapper sent by the event.
	 */
	public void useSkill(IWrapper wrapper) {
		SkillWrapper skill = (SkillWrapper) wrapper;
		isAttacking = true;
		String tmpMovementName = skill.getAnimationName()
				+ correctStringFormat(aimDirection.getXDirection().toString());
		// Set correct animation if neccesary
		setAnimation(tmpMovementName);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(IWrapper obj1, IWrapper obj2) {
		CharacterWrapper character = (CharacterWrapper) obj1;
		Direction tmpAimDirection = character.getAim().getDirection().getXDirection();
		Direction tmpMoveDirection = character.getDirection().getXDirection();
		inAir = character.isInAir();
		String tmpMovementName = "";

		if (isAttacking && animation.isStopped()) {
			isAttacking = false;
		}

		if (!isAttacking) {
			if (inAir) {
				if (tmpAimDirection == Direction.NONE) {
					tmpMovementName = "move"
							+ "Air"
							+ correctStringFormat(aimDirection
									.getXDirection().toString());
				} else {
					tmpMovementName = "move"
							+ "Air"
							+ correctStringFormat(tmpAimDirection
									.getXDirection().toString());
				}
			} else if (tmpMoveDirection != Direction.NONE) {
				if (tmpAimDirection == Direction.NONE) {
					tmpMovementName = "move"
							+ "Ground"
							+ correctStringFormat(tmpMoveDirection
									.toString())
							+ correctStringFormat(aimDirection
									.getXDirection().toString());
				} else {
					tmpMovementName = "move"
							+ "Ground"
							+ correctStringFormat(tmpMoveDirection
									.toString())
							+ correctStringFormat(tmpAimDirection
									.getXDirection().toString());
				}
			} else {
				if (tmpAimDirection == Direction.NONE) {
					tmpMovementName = "idle"
							+ correctStringFormat(aimDirection
									.getXDirection().toString());
				} else {
					tmpMovementName = "idle"
							+ correctStringFormat(tmpAimDirection.getXDirection()
									.toString());
				}
			}
			// Set correct animation if neccesary
			setAnimation(tmpMovementName);

		}

		// Set instance variables to current values
		if (tmpMoveDirection != Direction.NONE) {
			moveDirection = tmpMoveDirection;
		}
		if (tmpAimDirection != Direction.NONE) {
			aimDirection = tmpAimDirection;
		}
		drawPos = getDrawPos(character.getPosition());
		crosshair.setCenterX(character.getProjFirePos().getX()+character.getAim().getX());
		crosshair.setCenterY(character.getProjFirePos().getY()+character.getAim().getY());
	}

	/**
	 * sets the correct animation sorted on the appropriate movement name
	 * 
	 * @param tmpMovementName
	 *            the temporary movement name calculated in update and useSkill
	 */
	private void setAnimation(String tmpMovementName) {
		if (!mapAnimation.containsKey(tmpMovementName)) {
			tmpMovementName = "idle"
					+ (aimDirection.getXDirection() != Direction.NONE ? correctStringFormat(aimDirection
							.getXDirection().toString())
							: correctStringFormat(moveDirection.getXDirection().toString()));
		}
		if (!(tmpMovementName.equals(movementName))) {
			animation.stop();
			movementName = tmpMovementName;
			animation = mapAnimation.get(movementName);
			animation.restart();
			animation.start();
		}
	}

	public Integer getUniqeID() {
		return id;
	}
}
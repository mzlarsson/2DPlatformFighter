package edu.chalmers.brawlbuddies.view;

import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;

/**
 * Creates an object of the character that can be drawn on the screen. Contains
 * animation and logic for the correct animation.
 * 
 * @author Lisa
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
	}

	/**
	 * {@inheritDoc}
	 */
	public void render(GameContainer gc, Graphics g) {
		animation.draw(drawPos.getX(), drawPos.getY());
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
	public void useSkill(IWrapper wrapper) { // TODO Temporary until better
												// implemented Event handling
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

		// TODO fix direction and set animation
		if (obj2 != null) {
			if (obj2.getClass() == SkillWrapper.class) {
				SkillWrapper skill = (SkillWrapper) obj2;
				isAttacking = true;
				if (tmpAimDirection != Direction.NONE) {
					tmpMovementName = skill.getAnimationName()
							+ correctStringFormat(tmpAimDirection.toString());

				} else {
					tmpMovementName = skill.getAnimationName()
							+ correctStringFormat(aimDirection.toString());

				}
				// Set correct animation if neccesary
				setAnimation(tmpMovementName);
			}
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
			System.out.println(movementName);
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
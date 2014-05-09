package edu.chalmers.brawlbuddies.view;

import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import edu.chalmers.brawlbuddies.model.Direction;
import edu.chalmers.brawlbuddies.model.Position;
import edu.chalmers.brawlbuddies.services.factories.AnimationMapFactory;

public class CharacterImage implements IDrawable {
	private Position position;
	private Animation animation;
	private Map<String, Animation> mapAnimation;
	private Direction aimDirection;
	private Direction moveDirection;
	private Boolean inAir;
	private String movementName = "idleLeft";
	private boolean isAttacking = false;

	public CharacterImage(CharacterWrapper character) {
		int tmpID = character.getTypeID();
		position = character.getPosition();
		mapAnimation = AnimationMapFactory.create(tmpID);
		animation = mapAnimation.get(movementName);
		animation.start();
		aimDirection = character.getAim().getDirection();
		moveDirection = character.getDirection();
	}

	public void render(GameContainer gc, Graphics g) {
		animation.draw(position.getX(), position.getY());
	}

	private String correctStringFormat(String str) {
		return java.lang.Character.toUpperCase(str.charAt(0))
				+ str.substring(1).toLowerCase();
	}

	public void update(IWrapper obj1, IWrapper obj2) {
		CharacterWrapper character = (CharacterWrapper) obj1;
		Direction tmpAimDirection = character.getAim().getDirection();
		Direction tmpMoveDirection = character.getDirection();
		String tmpMovementName = "";

		if (isAttacking && animation.isStopped()) {
			isAttacking = false;
		}

		// TODO fix direction and set animation
		if (obj2!=null) {
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
			if (tmpMoveDirection == Direction.NONE) {
				tmpMovementName = "idle"
						+ correctStringFormat(moveDirection.toString());
			} else {
				if (inAir) {
					if (tmpAimDirection == Direction.NONE) {
						tmpMovementName = "move"
								+ "Air"
								+ correctStringFormat(tmpMoveDirection
										.toString())
								+ correctStringFormat(aimDirection.toString());
					} else {
						tmpMovementName = "move"
								+ "Air"
								+ correctStringFormat(tmpMoveDirection
										.toString())
								+ correctStringFormat(tmpAimDirection
										.toString());
					}
				} else {
					if (tmpAimDirection == Direction.NONE) {
						tmpMovementName = "move"
								+ "Ground"
								+ correctStringFormat(tmpMoveDirection
										.toString())
								+ correctStringFormat(aimDirection.toString());
					} else {
						tmpMovementName = "move"
								+ "Ground"
								+ correctStringFormat(tmpMoveDirection
										.toString())
								+ correctStringFormat(tmpAimDirection
										.toString());
					}
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
		inAir = character.isInAir();
		position = character.getPosition();
	}

	private void setAnimation(String tmpMovementName) {
		if (!(tmpMovementName.equals(movementName))) {
			animation.stop();
			movementName = tmpMovementName;
			animation = mapAnimation.get(movementName);
			animation.setCurrentFrame(0);
			animation.start();
		}
	}
}
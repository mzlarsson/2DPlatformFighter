package edu.chalmers.brawlbuddies.view;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.eventbus.IEventBusSubscriber;
import edu.chalmers.brawlbuddies.model.IWrapper;
import edu.chalmers.brawlbuddies.model.skills.SkillWrapper;
import edu.chalmers.brawlbuddies.model.world.CharacterWrapper;
import edu.chalmers.brawlbuddies.model.world.HealthWrapper;
import edu.chalmers.brawlbuddies.model.world.ProjectileWrapper;

/**
 * The main game view that handles the view in game.
 * @author Lisa Lipkin
 * @revised Patrik Haar
 * @version 1.0
 */
public class GameView implements IEventBusSubscriber, IView {
	// goal and time realated variables
	private boolean timeGoal;
	private int timeLimit;
	// tiled map and map lists of all objects in view
	private TiledMap map;
	private Map<Integer, Animation> objectAnims;
	private Map<Integer, IDrawable> objects;
	private Map<Integer, HudImage> huds;
	// scroller related variables
	private SideScroller scroller;
	private boolean resolutionSet = false;

	/**
	 * Creates the gameView instance.
	 */
	public GameView() {
		objectAnims = new TreeMap<Integer, Animation>();
		objects = new TreeMap<Integer, IDrawable>();
		huds = new HashMap<Integer, HudImage>();
		scroller = new SideScroller(null, null, 200, 200, 1.5d);
		EventBus.getInstance().addSubscriber(this);
	}
	//setters and getters

	/**
	 * set the lives in the hud objects
	 * 
	 * @param lives
	 *            the amount of lives the characters should begin with
	 */
	private void setLives(Integer lives) {
		for (Map.Entry<Integer, HudImage> entry : huds.entrySet()) {
			entry.getValue().setLives(lives);
		}
	
	}

	/**
	 * retun the scroller of this wiew
	 * 
	 * @return the scroller for this game view
	 */
	public SideScroller getScroller() {
		return this.scroller;
	}
//event handling
	/**
	 * a method to handle all the events the view listens to
	 * 
	 * @param event
	 *            the event that is to be handled
	 */
	public void eventPerformed(EventBusEvent event) {
		if (event.getName().equals("createObject")) {
			createObjectImage((IWrapper) event.getRecipient());
		} else if (event.getName().equals("removeObject")) {
			removeObjectImage((IWrapper) event.getRecipient());
		} else if (event.getName().equals("updateObject")) {
			updateObject((IWrapper) event.getRecipient(),
					(IWrapper) event.getActor());
		} else if (event.getName().equals("skillUsed")) {
			((CharacterImage) objects.get((Integer) event.getRecipient()))
					.useSkill((IWrapper) event.getActor());

			updateObject((IWrapper) event.getActor(), null);
		} else if (event.getName().equals("createMap")) {
			this.map = (TiledMap) event.getRecipient();
			this.scroller.setMapSize(map.getWidth() * map.getTileWidth(),
					map.getHeight() * map.getTileHeight());
		} else if (event.getName().equals("createAnimation")) {
			this.objectAnims.put(
					((IWrapper) event.getRecipient()).getTypeID(),
					AnimationMapFactory.create(
							((IWrapper) event.getRecipient()).getTypeID()).get(
							"idle"));
		} else if (event.getName().equals("createHud")) {
			addHud((CharacterWrapper) event.getRecipient());
		} else if (event.getName().equals("characterDied")) {
			int characterID = (Integer) event.getRecipient();
			huds.get(characterID).characterDied();
		} else if (event.getName().equals("lifeLimitAdded")) {
			setLives((Integer) event.getRecipient());

		} else if (event.getName().equals("timeLimitAdded")) {
			timeGoal = true;
			timeLimit = (Integer) event.getRecipient() * 1000;
		}
	}
	
	public void update(int delta) {
		if(timeGoal) {
			timeLimit = timeLimit - delta;
		}
		for (Map.Entry<Integer, HudImage> entry : huds.entrySet()) {
			entry.getValue().update(delta);
		}
	}
	
//render methods
	/**
	 * A method to render the game on screen using Slick2D
	 * 
	 * @param gc
	 *            the slick game contianer
	 * @param g
	 *            the current slick graphic object
	 */
	public void render(GameContainer gc, Graphics g) {
		// set resulution
		if (!resolutionSet) {
			this.scroller.setResolution(gc.getWidth(), gc.getHeight());
			this.resolutionSet = true;
		}
		// set scale
		g.scale(scroller.getScale(), scroller.getScale());
		g.translate(scroller.getX(), scroller.getY());
		// render scalable objects
		map.render(0, 0);
		for (Map.Entry<Integer, IDrawable> entry : objects.entrySet()) {
			entry.getValue().render(gc, g);
		}

		g.translate(-scroller.getX(), -scroller.getY());
		g.scale(1.0f / scroller.getScale(), 1.0f / scroller.getScale());
		// render hud
		for (Map.Entry<Integer, HudImage> entry : huds.entrySet()) {
			entry.getValue().render(gc, g);
		}
		// render time
		if (timeGoal) {
			renderTime(g);
		}
	}
	
	/**
	 * render the time with graphics object
	 * 
	 * @param time
	 *            the difference in time since last update
	 * @param g
	 *            Slick graphics object
	 */
	private void renderTime(Graphics g) {
		int minutes = timeLimit / 60000;
		int seconds = (timeLimit % 60000)/1000;
		g.setColor(Color.red);
		g.drawString(
				Integer.toString(minutes) + ":" + Integer.toString(seconds),
				600, 50);
		g.setColor(Color.black);
	}

	//creation methods
	/**
	 * method to handle the creation of the view components
	 * 
	 * @param obj
	 *            the wrapper for the object to be created
	 */
	private void createObjectImage(IWrapper obj) {
		if (obj.getClass() == SkillWrapper.class) {
			createSkillImage(obj);
		} else if (obj.getClass() == HealthWrapper.class) {
			createHealthImage(obj);
		} else if (obj.getClass() == CharacterWrapper.class) {
			CharacterImage image = new CharacterImage((CharacterWrapper) obj);
			addObjectImage(image);
		} else if (obj.getClass() == ProjectileWrapper.class) {
			ProjectileImage image = new ProjectileImage(objectAnims.get(obj
					.getTypeID()), obj.getUniqeID());
			addObjectImage(image);
		}
	}

	/**
	 * adds an object image to the objects collection
	 * 
	 * @param drawable
	 *            a drawable object implementing the IDrawable interface
	 */
	private void addObjectImage(IDrawable drawable) {
		if (drawable != null) {
			objects.put(drawable.getUniqeID(), drawable);
		}
	}
	/**
	 * a method to cerate a new hud object
	 * 
	 * @param obj
	 *            an IWrapper object that contains the neccesary information to
	 *            create a hud object
	 * @return a new hudImage
	 */
	public HudImage createHud(IWrapper obj) {
		return new HudImage(obj);
	}

	/**
	 * adds a hud image to the hud collection in view
	 * 
	 * @param obj
	 *            the character wrapper from which it is possible to get the id
	 *            of the hud
	 */
	public void addHud(IWrapper obj) {
		CharacterWrapper character = (CharacterWrapper) obj;
		huds.put(character.getUniqeID(), createHud(obj));
	}

	/**
	 * creates a skill image and adds it to the correct hud
	 * 
	 * @param obj
	 *            the wrapper of skill to be created
	 */
	private void createSkillImage(IWrapper obj) {
		SkillWrapper skillW = (SkillWrapper) obj;
		SkillImage skill = new SkillImage(skillW);
		huds.get(skill.getID()).addSkill(skill);
	}

	/**
	 * createse a health bar and adds it to the correct hud
	 * 
	 * @param obj
	 *            the wrapper of the health object to be created
	 */
	private void createHealthImage(IWrapper obj) {
		HealthWrapper healthW = (HealthWrapper) obj;
		HealthImage image = new HealthImage(healthW);
		HudImage hud = huds.get(healthW.getID());
		hud.addHealthBar(image);
	}
	//update methods
	/**
	 * method to handle the update of objects
	 * 
	 * @param obj1
	 *            the first object in an event, mainly the object changed by the
	 *            update
	 * @param obj2
	 *            the sectond object in an event, mainly contains additional
	 *            information to obj1
	 */
	public void updateObject(IWrapper obj1, IWrapper obj2) {
		if (obj1.getClass() == SkillWrapper.class) {
			SkillWrapper skill = (SkillWrapper) obj1;
			huds.get(skill.getOwnerID()).update(obj1, obj2);
		} else if (obj1.getClass() == HealthWrapper.class) {
			huds.get(obj1.getUniqeID()).update(obj1, obj2);
		} else {
			objects.get(obj1.getUniqeID()).update(obj1, obj2);
		}
	}

	/**
	 * updates the resulution.
	 */
	public void resolutionUpdated() {
		this.resolutionSet = false;
	}

	//removal and close
	/**
	 * removes an object from the objects collection
	 * 
	 * @param obj
	 *            a wrapper containing the correct id so it is possible to
	 *            remove the object with that id
	 */
	public void removeObjectImage(IWrapper obj) {
		int uniqeID = obj.getUniqeID();
		objects.remove(uniqeID);
	}

	/**
	 * method to handle the cleanup in the view on game closing
	 */
	public void close() {
		for (Map.Entry<Integer, HudImage> entry : huds.entrySet()) {
			entry.getValue().gameOver();
		}
		huds.clear();
		EventBus.getInstance().removeSubscriber(this);

	}
}

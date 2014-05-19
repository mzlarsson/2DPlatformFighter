package edu.chalmers.brawlbuddies.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
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

public class GameView implements IEventBusSubscriber, IView {
	private boolean timeGoal;
	private int timeLimit = 0;
	private int delta;
	private long lastFrame;

	private TiledMap map;
	private Map<Integer, Animation> objectAnims;
	private Map<Integer, IDrawable> objects;
	private Map<Integer, HudImage> huds = new HashMap<Integer, HudImage>();
	private SideScroller scroller;
	private boolean resolutionSet = false;

	public GameView() {
		objectAnims = new TreeMap<Integer, Animation>();
		objects = new TreeMap<Integer, IDrawable>();
		scroller = new SideScroller(null, null, 200, 200, 1.5d);
		EventBus.getInstance().addSubscriber(this);
	}

	public void render(GameContainer gc, Graphics g) {
		if (lastFrame > 0) {
			delta = (int) (gc.getTime() - lastFrame);
		}
		lastFrame = gc.getTime();

		if (!resolutionSet) {
			this.scroller.setResolution(gc.getWidth(), gc.getHeight());
			this.resolutionSet = true;
		}

		g.scale(scroller.getScale(), scroller.getScale());
		g.translate(scroller.getX(), scroller.getY());
		map.render(0, 0);
		for (Map.Entry<Integer, IDrawable> entry : objects.entrySet()) {
			entry.getValue().render(gc, g);
		}

		g.translate(-scroller.getX(), -scroller.getY());
		g.scale(1.0f / scroller.getScale(), 1.0f / scroller.getScale());
		for (Map.Entry<Integer, HudImage> entry : huds.entrySet()) {
			entry.getValue().render(gc, g);
		}
		if (timeGoal) {
			renderTime(delta, g);
		}
	}

	private void renderTime(int time, Graphics g) {
		timeLimit = timeLimit - time;

		int minutes = timeLimit / 60000;
		int seconds = (timeLimit % 60000) / 600;

		g.setColor(Color.red);
		g.drawString(
				Integer.toString(minutes) + ":" + Integer.toString(seconds),
				600, 50);
		g.setColor(Color.black);
	}

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
			HudImage.lifeLimit = true;
			setLives((Integer) event.getRecipient());

		} else if (event.getName().equals("timeLimitAdded")) {
			timeGoal = true;
			timeLimit = (Integer) event.getRecipient() * 1000;
		} else if (event.getName().equals("updateTime")) {
			timeLimit = (Integer) event.getRecipient();
		}
	}

	private void setLives(Integer lives) {
		for (Map.Entry<Integer, HudImage> entry : huds.entrySet()) {
			entry.getValue().setLives(lives);
		}

	}

	public HudImage createHud(IWrapper obj) {
		return new HudImage(obj);
	}

	public void addHud(IWrapper obj) {
		CharacterWrapper character = (CharacterWrapper) obj;
		huds.put(character.getUniqeID(), createHud(obj));
	}

	public void removeObjectImage(IWrapper obj) {
		int uniqeID = obj.getUniqeID();
		objects.remove(uniqeID);
	}

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

	private void addObjectImage(IDrawable drawable) {
		if (drawable != null) {
			objects.put(drawable.getUniqeID(), drawable);
		}
	}

	private IDrawable createObjectImage(IWrapper obj) {
		if (obj.getClass() == SkillWrapper.class) {
			createSkillImage(obj);
			return null;
		} else if (obj.getClass() == HealthWrapper.class) {
			createHealthImage(obj);
			return null;
		} else if (obj.getClass() == CharacterWrapper.class) {
			CharacterImage image = new CharacterImage((CharacterWrapper) obj);
			addObjectImage(image);
			return image;
		} else if (obj.getClass() == ProjectileWrapper.class) {
			ProjectileImage image = new ProjectileImage(objectAnims.get(obj
					.getTypeID()), obj.getUniqeID());
			addObjectImage(image);
			return image;
		} else {
			return null;
		}
	}

	private void createSkillImage(IWrapper obj) {
		SkillWrapper skillW = (SkillWrapper) obj;
		SkillImage skill = new SkillImage(skillW);
		huds.get(skill.getID()).addSkill(skill);
	}

	private void createHealthImage(IWrapper obj) {
		HealthWrapper healthW = (HealthWrapper) obj;
		HealthImage image = new HealthImage(healthW);
		HudImage hud = huds.get(healthW.getID());
		hud.addHealthBar(image);
	}

	public void close() {
		System.out.println("close");
		for (Map.Entry<Integer, HudImage> entry : huds.entrySet()) {
			entry.getValue().gameOver();
		}
		huds.clear();
		EventBus.getInstance().removeSubscriber(this);

	}

	public void resolutionUpdated() {
		this.resolutionSet = false;
	}

}

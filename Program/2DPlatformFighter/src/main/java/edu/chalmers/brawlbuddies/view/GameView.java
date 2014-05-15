package edu.chalmers.brawlbuddies.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.eventbus.IEventBusSubscriber;

public class GameView implements IEventBusSubscriber, IView {
	private TiledMap map;
	private Map<Integer, Animation> objectAnims;
	private Map<Integer, IDrawable> objects;
	private Map<Integer, HudImage> huds = new HashMap<Integer, HudImage>();

	public GameView() {
		objectAnims = new TreeMap<Integer, Animation>();
		objects = new TreeMap<Integer, IDrawable>();
		EventBus.getInstance().addSubscriber(this);
	}

	public void render(GameContainer gc, Graphics g) {
		map.render(0, 0);
		for (Map.Entry<Integer, IDrawable> entry : objects.entrySet()) {
			entry.getValue().render(gc, g);
		}
		for (Map.Entry<Integer, HudImage> entry : huds.entrySet()) {
			entry.getValue().render(gc, g);
		}
	}

	public void eventPerformed(EventBusEvent event) {
		if (event.getName().equals("createObject")) {
			addObjectImage((IWrapper) event.getRecipient());
		} else if (event.getName().equals("removeObject")) {
			removeObjectImage((IWrapper) event.getRecipient());
		} else if (event.getName().equals("updateObject")) {
			updateObject((IWrapper) event.getRecipient(),
					(IWrapper) event.getActor());
		} else if (event.getName().equals("skillUsed")) {
			((CharacterImage) objects.get((Integer) event.getRecipient()))
					.useSkill((IWrapper) event.getActor());
		} else if (event.getName().equals("createMap")) {
			this.map = (TiledMap) event.getRecipient();
		} else if (event.getName().equals("createAnimation")) {
			this.objectAnims.put(
					((IWrapper) event.getRecipient()).getTypeID(),
					AnimationMapFactory.create(
							((IWrapper) event.getRecipient()).getTypeID()).get(
							"idle"));
		} 
	}
	public HudImage createHud(IWrapper obj) {
		return new HudImage(obj);
	}

	public void addHud(IWrapper obj) {
		SkillWrapper skill = (SkillWrapper) obj;
		if (!huds.containsKey(skill.getOwnerID())) {
			HudImage hud = createHud(obj);
			huds.put(skill.getOwnerID(), hud);
		}
	}

	public void removeObjectImage(IWrapper obj) {
		int uniqeID = obj.getUniqeID();
		objects.remove(uniqeID);
	}

	public void updateObject(IWrapper obj1, IWrapper obj2) {
		if (obj1.getClass() == SkillWrapper.class) {
			huds.get(obj1.getUniqeID()).update(obj1, obj2);
		} else {
			objects.get(obj1.getUniqeID()).update(obj1, obj2);
		}
	}

	private void addObjectImage(IWrapper obj) {
		objects.put(obj.getUniqeID(), createObjectImage(obj));
	}

	private IDrawable createObjectImage(IWrapper obj) {
		if (obj.getClass() == CharacterWrapper.class) {
			return new CharacterImage((CharacterWrapper) obj);
		} else if (obj.getClass() == ProjectileWrapper.class) {
			return new ProjectileImage(objectAnims.get(obj.getTypeID()));
		} else if (obj.getClass() == SkillWrapper.class) {
			SkillWrapper skillW= (SkillWrapper)obj;
			if (!huds.containsKey(skillW.getOwnerID())){
				addHud(obj);
			}
			SkillImage skill = new SkillImage(skillW);
			huds.get(skill.getID()).addSkill(skill);
			return skill;
		}
		return null;
	}

}

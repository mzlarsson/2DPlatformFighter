package edu.chalmers.brawlbuddies.view;

import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.eventbus.IEventBusSubscriber;
import edu.chalmers.brawlbuddies.services.factories.AnimationMapFactory;

public class GameView implements IEventBusSubscriber, IView {
	private TiledMap map;
	private Map<Integer, Animation> objectAnims;
	private Map<Integer, IDrawable> objects;

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
			((CharacterImage)objects.get((Integer)event.getRecipient())).useSkill((IWrapper)event.getActor());;
		} else if (event.getName().equals("createMap")) {
			this.map = (TiledMap)event.getRecipient();
		} else if (event.getName().equals("createAnimation")) {
			this.objectAnims.put(((IWrapper)event.getRecipient()).getTypeID()
					, AnimationMapFactory.create(
							((IWrapper)event.getRecipient()).getTypeID())
							.get("idle"));
		}
	}

	public void removeObjectImage(IWrapper obj) {
		int uniqeID = obj.getUniqeID();
		objects.remove(uniqeID);
	}

	public void updateObject(IWrapper obj1, IWrapper obj2) {
		objects.get(obj1.getUniqeID()).update(obj1, obj2);
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
			return new SkillImage((SkillWrapper) obj);
		}
		return null;
	}

}

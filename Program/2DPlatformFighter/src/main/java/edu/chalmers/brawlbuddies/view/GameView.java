package edu.chalmers.brawlbuddies.view;

import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import edu.chalmers.brawlbuddies.eventbus.EventBus;
import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.eventbus.IEventBusSubscriber;

public class GameView implements IEventBusSubscriber, IView {
	public TiledMap map;
	public Map<Integer, IDrawable> objects;

	public GameView() {
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
		} else if (event.getName().equals("createMap")) {
			this.map = (TiledMap)event.getRecipient();
		}
	}

	public void removeObjectImage(IWrapper obj) {
		int uniqeID = obj.getUniqeID();
		objects.remove(uniqeID);
	}

	public void updateObject(IWrapper obj1, IWrapper obj2) {
		int uniqeID = obj1.getUniqeID();
		IDrawable object = objects.get(uniqeID);
		object.update(obj1, obj2);
	}

	private void addObjectImage(IWrapper obj) {
		objects.put(obj.getUniqeID(), createObjectImage(obj));
	}

	private IDrawable createObjectImage(IWrapper obj) {
		if (obj.getClass() == CharacterWrapper.class) {
			return new CharacterImage((CharacterWrapper) obj);
		} else if (obj.getClass() == ProjectileWrapper.class) {
			return new ProjectileImage((ProjectileWrapper) obj);
		}else if (obj.getClass() == SkillWrapper.class) {
			return new SkillImage((SkillWrapper) obj);
		}
		return null;
	}

}

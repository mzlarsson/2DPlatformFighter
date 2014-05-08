package edu.chalmers.brawlbuddies.view;

import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import edu.chalmers.brawlbuddies.eventbus.EventBusEvent;
import edu.chalmers.brawlbuddies.eventbus.IEventBusSubscriber;
import edu.chalmers.brawlbuddies.model.skills.Skill;
import edu.chalmers.brawlbuddies.model.world.Projectile;
import edu.chalmers.brawlbuddies.model.world.Character;

public class GameView implements IEventBusSubscriber, IView{
	public Map<Integer, IDrawable> objects;

	public GameView() {
		objects = new TreeMap();
	}

	public void render(GameContainer gc, Graphics g) {
		for (Map.Entry<Integer, IDrawable> entry : objects.entrySet()) {
			entry.getValue().render(gc, g);
		}

	}

	public void eventPerformed(EventBusEvent event) {
		if (event.getName().equals("createObject")) {
			addObjectImage((IWrapper)event.getRecipient());
		}
		if (event.getName().equals("removeObject")) {
			removeObjectImage((IWrapper)event.getRecipient());
		}
		if (event.getName().equals("updateObject")) {
			updateObject((IWrapper)event.getRecipient(), (IWrapper)event.getActor());
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
		if (obj.getClass() == Character.class) {
			return new CharacterImage((CharacterWrapper) obj);
		}
		if (obj.getClass() == Projectile.class) {
			return new ProjectileImage((ProjectileWrapper) obj);
		}
		if (obj.getClass() == Skill.class) {
			return new SkillImage((SkillWrapper) obj);
		}
		return null;
	}

}

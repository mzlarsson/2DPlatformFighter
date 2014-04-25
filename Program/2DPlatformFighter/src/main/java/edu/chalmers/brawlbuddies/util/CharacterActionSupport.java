package edu.chalmers.brawlbuddies.util;

import java.util.ArrayList;
import java.util.List;

import edu.chalmers.brawlbuddies.model.Skills.Effect;
import edu.chalmers.brawlbuddies.model.world.GameObject;
import edu.chalmers.brawlbuddies.model.world.Projectile;
import edu.chalmers.brawlbuddies.model.world.Character;

public class CharacterActionSupport {
	List<CharacterActionListener> listeners = new ArrayList<CharacterActionListener>();
	
	public void addListener(CharacterActionListener listener){
		listeners.add(listener);
	}
	public void removeListener(CharacterActionListener listener){
		listeners.remove(listener);
	}
	public void sendEvent(Projectile p) {
		for( CharacterActionListener l : listeners){
			l.characterActionPerformed(p);
		}
	}
	public void sendEvent(GameObject o) {
		for( CharacterActionListener l : listeners){
			l.characterActionPerformed(o);
		}
	}
	public void sendEvent(Character c , Effect e){
		for( CharacterActionListener l : listeners){
			l.characterActionPerformed(c, e);
		}
	}
	public void sendEvent(Effect e){
		for( CharacterActionListener l : listeners){
			l.characterActionPerformed(e);
		}
	}
}

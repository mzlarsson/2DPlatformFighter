package edu.chalmers.brawlbuddies.view.sound;

import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.services.factories.XMLReader;

public class SoundFactory {
	
	private static SoundFactory instance;
	
	private Document document;
	private String[] typeName;

	/**
	 * Creates a new factory for creation of new sounds
	 */
	private SoundFactory() {
		this.document = XMLReader.getDocument(Constants.SOUND + "sound_resource_locator.xml", Constants.SOUND + "sound_schema.xsd");
		this.typeName = new String[]{null, "characters", "skills", "projectiles", "melees"};
	}
	
	/**
	 * Returns an instance of a SoundFactory
	 * @return An instance of a SoundFactory
	 */
	public static SoundFactory getInstance(){
		if(instance == null){
			instance = new SoundFactory();
		}
		
		return instance;
	}

	/**
	 * Retrieves all sounds connected to the given typeID
	 * @param typeID The type ID to use
	 * @return A map of all sounds with the sound name as key and the content as value
	 */
	public Map<String, SoundKeeper> getSounds(int typeID){
		Map<String, SoundKeeper> sounds = new TreeMap<String, SoundKeeper>();
		
		Element el = this.document.getElementById("ID_"+typeID);
		if(el != null){
			String path = Constants.SOUND;
			path += typeName[Character.getNumericValue(String.valueOf(typeID).charAt(0))] + "/";
			path += el.getElementsByTagName("name").item(0).getFirstChild().getNodeValue() + "/";
			
			NodeList soundNodes = el.getElementsByTagName("sound");
			NodeList stopNodes = null;
			Element soundNode = null;
			SoundKeeper sound = null;
			String key = "", filePath = "";
			boolean looping = false;
			for(int i = 0; i<soundNodes.getLength(); i++){
				//Basic nodes
				soundNode = (Element)soundNodes.item(i);
				key = soundNode.getElementsByTagName("trigger").item(0).getFirstChild().getNodeValue();
				filePath = path + soundNode.getElementsByTagName("source").item(0).getFirstChild().getNodeValue();
				
				//StopNodes
				String[] stoppers = null;
				if(soundNode.getElementsByTagName("stoppers").getLength()>0){
					stopNodes = ((Element)soundNode.getElementsByTagName("stoppers").item(0)).getElementsByTagName("stopper");
					stoppers = new String[stopNodes.getLength()];
					for(int j = 0; j<stoppers.length; j++){
						stoppers[j] = stopNodes.item(j).getFirstChild().getNodeValue();
					}
				}else{
					stoppers = new String[]{};
				}
				
				//Looping or not
				looping = Boolean.parseBoolean(soundNode.getAttribute("looping"));
				
				try {
					sound = new SoundKeeper(new Sound(filePath), stoppers, looping);
					sounds.put(key, sound);
				} catch (SlickException e) {
					System.out.println("Did not find sound file: "+filePath);
				}
			}
		}
		
		return sounds;
	}
}

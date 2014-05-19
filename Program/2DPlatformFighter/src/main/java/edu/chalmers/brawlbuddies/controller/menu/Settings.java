package edu.chalmers.brawlbuddies.controller.menu;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import edu.chalmers.brawlbuddies.Constants;
import edu.chalmers.brawlbuddies.util.XMLReader;
import edu.chalmers.brawlbuddies.util.XMLWriter;

public class Settings {
	
	private static Settings instance;
	
	private Map<String, String> values;

	private Settings() {
		values = new HashMap<String, String>();
		load();
	}
	
	public static Settings getInstance(){
		if(instance == null){
			instance = new Settings();
		}
		
		return instance;
	}
	
	public void setSetting(String setting, String value){
		if(setting != null && value != null){
			this.values.put(setting, value);
		}
	}
	
	public Map<String, String> getSettings(){
		return this.values;
	}
	
	public String getSetting(String setting){
		return this.values.get(setting);
	}
	
	public void save(){
		StringBuilder builder = new StringBuilder();
		builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		builder.append("<settings>\n");
		for(String settingName : this.values.keySet()){
			builder.append("\t<setting>\n");
				builder.append("\t\t<name>"+settingName+"</name>\n");
				builder.append("\t\t<value>"+getSetting(settingName)+"</value>\n");
			builder.append("\t</setting>\n");
		}
		builder.append("</settings>");
		
		XMLWriter.write(Constants.SETTINGS, builder.toString());
	}
	
	public void load(){
		this.values.clear();
		if(new File(Constants.SETTINGS).exists()){
			Document doc = XMLReader.getDocument(Constants.SETTINGS);
			NodeList list = doc.getElementsByTagName("setting");
			for(int i = 0; i<list.getLength(); i++){
				Element el = (Element)list.item(i);
				String name = el.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
				String value = el.getElementsByTagName("value").item(0).getFirstChild().getNodeValue();
				this.setSetting(name, value);
			}
		}
	}

}

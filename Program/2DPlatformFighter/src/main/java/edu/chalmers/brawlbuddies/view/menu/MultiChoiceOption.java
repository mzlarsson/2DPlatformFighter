package edu.chalmers.brawlbuddies.view.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiChoiceOption {

	private String codeValue;
	private String value;
	
	public MultiChoiceOption(String codeValue, String value) {
		this.codeValue = codeValue;
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public String getCodeValue(){
		return this.codeValue;
	}
	
	public static List<MultiChoiceOption> stringToMultiChoice(Map<String, String> values){
		List<MultiChoiceOption> list = new ArrayList<MultiChoiceOption>();
		for(String key : values.keySet()){
			list.add(new MultiChoiceOption(key, values.get(key)));
		}
		
		return list;
	}
	
	public static List<MultiChoiceOption> stringToMultiChoice(String[] values){
		List<MultiChoiceOption> list = new ArrayList<MultiChoiceOption>();
		for(String value : values){
			list.add(new MultiChoiceOption(value, value));
		}
		
		return list;
	}

}

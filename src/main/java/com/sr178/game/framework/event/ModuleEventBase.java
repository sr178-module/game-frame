package com.sr178.game.framework.event;

import java.util.HashMap;
import java.util.Map;

public class ModuleEventBase {
    private Map<String,Object> eventAttbute = new HashMap<String,Object>();
    
    public String getStringValue(String key,String defaultValue){
    	String result = null;
    	if(eventAttbute.containsKey(key)){
    		result =  (String)eventAttbute.get(key);
    		return result;
    	}
    	return defaultValue;
    }
    
    public void addStringValue(String key,String value){
    	eventAttbute.put(key, value);
    }
    
    public int getIntValue(String key,int defaultValue){
    	int result = 0;
    	if(eventAttbute.containsKey(key)){
    		result =  (Integer)eventAttbute.get(key);
    		return result;
    	}
    	return defaultValue;
    }
    public void addIntValue(String key,int value){
    	eventAttbute.put(key, value);
    }
    @SuppressWarnings("unchecked")
	public <T> T getObjectValue(String key){
    	T result = null;
    	if(eventAttbute.containsKey(key)){
    		result =  (T)eventAttbute.get(key);
    		return result;
    	}
    	return null;
    }
    public void addObjectValue(String key,Object value){
    	eventAttbute.put(key, value);
    }
}

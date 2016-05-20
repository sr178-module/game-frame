package com.sr178.game.framework.config;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import com.sr178.game.framework.log.LogSystem;


@SuppressWarnings("rawtypes")
public class ConfigLoader {

	private static Properties pros = null;
	
	static {
		pros = new Properties();
		try {
			pros.load(ConfigLoader.class.getResourceAsStream("/config.properties"));
			LogSystem.info("config has been loaded!");
			for (Enumeration e = pros.propertyNames(); e.hasMoreElements();) {
				String key = String.valueOf(e.nextElement());
				LogSystem.info(key + "****:" + pros.getProperty(key));
			}
		} catch (IOException e) {
			LogSystem.info("no config been loaded!");
		}
	}
	
	public static String getStringValue(String key){
		return pros.getProperty(key);
	}
	public static String getStringValue(String key,String defaultValue){
		return pros.getProperty(key,defaultValue);
	}
	public static Long getLongValue(String key){
		return getLongValue(key, null);
	}
	public static Long getLongValue(String key,Long defaultValue){
		String s = pros.getProperty(key);
		if(s==null){
			return defaultValue;
		}
		return Long.valueOf(s);
	}
	public static Integer getIntValue(String key){
		return getIntValue(key, null);
	}
	public static Integer getIntValue(String key,Integer defaultValue){
		String s = pros.getProperty(key);
		if(s==null){
			return defaultValue;
		}
		return Integer.valueOf(s);
	}
	
	public static Double getDoubleValue(String key){
		return getDoubleValue(key, null);
	}
	public static Double getDoubleValue(String key,Double defaultValue){
		String s = pros.getProperty(key);
		if(s==null){
			return defaultValue;
		}
		return Double.valueOf(s);
	}
	
	
	public static String[] getStringArray(String key,String split){
		 return getStringArray(key, split, null);
	}
	
	public static String[] getStringArray(String key,String split,String[] defaultValue){
		String s = pros.getProperty(key);
		if(s==null){
			return defaultValue;
		}
		return s.split(split);
	}
	
	public static int[] getIntArray(String key,String split){
		  return getIntArray(key, split, null);
	}
	
	public static int[] getIntArray(String key,String split,int[] defaultValue){
		String[] array = getStringArray(key, split);
		 if(array==null){
			 return defaultValue;
		 }
		 int[] result = new int[array.length];
		 for(int i=0;i<array.length;i++){
			 result[i] = Integer.valueOf(array[i]);
		 }
		 return result;
	}
}

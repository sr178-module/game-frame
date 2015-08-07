package com.sr178.game.framework.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sr178.game.framework.log.LogSystem;

public class ModuleEventManager {
    private static ModuleEventManager mgr = new ModuleEventManager();
    
    private Map<String,List<ModuleEventHandler>> moduleHandlerMap = new HashMap<String,List<ModuleEventHandler>>();
    
    private ModuleEventManager(){}
    
    public static ModuleEventManager getInstance(){
    	return mgr;
    }
    /**
     * 添加handler
     * @param key
     * @param handler
     */
    public void addHandler(ModuleEventHandler handler){
    	List<String> keys = handler.getHandlerType();
    	for(String key:keys){
    	LogSystem.info("添加事件"+key+",class="+handler.getClass().getSimpleName());
    	List<ModuleEventHandler> list = moduleHandlerMap.get(key);
    	if(list==null){
    		list = new ArrayList<ModuleEventHandler>();
    	}
    	list.add(handler);
    	Collections.sort(list, new Comparator<ModuleEventHandler>() {
			@Override
			public int compare(ModuleEventHandler o1, ModuleEventHandler o2) {
				if(o1.order()>o2.order()){
					return 1;
				}else if(o1.order()<o2.order()){
					return -1;
				}
				return 0;
			}
		});
    	moduleHandlerMap.put(key, list);
      }
    }
    /**
     * 分发事件
     * @param key
     * @param baseModuleEvent
     */
    public void dispatchEvent(String key,ModuleEventBase baseModuleEvent){
    	List<ModuleEventHandler> list = moduleHandlerMap.get(key);
    	LogSystem.debug("dispatchEventName = " + key);
    	if(list!=null&&list.size()>0){
    		for(ModuleEventHandler eventHandler:list){
    			 LogSystem.debug("执行eventName="+key+"handler="+eventHandler.getClass().getName()+",order="+eventHandler.order());
    			 eventHandler.handler(key,baseModuleEvent);
    		}
    	}
    }
    
}

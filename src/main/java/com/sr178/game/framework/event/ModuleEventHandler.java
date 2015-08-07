package com.sr178.game.framework.event;

import java.util.List;

public interface ModuleEventHandler {
	/**
	 * 执行事件
	 * @param baseModuleEvent
	 */
     public void handler(String handlerType,ModuleEventBase baseModuleEvent);
     /**
      * 获取事件类型
      * @return
      */
     public List<String> getHandlerType();
     /**
      * 获取排序字段   small before  big  after  such as  first 1  then 2  then 3
      * @return
      */
     public int order();
}

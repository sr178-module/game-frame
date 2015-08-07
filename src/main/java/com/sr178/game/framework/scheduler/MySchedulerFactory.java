package com.sr178.game.framework.scheduler;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.sr178.game.framework.log.LogSystem;
/**
 * 调度工厂
 * @author mengchao
 *
 */
public class MySchedulerFactory {
	private static SchedulerFactory schedulerFactory;

	private MySchedulerFactory() {
	}

	/**
	 * 调度工厂获取
	 * 
	 * @return
	 */
	private static SchedulerFactory getSchedulerFactoryInstance() {
		if (schedulerFactory == null) {
			schedulerFactory = new StdSchedulerFactory();
		}
		return schedulerFactory;
	}
	/**
	 * 获得一个调度者
	 * @return
	 */
	public static Scheduler getSchedulerInstance(){
		try {
			return MySchedulerFactory.getSchedulerFactoryInstance().getScheduler();
		} catch (SchedulerException e) {
			LogSystem.error(e, "getScheduler error");
		}
		return null;
	}
}

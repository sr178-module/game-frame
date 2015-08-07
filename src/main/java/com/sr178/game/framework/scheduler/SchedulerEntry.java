package com.sr178.game.framework.scheduler;


import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;

import com.sr178.game.framework.log.LogSystem;
/**
 * 调度实现基类  因为调度的原理是会把子类重新new一个出来执行execute方法  所以不能在子类中通过注入手段注入bean 
 * @author mengchao
 *
 */
public abstract class SchedulerEntry implements IScheduler {

	private Scheduler sched;
	private String cronExpression;
	
	private boolean isRunning=false;
	
	public void startup() {
		if(cronExpression==null||cronExpression.equals("")){
       	 throw new NullPointerException(this.getClass().getSimpleName()+" Parama 'cronExpression' is NULL!please set it!");
		}
        JobDetail jobDetail= JobBuilder.newJob(this.getClass()).withIdentity(this.getClass().getSimpleName()+"job",this.getClass().getSimpleName()+"jobgroup").build(); //newJob()//JobDetail//JobDetail(this.getClass().getSimpleName()+"job",this.getClass().getSimpleName()+"jobgroup",this.getClass());
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(this.getClass().getSimpleName()+"cron",this.getClass().getSimpleName()+"crongroup").withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();//new CronTrigger(this.getClass().getSimpleName()+"cron",this.getClass().getSimpleName()+"crongroup");
		sched = MySchedulerFactory.getSchedulerInstance();
	    try {
			sched.scheduleJob(jobDetail,cronTrigger);
		    sched.startDelayed(20);
		    isRunning = true;
		    LogSystem.info(this.getClass().getSimpleName()+" startup!"+"cronExpression=["+cronExpression+"]");
		} catch (SchedulerException e) {
			LogSystem.error(e, "QUARTZ Error!");
		}
	}
	
   public void shutdown(){
	  if(sched!=null&&isRunning){
		  try {
			sched.shutdown();
		} catch (SchedulerException e) {
			LogSystem.error(e, "ERROR IN QUARTZ!");
		}
	  }else{
			LogSystem.error(new NullPointerException(), "QUARTZ IS NEVER START!CAN NOT BE SHUTDOWN!");
	  }
   }
	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
	public Scheduler getSched() {
		return sched;
	}

	public void setSched(Scheduler sched) {
		this.sched = sched;
	}

	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException{
		try{
		   executeJob(jobExecutionContext);
		}catch(Exception e){
			LogSystem.error(e, e.getMessage());
		}
	}
	
	public int cpOrder(){
		return 0;
	}
	/**
	 * 调度执行的内容 需要具体实现
	 */
	public abstract void executeJob(JobExecutionContext jobExecutionContext) throws JobExecutionException;

}

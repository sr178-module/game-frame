package com.sr178.game.framework.log;

import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;




public class ShowMethodRunTime implements MethodInterceptor {
	
	private boolean isRecordLog;
//打印到警告日志中的时间设置
	private long logWarnMaxTime;
//是否记录到数据库中	
	private boolean isRecordData;
	public boolean getIsRecordData() {
		return isRecordData;
	}
	public void setIsRecordData(boolean isRecordData) {
		this.isRecordData = isRecordData;
	}

	public Object invoke(MethodInvocation arg0) throws Throwable{
		Object result = null;
        boolean isException =false;
		Date startTime = new Date();
		try {
			result = arg0.proceed();
		} catch (Exception e) {
			isException =true;
//			LogSystem.error(e, "");
			throw e;
		} finally {
			Date endTime = new Date();
			long distanceTime = endTime.getTime() - startTime.getTime();
			String exp1 = "";
			if (arg0.getArguments() != null && arg0.getArguments().length > 0) {
				for (int i = 0; i < arg0.getArguments().length; i++) {
					exp1 = exp1
							+ "p"
							+ i
							+ ":"
							+ ((arg0.getArguments())[i] == null ? "null"
									: (arg0.getArguments())[i].toString())
							+ ";";
				}
			}
			if (isRecordLog&&distanceTime > logWarnMaxTime) {
				LogSystem.warn(arg0.getThis().getClass().getSimpleName() + "方法"
						+ arg0.getMethod().getName() + "运行时间:" + (distanceTime)
						+ "毫秒" + ",参数列表" + exp1+",是否出现异常:"+isException);
			}
		}
		return result;	
	}
	public long getLogWarnMaxTime() {
		return logWarnMaxTime;
	}
	public void setLogWarnMaxTime(long logWarnMaxTime) {
		this.logWarnMaxTime = logWarnMaxTime;
	}
	public boolean getIsRecordLog() {
		return isRecordLog;
	}
	public void setIsRecordLog(boolean isRecordLog) {
		this.isRecordLog = isRecordLog;
	}
}

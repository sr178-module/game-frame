package com.sr178.game.framework.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
/**
 * 日志系统
 * 
 * @author mengchao
 * 
 */
public class LogSystem {
	private final static Logger logger = Logger.getLogger(Logger.class);
	private final static Logger loggerDebug = Logger.getLogger("FileDebug");
	private final static Logger loggerInfo = Logger.getLogger("FileInfo");
	private final static Logger loggerWarn = Logger.getLogger("FileWarn");
	private final static Logger loggerError = Logger.getLogger("FileError");
	private final static Logger loggeraLOG = Logger.getLogger("Log");
	public static void log(String disc) {
			loggeraLOG.debug(getCaller()+"-"+disc);
	}
	public static void debug(String disc) {
		if(logger.isDebugEnabled()){
			loggerDebug.debug(getCaller()+"-"+disc);
		}
	}
	public static void info(String disc) {
		if(logger.isEnabledFor(Level.INFO)){
			loggerInfo.info(getCaller()+"-"+disc);
		}
	}
	public static void warn(String disc) {
		if(logger.isEnabledFor(Level.WARN)){
			loggerWarn.warn(getCaller()+"-"+disc);
		}
	}
	public static void error(Exception e, String disc) {
		if(logger.isEnabledFor(Level.ERROR)){
			loggerError.error(disc, e);
		}
	}
	public static void error(Throwable t,Object message){
		if(logger.isEnabledFor(Level.ERROR)){
			loggerError.error(message,t);
		}
	}
	public static boolean isEnableFor(Priority level){
		return logger.isEnabledFor(level);
	}
	public static String getCaller() {
		StackTraceElement stack[] = Thread.currentThread().getStackTrace();
		if (stack != null && stack.length >= 4) {
			return "[" + stack[3].getClassName() + "]";
		}
		return "[unknow source..]";
	}
}

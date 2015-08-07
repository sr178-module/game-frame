package com.sr178.game.framework.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.sr178.game.framework.log.LogSystem;

/**
 * 
 * 应用工厂类,主要提供应用实例
 * 
 * @author mengchao
 * 
 */
public class AppPluginFactory implements BeanPostProcessor {

	// 插件列表
	private static List<IAppPlugin> appList = new ArrayList<IAppPlugin>();
	//系统插件
	private static List<ISystemAppPlugin> systemAppList = new ArrayList<ISystemAppPlugin>();

	public static List<IAppPlugin> getAppList() {
		return appList;
	}

	/**
	 * 启动插件，按顺序
	 * @throws Exception 
	 */
	public static void startup() {
		startUpSystemPlugin();
		startUpAppPlugin();
	}
	/**
	 * 启动系统插件
	 */
	public static void startUpSystemPlugin(){
		//先启动系统插件 order越大越靠后
				Collections.sort(systemAppList, new Comparator<ISystemAppPlugin>() {
					@Override
					public int compare(ISystemAppPlugin o1, ISystemAppPlugin o2) {
						if(o1.spOrder()>o2.spOrder()){
							return 1;
						}else if(o1.spOrder()<o2.spOrder()){
							return -1;
						}
						return 0;
					}
				});
				if(systemAppList!=null&&systemAppList.size()>0){
					for(ISystemAppPlugin systemAppPlugin:systemAppList){
						try {
							systemAppPlugin.startup();
							LogSystem.info("插件"+systemAppPlugin.getClass().getSimpleName()+",启动成功,order="+systemAppPlugin.spOrder());
						} catch (Throwable e) {
							LogSystem.error(e, "插件"+systemAppPlugin.getClass().getSimpleName()+",启动失败,order="+systemAppPlugin.spOrder());
							System.exit(0);
						}
					}
				}
	}
	/**
	 * 启动一般应用插件
	 */
	public static void startUpAppPlugin(){
		//启动一般应用插件  order越大越靠后
				List<IAppPlugin> appList = AppPluginFactory.getAppList();
				Collections.sort(appList, new Comparator<IAppPlugin>() {
					@Override
					public int compare(IAppPlugin o1, IAppPlugin o2) {
						if(o1.cpOrder()>o2.cpOrder()){
							return 1;
						}else if(o1.cpOrder()<o2.cpOrder()){
							return -1;
						}
						return 0;
					}
				});
				if (appList != null) {
					for (IAppPlugin application : appList) {
						try {
							application.startup();
							LogSystem.info("插件"+application.getClass().getSimpleName()+",启动成功,order="+application.cpOrder());
						} catch (Throwable e) {
							LogSystem.error(e, "插件"+application.getClass().getSimpleName()+",启动失败,order="+application.cpOrder());
							System.exit(0);
						}
					}
				}
	}
	/**
	 * 关闭插件，按倒序
	 */
	public static void shutdown() {
		//关闭系统插件
		if(systemAppList!=null&&systemAppList.size()>0){
			for(ISystemAppPlugin systemAppPlugin:systemAppList){
				try {
					systemAppPlugin.shutdown();
				} catch (Throwable e) {
					LogSystem.error(e, "");
				}
			}
		}
		
		List<IAppPlugin> appList = AppPluginFactory.getAppList();
		if (appList != null) {
			int size = appList.size();
			for (int i = size - 1; i >= 0; i--) {
				try {
					appList.get(i).shutdown();
				} catch (Throwable e) {
					LogSystem.error(e, "");
				}
			}
		}
	}

	/**
	 * 是在bean加载每个bean之前，都会调用该办法。
	 */
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof IAppPlugin) {
			IAppPlugin appPlugin = (IAppPlugin) bean;
			AppPluginFactory.appList.add(appPlugin);
		}
		if(bean instanceof ISystemAppPlugin){
			ISystemAppPlugin systemAppPlugin = (ISystemAppPlugin)bean;
			AppPluginFactory.systemAppList.add(systemAppPlugin);
		}
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

}

package com.sr178.game.framework.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sr178.game.framework.context.SpringLoad;
import com.sr178.game.framework.context.WebLaod;
import com.sr178.game.framework.log.LogSystem;
import com.sr178.game.framework.plugin.AppPluginFactory;
	/**
	 * web服务器启动应用类
	 * 
	 * @author mengchao
	 * 
	 */
	public class ApplicationListener implements ServletContextListener {

		public void contextInitialized(ServletContextEvent sce) {
			try {
				// 加载应用，主要加载bean
				SpringLoad.getApplicationLoad();
				// 初始化webLoad，主要缓存ServletContext
				WebLaod.init(sce.getServletContext());
				// 启动各个应用插件
				AppPluginFactory.startup();
			} catch (Throwable e) {
				LogSystem.error(e, "");
				System.exit(0);
			}
		}

		public void contextDestroyed(ServletContextEvent sce) {
			// 关闭各个应用插件
			AppPluginFactory.shutdown();
		}

}

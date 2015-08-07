package com.sr178.game.framework.context;

import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import com.sr178.game.framework.plugin.AppPluginFactory;

public class SpringMvcListener extends ContextLoaderListener {
	public void contextInitialized(ServletContextEvent event) {
		 super.contextInitialized(event);
		 ApplicationContext application =  ContextLoaderListener.getCurrentWebApplicationContext();
		 IServiceCache serviceCache = new ServiceCacheClassImpl(application);
		 ServiceCacheFactory.setServiceCacheFactory(serviceCache);
		 AppPluginFactory.startup();
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		 super.contextDestroyed(event);
		 AppPluginFactory.shutdown();
	}
}

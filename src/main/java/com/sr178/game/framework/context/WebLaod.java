package com.sr178.game.framework.context;

import javax.servlet.ServletContext;

/**
 * 
 * web应用加载类，有需要可以在这里装载web的spring的bean文件
 * 
 * 
 * @author mengchao
 * 
 */
public class WebLaod {

	private static WebLaod webLaod;

	private ServletContext servletContext;

	private static boolean isInit = false;

	private WebLaod(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public static void init(ServletContext servletContext) {
		if (isInit == false) {
			webLaod = new WebLaod(servletContext);
			isInit = true;
		}
	}

	public static WebLaod getWebLaod() {
		return webLaod;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}
	
	public String  getInitParamtByName(String name){
		return servletContext.getInitParameter(name);
	}

}

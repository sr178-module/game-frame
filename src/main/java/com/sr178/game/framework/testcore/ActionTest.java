package com.sr178.game.framework.testcore;

import com.sr178.game.framework.context.SpringLoad;
import com.sr178.game.framework.plugin.AppPluginFactory;

import junit.framework.TestCase;
/**
 * 相当于执行一次启动游戏的过程，再运行测试方法（应用测试）
 * 
 * @author mengchao
 * 
 */
public class ActionTest extends TestCase {
	public ActionTest() {
		super();
		SpringLoad.getApplicationLoad();
		// 启动各个应用插件
		AppPluginFactory.startup();
	}
}

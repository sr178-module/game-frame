package com.sr178.game.framework.plugin;


/**
 * 系统插件  在应用插件之前初始化
 * @author mengc
 *
 */
public interface ISystemAppPlugin {
	/**
	 * 当服务器启动时，将调用该办法启动插件
	 */
	public void startup() throws Exception;

	/**
	 * 当服务器关闭时，将调用该办法关闭插件
	 */
	public void shutdown() throws Exception;
	/**
	 * 获得执行顺序
	 * @return
	 */
	public int spOrder();

}

package com.sr178.game.framework.testcore;

import com.sr178.game.framework.context.SpringLoad;

import junit.framework.TestCase;
/**
 * 只加载spring中配置的所有类，再运行测试。(dao测试)
 * @author Administrator
 *
 */
public class DaoTest extends TestCase {

	public DaoTest() {
		super();
		SpringLoad.getApplicationLoad();
	}
}

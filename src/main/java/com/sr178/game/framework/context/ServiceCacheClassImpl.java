package com.sr178.game.framework.context;

import java.util.Collection;
import java.util.Map;

import org.springframework.context.ApplicationContext;

/**
 * service缓存，该类交给spring容器管理，可以�?�过名字获取service
 * 
 * @author mengchao
 * 
 */
public class ServiceCacheClassImpl implements IServiceCache {

	private ApplicationContext applicationContext;

	public ServiceCacheClassImpl(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public <T> T getService(Class<T> serviceClass) {
		Map<String, T> map = applicationContext.getBeansOfType(serviceClass);
		T t = null;
		if (map != null) {
			Collection<T> cl = map.values();
			if (cl.size() == 1) {
				for (T t2 : cl) {
					t = t2;
					break;
				}
			} else if (cl.size() == 0) {
				throw new IllegalStateException("该service还没有配�");
			} else {
				throw new IllegalStateException("该service的配置多�于1�个");
			}
		}else{
			throw new IllegalStateException("该service还没有配�");
		}
		return t;
	}
	
	public <T> Collection<T>  getServiceList(Class<T> serviceClass) {
		Map<String, T> map = applicationContext.getBeansOfType(serviceClass);
		if (map != null) {
			 Collection<T> cl = map.values();
			 return cl;
		}
		return null;
	}
	public Object getBeanById(String id){
		return applicationContext.getBean(id);
	}
	@Override
	public <T> T getBeanById(String id, Class<T> serviceClass) {
		return applicationContext.getBean(id, serviceClass);
	}

}

package ld.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器：初始化前后进行处理工作
 * 将后置处理器加入到容器当中
 * @author Administrator
 *
 */
@Component
public class MyNeamPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyNeamPostProcessor.postProcessBeforeInitialization()");
		// TODO Auto-generated method stub
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyNeamPostProcessor.postProcessAfterInitialization()");
		// TODO Auto-generated method stub
		return bean;
	}
	
}

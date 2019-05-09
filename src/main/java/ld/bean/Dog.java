package ld.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Dog implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	public Dog() {
		System.out.println("Dog.Dog()");
	}

	@PostConstruct
	public void init() {
		System.out.println("Dog.init()");
	}

	@PreDestroy
	public void detory() {
		System.out.println("Dog.detory()");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}

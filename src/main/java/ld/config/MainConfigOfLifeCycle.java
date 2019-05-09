package ld.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.ld.bean.Car;
import com.ld.bean.Dog;

/**
 * bean 的生命周期 bean创建---初始化---销毁 容器管理bean的生命周期
 * 我们可以自定义初始化和销毁方法，容器在bean进行到生命周期的时候来调用我们自定义的初始化和销毁方法
 * 
 * 构造（对象创建） 单实例：在容器启动的时候创建对象 
 * 				多实例：在每次获取的时候创建对象
 * 
 * BeanPostProcessor.postProcessBeforeInitialization
 * 初始化:对象创建完成，并赋值好，调用初始化方法
 * BeanPostProcessor.postProcessAfterInitialization
 * 销毁：容器关闭的时候，进行销毁
 * 
 * 1).指定初始化方法
 * 		初始化:对象创建完成，并赋值好，调用初始化方法
 * 		销毁：容器关闭的时候，进行销毁
 * 		单实例：容器关闭的时候
 * 		多实例：容器不会管理这个bean，容器不会调用销毁方法
 * 
 * 2).通过bean实现InitializingBean（定义初始化逻辑），DisposableBean（定义销毁）
 * 
 * 3).可以使用JSR250:
 * 		@PostConstruct:在bean创建完成并且属性赋值完成；来执行初始化方法
 * 		@PreDestroy:在容器销毁bean之前通知我们进行清理工作
 * 
 * 4).BeanPostProcessor[interface]:bean的后置处理器；
 * 		在bean初始化前后进行一些处理工作
 * 		postProcessBeforeInitialization：在初始化之前工作
 * 		postProcessAfterInitialization：在初始化之后工作
 * 
 * Spring底层对BeanPostProcessor的使用
 * 		bean赋值，注入其他组件，@Autowired，生命周期注解功能，@Async,xxx BeanPostProcessor
 */
@ComponentScan("com.ld.bean")
@Configuration
public class MainConfigOfLifeCycle {

	@Scope("prototype")
	@Bean(initMethod = "init", destroyMethod = "detory")
	public Car car() {
		return new Car();
	}
	
//	@Bean
//	public Dog dog() {
//		return new Dog();
//	}

}

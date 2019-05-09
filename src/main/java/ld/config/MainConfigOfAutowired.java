package ld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.ld.bean.Car;
import com.ld.bean.Color;
import com.ld.dao.BookDao;

/**
 * 自动装配： Spring利用依赖注入（DI），完成对IOC容器中各个组件的依赖关系赋值
 * 
 * 1).@Autowried:自动注入:
 * 		1.默认优先按照类型去容器中找对应的组件：applicationContext.getBean(BookDao.class);
 * 		2.如果找到多个相同类型的组件,在将属性的名称作为的id去容器中查找
 * 										applicationContext.getBean("bookDao");
 * 		3.@Qualifier("bookDao")：使用 @Qualifier 指定需要装配的组件的id，而不是使用属性名
 * 		4.自动装配一定要将属性赋值好，没有就会报错；
 * 			可以使用@Autowried(required=false);
 * 		5.@Primary:让Spring进行自动装配的时候，默认使用首选 bean;
 * 				也可以继续使用@Qualifier指定需要装配的bean的名字
 * 		
 * 		BookService{
 * 			@Autowried BookDao bookDao; 
 * 		}
 * 
 * 2).Spring还支持使用@Resource(JSR250)和Inject(JSR330)[java规范的注解]
 * 		@Resource :
 * 			可以和@Autowried 一样实现自动装配：默认是按照组件名称进行装配的
 * 			没有能支持@Primary功能，没有支持 @Autowried(required=false)
 * 		@Inject:
 * 			需要导入javax.inject的包，和 @Autowried的功能一样 ，没有required=false 的功能
 * 	 @Autowried ：spring 定义的规范 , @Resource, @Inject 都是java规范
 * 
 * 3).@Autowried 可以在构造器，参数，方法，属性上使用；都是从容器中获取组件的值
 * 		1.标注在方法位置：@Bean+方法参数：参数从容器中获取，默认不写@Autowried，都能自动装配
 * 		2.标注在构造器上：如果组件只有一个有参构造器，这个有参构造器的 @Autowried 可以省略，参数位置的组件还是可以自动从容器中获取
 * 		3.可以放在参数位置：
 * 
 * 4).自定义组件想要使用Spring容器底层的一些组件（ApplicationContext，BeanFactory，xxx）；
 * 		自定义组件实现xxxAware；在创建对象的时候，会调用接口规定的方法注入相关的组件；Aware
 * 		把Spring底层一些注入到自定义的Bean中；
 * 		xxxAware：功能使用xxxProcessor；
 * 			ApplicationContextAware==>>ApplicationContextAwareProcessor;
 */
@Configuration
@ComponentScan({ "com.ld.controller", "com.ld.dao", "com.ld.service", "com.ld.bean" })
public class MainConfigOfAutowired {

	@Primary
	@Bean(value = "bookDao2")
	public BookDao bookDao() {
		BookDao bookDao = new BookDao();
		bookDao.setLable("2");
		return bookDao;
	}
	
	/**
	 * @Bean 标注的方法创建对象的时候，方法参数的值从容器中获取
	 * @param car
	 * @return
	 */
	@Bean
	public Color color(Car car) {
		Color color = new Color();
		color.setCar(car);
		return color; 
	}

}

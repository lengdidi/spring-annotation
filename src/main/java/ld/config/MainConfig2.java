package ld.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.ld.bean.Color;
import com.ld.bean.ColorFactoryBean;
import com.ld.bean.Person;
import com.ld.bean.Red;
import com.ld.condition.LinuxCondition;
import com.ld.condition.MyImportBeanDefinitionRegistrar;
import com.ld.condition.MyImportSelector;
import com.ld.condition.WindowsCondition;
import com.ld.service.BookService;

//a 类中组件统一设置。满足这个条件，这个类中配置的bean注册才能生效
//@Conditional({ LinuxCondition.class })
@Configuration
@Import({ Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class })
//快速导入组件：id默认是组件的全类名
public class MainConfig2 {
	/**
	 * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE prototype
	 * @see ConfigurableBeanFactory#SCOPE_SINGLETON singleton
	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST
	 *      request
	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION
	 *      session
	 * @Scope 调整作用域 prototype 多实例的：ioc容器启动并不会去调用创建对象的方法，每次获取的时候才会去调用创建对象的方法
	 *        singleton 单实例的（默认的）：ioc容器启动的时候就会调用方法创建实例 request 同一次请求创建一个实例 session
	 *        同一个session创建一个实例
	 * 
	 *        懒加载： 单实例bean：默认在容器启动的时候创建对象 懒加载：容器启动不创建对象，每一次获取bean创建对象，并初始化
	 */
	// @Scope("prototype")
	@Lazy
	@Bean(value = "person")
	public Person person() {
		System.out.println("给容器中添加person");
		return new Person("张三", 25);
	}

	/**
	 * @Conditional : 按照一定的条件进行判断，满足条件给容器注册bean
	 */
	@Conditional({ WindowsCondition.class })
	@Bean("bill")
	public Person person01() {
		return new Person("Bill Gates", 62);
	}

	@Conditional({ LinuxCondition.class })
	@Bean("linus")
	public Person person02() {
		return new Person("linus", 48);
	}

	/**
	 * 给容器中注册组件 a).[包扫描+组件标注注解] b).@Bean[导入第三方包里面的组件] c).@Import[快速给容器中导入一个组件]
	 * 1).@Import(要导入到容器中的组件)；容器就会自动注册这个组件，id默认是全类名 2).ImportSelect:返回需要导入的组件的全类名数组
	 * 3).ImportBeanDefinitionRegistrar:手动注册bean到容器中
	 * 4).使用spring提供的FactoryBean(工厂bean):
	 * 		1).默认获取到的是工厂Bean本身，我们需要给id前面加一个&
	 * 			&colorFactoryBean
	 */
	@Bean
	public ColorFactoryBean colorFactoryBean() {
		return new ColorFactoryBean();
	}
}

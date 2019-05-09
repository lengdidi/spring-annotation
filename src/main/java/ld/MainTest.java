package ld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ld.bean.Person;
import com.ld.config.MainConfig2;

public class MainTest {
	public static void main(String[] args) {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
//		Person person = (Person) ctx.getBean("person");
//		System.out.println(person);

		AnnotationConfigApplicationContext atx = new AnnotationConfigApplicationContext(MainConfig2.class);
		Person person = (Person) atx.getBean("person");
		System.out.println(person);
		
		String[] beanNamesForType = atx.getBeanNamesForType(Person.class);
		for (String string : beanNamesForType) {
			System.out.println(string);
		}
	}
}

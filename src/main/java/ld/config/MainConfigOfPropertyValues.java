package ld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.ld.bean.Person;

@PropertySource(value = { "classpath:/person.properties" })
@Configuration
public class MainConfigOfPropertyValues {

	@Bean
	public Person person() {
		return new Person();
	}
}

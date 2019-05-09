package ld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.ld.bean.Person;
import com.ld.service.BookService;

@Configuration

@ComponentScans(value = {
		@ComponentScan(value = "com.ld", includeFilters = {
//				@Filter(type = FilterType.ANNOTATION, classes = { Controller.class }),
//				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { BookService.class }),
				@Filter(type = FilterType.CUSTOM, classes = { MyTypeFilter.class }) }, useDefaultFilters = false)
		}
//		,
//		@ComponentScan(value = "com.ld", excludeFilters = {
//				@Filter(type = FilterType.ANNOTATION, classes = { Service.class }) }, useDefaultFilters = false) }
)
//excludeFilters排除那些    Filter[] 
//includeFilters只包含那些   Filter[]
//FilterType.ANNOTATION:按照注解
//FilterType.ASSIGNABLE_TYPE:按照给定的类型
//FilterType.ASPECTJ:按照ASPECTJ过滤规则
//FilterType.REGEX:按照REGEX过滤规则
//FilterType.CUSTOM:按照自定义规则
public class MainConfig {
	@Bean(value = "person")
	public Person person1() {
		return new Person("list", 20);
	}
}

package ld.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import com.ld.bean.Yellow;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Profile： Spring为我们提供的可以根据当前环境，动态的激活和切换一系列的组件
 * 
 * @Profile:指定组件在哪个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件
 * 		1.加了环境表示的bean，只有这个环境被激活的时候才能被注册到容器中。默认是default环境
 * 		2.写在配置类上，只有是指定的环境的时候，整个配置类里的所有配置才能生效
 * 		3.没有标注环境标识的bean在任何环境下都是加载的
 */
//@Profile("test")
@PropertySource("classpath:/db.properties")
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

	@Value("${db.user}")
	private String user;

	private StringValueResolver valueResolver;

	private String driverClass;

	@Profile("test")
	@Bean
	public Yellow yellow() {
		return new Yellow();
	}

	@Profile("default")
	@Bean("prodDataSource")
	public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(pwd);
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/smbms");
		dataSource.setDriverClass(driverClass);
		return dataSource;
	}

	@Profile("test")
	@Bean("testDataSource")
	public DataSource dataSourceTest() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("root");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		return dataSource;
	}

	@Profile("dev")
	@Bean("devDataSource")
	public DataSource dataSourceDev() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("root");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/jpa");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		return dataSource;
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		this.valueResolver = resolver;
		this.driverClass = valueResolver.resolveStringValue("${db.driverClass}");
	}
}

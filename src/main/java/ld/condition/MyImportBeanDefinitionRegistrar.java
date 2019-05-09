package ld.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.ld.bean.RainBow;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	/**
	 * AnnotationMetadata ： 当前类的注解信息 BeanDefinitionRegistry：BeanDefinition：注册类
	 * 把所有需要添加到容器中的bean： 调用BeanDefinitionRegistry.registerBeanDefinition手工注册进来
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		boolean containsBeanDefinition = registry.containsBeanDefinition("com.ld.bean.Red");
		boolean containsBeanDefinition2 = registry.containsBeanDefinition("com.ld.bean.Blue");

		if (containsBeanDefinition && containsBeanDefinition2) {
			RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
			registry.registerBeanDefinition("rainBow", rootBeanDefinition);
		}
	}

}

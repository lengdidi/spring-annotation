package ld.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.ld.aop.LogAspects;
import com.ld.aop.MathCalculator;

/**
 * AOP:
 * 		指定程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式
 * 
 * 1).导入aop模块：Spring AOP(spring-aspects)
 * 2).定义一个业务逻辑类 (MathCalculator)；在业务逻辑运行的时候降日志打印（方法运行之前，方法运行的时候，方法运行完毕，方法出现异常），
 * 3).定义一个日志切面类(LogAspects):切面类里面的方法需要动态感知MathCalculator.div运行到哪里然后执行
 * 		通知方法：
 * 			前置(@Before)：logStart:在目标方法div()运行之前运行
 * 			后置(@After)：logEnd在目标方法div()运行之后运行
 * 			返回(@AfterRetuning)：logReturn在目标方法div()正常返回之后运行
 * 			异常(@AfterThowing)：logException在目标方法div()出现异常之后运行
 * 			环绕(@Around)：动态代理，手动推进目标方法运行（joinPoint.procced()）
 * 4).给切面类的目标方法标注何时何地的运行（通知注解）
 * 5).将切面类和业务逻辑类（目标方法类）都加入到容器中
 * 6).必须告诉那个类是切面类（给切面类加一个注解 ： @Aspect）
 * 7).给配置类中加 @EnableAspectJAutoProxy [开启基于注解的aop模式]
 * 		在Spring中很多的 @EnableXXX;开启XXX
 * 
 * 三部：
 * 	1).将业务逻辑组件和切面都加入到容器中：告诉Spring哪个是切面类 @Aspect
 * 	2).在切面类上的每一个通知方法上标注通知注解，告诉Spring何时何地运行(切入点表达式)
 * 	3).开启基于aop模式的注解 @EnableAspectJAutoProxy】
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {
	
	@Bean
	public MathCalculator calculator() {
		return new MathCalculator();
	}
	
	@Bean
	public LogAspects logAspects() {
		return new LogAspects();
	}
	
}

package ld.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 切面类
 * 
 * @Aspect 告诉spring这个是切面类
 */
@Aspect
public class LogAspects {

	@Pointcut("execution(public int com.ld.aop.MathCalculator.div(int, int))")
	public void pointCut() {
	}

	// @Before 在目标方法之前切入
	@Before("pointCut()")
	public void logStart(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		System.out.println(joinPoint.getSignature().getName() + "运行。。。。。。@Before的参数列表是：{" + Arrays.asList(args) + "}");
	}

	@After("com.ld.aop.LogAspects.pointCut()")
	public void logEnd(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().getName() + "结束");
	}

	@AfterReturning(value = "pointCut()", returning = "result")
	public void logReturn(JoinPoint joinPoint, Object result) {
		System.out.println(joinPoint.getSignature().getName() + "正常返回。。。@AfterReturning运行结果是：{" + result + "}");
	}

	@AfterThrowing(value = "pointCut()", throwing = "e")
	public void logException(JoinPoint joinPoint, Exception e) {
		System.out.println(joinPoint.getSignature().getName() + "异常。。。异常信息：{" + e + "}");
	}
}

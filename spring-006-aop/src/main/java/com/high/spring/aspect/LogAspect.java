package com.high.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 *
 * @author programmer
 * @version 1.0
 * @since 1.0
 */
@Component
@Aspect
// Order排序，会决定切面的执行顺序，数字越小，优先级越高
@Order(0)
public class LogAspect {

    // 定义通用切点表达式
    @Pointcut("execution(* com.high.spring.service.impl.*.*(..))")
    public void servicePointcut() {
    }

    // 切面 = 通知 + 切点
    // @Before是前置通知，()需要在括号内写切点表达式
    // execution([修饰符] 返回值类型 [全限定类名] 方法名(形参列表) [异常])
    @Before("servicePointcut()")
    public void beforeLog(JoinPoint joinPoint) {
        System.out.println("前置日志记录。。。");
        // 在通知方法中可以获取连接点对象，这个对象是Spring自动传入的
        // JoinPoint对象可以获取方法的具体信息
        System.out.println("目标方法签名：" + joinPoint.getSignature());
        System.out.println("目标方法名：" + joinPoint.getSignature().getName());
    }

    // 后置通知：在方法return后通知
    @AfterReturning("servicePointcut()")
    public void afterReturningLog() {
        System.out.println("后置日志记录。。。");
    }

    // 环绕通知：环绕通知的范围是最大的，前环绕通知在前置通知之前，后环绕通知在最终通知之后
    // 后环绕通知在方法抛出异常时不执行
    @Around("servicePointcut()")
    public void aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("前环绕日志记录。。。");
        joinPoint.proceed();
        System.out.println("后环绕日志记录。。。");
    }

    // 异常通知：在方法抛出异常时通知
    @AfterThrowing("servicePointcut()")
    public void afterThrowingLog() {
        System.out.println("异常日志记录。。。");
    }

    // 最终通知：无论是否发生异常，在方法结束后通知
    @After("servicePointcut()")
    public void afterLog() {
        System.out.println("最终日志记录。。。");
    }

}

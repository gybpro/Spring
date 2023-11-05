package com.high.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 安全切面
 *
 * @author programmer
 * @version 1.0
 * @since 1.0
 */
@Component
@Aspect
// Order排序，会决定切面的执行顺序，数字越小，优先级越高
@Order(1)
public class SecurityAspect {

    // 通用切点表达式可以跨类使用，但要加上全限定类名
    @Before("com.high.spring.aspect.LogAspect.servicePointcut()")
    public void beforeSecurity() {
        System.out.println("前置权限认证。。。");
    }

}

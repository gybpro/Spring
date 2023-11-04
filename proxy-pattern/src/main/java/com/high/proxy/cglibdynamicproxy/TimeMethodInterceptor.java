package com.high.proxy.cglibdynamicproxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 方法计时拦截器
 *
 * @author programmer
 * @version 1.0
 * @since 1.0
 */
public class TimeMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        long start = System.currentTimeMillis();
        Object retValue = methodProxy.invokeSuper(target, args);
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "执行累计耗时" + (end - start));
        return retValue;
    }
}

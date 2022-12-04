package com.high.proxy.jdkdynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 计时调用处理程序/调用事件句柄计时类/调用事件发生时计时类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class TimeInvocationHandler implements InvocationHandler {
    // 关联目标对象
    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    /*
    InvocationHandler中invoke方法的三个参数：
        第一个参数：代理对象
        第二个参数：目标对象调用的目标方法
        第三个参数：目标方法的参数列表
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = method.invoke(target, args);
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "执行累计耗时" + (end - start));
        return obj;
    }
}

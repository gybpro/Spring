package com.high.proxy.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * CGLIB动态代理工具类
 *
 * @author programmer
 * @version 1.0
 * @since 1.0
 */
public class CgLibDynamicProxyUtils {
    private CgLibDynamicProxyUtils() {

    }

    @SuppressWarnings("unchecked")
    public static <T> T newProxy(T target, MethodInterceptor interceptor) {
        // CGLIB库中的核心对象，用于生成代理类
        Enhancer enhancer = new Enhancer();

        // 设置目标类，即父类
        enhancer.setSuperclass(target.getClass());

        // 设置回调拦截器/方法拦截器
        enhancer.setCallback(interceptor);

        // 创建并返回代理对象
        return (T) enhancer.create();
    }
}

package com.high.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Spring配置类
 *
 * @author programmer
 * @version 1.0
 * @since 1.0
 */
@Configuration // 代替XML配置文件
@ComponentScan({ "com.high.spring" }) // 组件扫描
@EnableAspectJAutoProxy // 启用自动代理机制
// @EnableAspectJAutoProxy(proxyTargetClass = true) // 只用CGLIB动态代理
public class SpringConfig {
}

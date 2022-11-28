package com.high.myspringframework.context.annotation;

import com.high.myspringframework.beans.factory.support.DefaultSingletonBeanRegistry;
import com.high.myspringframework.context.ApplicationContext;
import com.high.myspringframework.context.support.ClassPathXmlApplicationContext;
import com.high.myspringframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;

/**
 * MySpring容器加载注解配置类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class AnnotationConfigApplicationContext implements ApplicationContext {
    private static final Logger logger = LoggerFactory.getLogger(ClassPathXmlApplicationContext.class);

    private final DefaultSingletonBeanRegistry registry = new DefaultSingletonBeanRegistry();

    public AnnotationConfigApplicationContext(String packageName) {
        String path = packageName.replace(".", "/");
        // 获取系统绝对路径
        URL url = ClassLoader.getSystemClassLoader().getResource(path);
        // 获取包/文件夹
        // 此处可以写断言工具类
        assert url != null;
        File file = new File(url.getPath());
        // 获取包下所有类
        File[] files = file.listFiles();
        // 遍历所有类
        assert files != null;
        Arrays.stream(files).forEach(f -> {
            // 获取全限定类名
            String className = packageName + "." + f.getName().split("\\.")[0];
            try {
                // 获取类字节码
                Class<?> clazz = Class.forName(className);
                // 判断是否有标记注解
                if (clazz.isAnnotationPresent(Component.class)) {
                    Component component = clazz.getAnnotation(Component.class);
                    String beanName = component.value();
                    Object bean = clazz.getDeclaredConstructor().newInstance();
                    registry.registerSingleton(beanName, bean);
                    System.out.println(registry.getSingletonObjects());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public Object getBean(String beanName) {
        return registry.getSingleton(beanName);
    }
}

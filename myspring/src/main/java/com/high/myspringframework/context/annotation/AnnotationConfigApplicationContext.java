package com.high.myspringframework.context.annotation;

import com.high.myspringframework.beans.factory.annotation.Qualifier;
import com.high.myspringframework.beans.factory.annotation.Value;
import com.high.myspringframework.beans.factory.support.DefaultSingletonBeanRegistry;
import com.high.myspringframework.context.ApplicationContext;
import com.high.myspringframework.context.support.ClassPathXmlApplicationContext;
import com.high.myspringframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 属性赋值(简单实现)
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
                    Object obj = registry.getSingleton(beanName);
                    Field[] fields = clazz.getDeclaredFields();
                    Arrays.stream(fields).forEach(field -> {
                        try {
                            Class<?> fieldType = field.getType();
                            String fieldName = field.getName();
                            String methodName = "set" + fieldName.toUpperCase().charAt(0) +
                                    fieldName.substring(1);
                            Method method = clazz.getDeclaredMethod(methodName, fieldType);
                            if (field.isAnnotationPresent(Value.class)) {
                                Value annotation = field.getAnnotation(Value.class);
                                String value = annotation.value();
                                if (value != null) {
                                    Object realValue = null;
                                    String simpleName = fieldType.getSimpleName();
                                    switch (simpleName) {
                                        case "byte", "Byte" -> realValue = Byte.valueOf(value);
                                        case "short", "Short" -> realValue = Short.valueOf(value);
                                        case "int", "Integer" -> realValue = Integer.valueOf(value);
                                        case "long", "Long" -> realValue = Long.valueOf(value);
                                        case "float", "Float" -> realValue = Float.valueOf(value);
                                        case "boolean", "Boolean" -> realValue = Boolean.valueOf(value);
                                        case "char", "Character" -> realValue = value.charAt(0);
                                        case "String" -> realValue = value;
                                    }
                                    method.invoke(obj, realValue);
                                }
                            }
                            if (field.isAnnotationPresent(Qualifier.class)) {
                                Qualifier qualifier = field.getAnnotation(Qualifier.class);
                                String ref = qualifier.value();
                                method.invoke(obj, registry.getSingleton(ref));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
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

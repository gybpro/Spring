package com.high.test;

import com.high.annotation.Component;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class AnnotationTest {
    @Test
    public void testAnnotation() throws ClassNotFoundException {
        // 获取类字节码
        Class<?> userClass = Class.forName("com.high.bean.User");
        // 获取注解
        Component annotation = userClass.getAnnotation(Component.class);
        // 获取注解的属性值
        String value = annotation.value();
        System.out.println(value);
    }

    @Test
    public void testPackageScan() {
        // 存放bean对象的集合
        Map<String, Object> singletonObjects = new HashMap<>();
        // 包路径
        String packageName = "com.high.bean";
        // 变为带/的路径
        String path = packageName.replace(".", "/");
        // 获取在当前系统下的绝对路径(能适配系统)
        URL url = ClassLoader.getSystemClassLoader().getResource(path);
        // 读取整个包/文件夹
        assert url != null;
        File file = new File(url.getPath());
        // 获取包下所有的文件
        File[] files = file.listFiles();
        // 遍历所有的文件
        assert files != null;
        Arrays.stream(files).forEach(f -> {
            // 获取类的全限定名
            // 因为.在正则表达式中匹配除\n和\r之外的所有字符，所以要用\.转义，
            // 但\在Java中也为转义字符，也需要转义，所以用\\.
            String className = packageName + "." + f.getName().split("\\.")[0];
            // System.out.println(className);
            try {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Component.class)) {
                    Component component = clazz.getAnnotation(Component.class);
                    String beanName = component.value();
                    Object bean = clazz.getDeclaredConstructor().newInstance();
                    singletonObjects.put(beanName, bean);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(singletonObjects);
    }
}

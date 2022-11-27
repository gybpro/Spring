package com.high.myspringframework.context.support;

import com.high.myspringframework.beans.factory.support.DefaultSingletonBeanRegistry;
import com.high.myspringframework.context.ApplicationContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

/**
 * MySpring容器加载类路径XML配置文件类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {
    private static final Logger logger = LoggerFactory.getLogger(ClassPathXmlApplicationContext.class);

    private final DefaultSingletonBeanRegistry registry = new DefaultSingletonBeanRegistry();

    /**
     * 构造方法：根据类路径加载XML配置文件，解析并初始化所有的Bean对象
     *
     * @param configLocation myspring配置文件路径
     */
    public ClassPathXmlApplicationContext(String configLocation) {
        // 解析XML配置文件，实例化Bean，将Bean曝光(存放到singletonObjects集合中)
        try {
            // 获取XML文件读取对象
            SAXReader reader = new SAXReader();
            // 获取配置文件输入流
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(configLocation);
            // 读取文件
            Document document = reader.read(in);
            // 读取所有bean标签
            List<Node> nodes = document.selectNodes("//bean");
            // 遍历bean标签，实例化Bean并存入singleObjects
            nodes.forEach(node -> {
                try {
                    // 解析并实例化对象
                    // System.out.println(node.toString());// 三个Element对象
                    // 向下转型为Element，子类接口Element方法更丰富
                    Element element = (Element) node;
                    // 获取id属性
                    String beanName = element.attributeValue("id");
                    // 获取class属性
                    String className = element.attributeValue("class");
                    // 打印日志
                    // logger.info(beanName);
                    // logger.info(className);
                    // 反射实例化对象
                    Class<?> clazz = Class.forName(className);
                    Object obj = clazz.getDeclaredConstructor().newInstance();
                    // 将对象存入singletonObjects集合
                    registry.registerSingleton(beanName, obj);
                    // logger.info(registry.getSingletonObjects().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // 再次遍历进行属性赋值(源码并非如此，这里只是简单实现)
            // 再次遍历是为了将实例化Bean和属性赋值清晰的分离，解决循环依赖问题
            nodes.forEach(node -> {
                try {
                    // 向下转型为Element，子类接口Element方法更丰富
                    Element element = (Element) node;
                    // 获取id属性
                    String beanName = element.attributeValue("id");
                    // 根据beanName从singletonObjects集合中获取Bean对象
                    Object obj = registry.getSingleton(beanName);
                    // 获取对象字节码
                    Class<?> clazz = obj.getClass();
                    // 获取bean标签下的所有property子标签
                    List<Element> properties = element.elements("property");
                    // 遍历properties集合
                    properties.forEach(property -> {
                        try {
                            // 获取属性名
                            String propertyName = property.attributeValue("name");
                            // 获取属性类型
                            Class<?> propertyType = clazz.getDeclaredField(propertyName).getType();
                            // 获取set方法
                            String methodName = "set" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                            Method method = clazz.getDeclaredMethod(methodName, propertyType);
                            // 获取属性值
                            String value = property.attributeValue("value");
                            String ref = property.attributeValue("ref");
                            // 根据不同类型属性赋值(源码并非如此，这里只是简单实现)
                            // 这里可以像Spring实现一个BeanUtils，有兴趣可以自己实现
                            if (value != null) {
                                Object realValue = null;
                                String simpleName = propertyType.getSimpleName();
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
                            if (ref != null) {
                                method.invoke(obj, registry.getSingleton(ref));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanName) {
        return registry.getSingleton(beanName);
    }
}

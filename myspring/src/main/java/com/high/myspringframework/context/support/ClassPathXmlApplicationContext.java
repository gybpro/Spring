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
            // 遍历bean标签
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
                    logger.info(beanName);
                    logger.info(className);
                    // 反射实例化对象
                    Class<?> clazz = Class.forName(className);
                    Object obj = clazz.getDeclaredConstructor().newInstance();
                    // 将对象存入singletonObjects集合
                    registry.registerSingleton(beanName, obj);
                    logger.info(registry.getSingletonObjects().toString());
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
        return null;
    }
}

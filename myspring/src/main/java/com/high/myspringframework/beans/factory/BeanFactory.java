package com.high.myspringframework.beans.factory;

/**
 * Bean工厂接口
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public interface BeanFactory {
    /**
     * 根据bean name/id获取对应的Bean对象
     * @param beanName myspring.xml配置文件中bean标签的id属性值
     * @return 对应的单例Bean对象
     */
    Object getBean(String beanName);
}

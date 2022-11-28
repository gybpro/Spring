package com.high.myspringframework.beans.factory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 引用类型注入ByName注解
 * 简单实现
 * 此处省略@Autowired注解
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Qualifier {
    /**
     * bean name/id
     */
    String value();
}

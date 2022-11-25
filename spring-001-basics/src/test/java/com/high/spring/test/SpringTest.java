package com.high.spring.test;

import com.high.spring.bean.*;
import com.high.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class SpringTest {
    @Test
    public void testCreateBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        /* Object userBean = applicationContext.getBean("userBean");
        User userBean = (User) applicationContext.getBean("userBean");*/
        // 在Spring容器启动，配置文件加载的时候Spring就已经创建好了对象
        System.out.println("还没有getBean");
        User userBean = applicationContext.getBean("userBean", User.class);
        System.out.println(userBean);
    }

    @Test
    public void testDI() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.create();
    }

    @Test
    public void testSetDI() {
        // 内外部注入、注入简单类型
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        // 级联属性赋值
        Student student = applicationContext.getBean("student", Student.class);
        System.out.println(student);

        // 注入数组、List集合、Set集合、Map集合
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);

        // 注入Properties集合
        AttributeConfig attributeConfig = applicationContext.getBean("attributeConfig", AttributeConfig.class);
        System.out.println(attributeConfig);

        // 注入null
        Clazz clazzNull = applicationContext.getBean("clazzNull", Clazz.class);
        System.out.println(clazzNull.getName());

        // 注入空字符串
        Clazz clazzEmptyString = applicationContext.getBean("clazzEmptyString", Clazz.class);
        System.out.println(clazzEmptyString);

        // 注入特殊字符
        Clazz clazzSpecialChar = applicationContext.getBean("clazzSpecialChar", Clazz.class);
        System.out.println(clazzSpecialChar);

        // p命名空间注入
        Clazz clazzP = applicationContext.getBean("clazzP", Clazz.class);
        System.out.println(clazzP);

        // c命名空间注入
        Clazz clazzC = applicationContext.getBean("clazzC", Clazz.class);
        System.out.println(clazzC);

        // util命名空间配置复用
        AttributeConfig config1 = applicationContext.getBean("config1", AttributeConfig.class);
        System.out.println(config1);
        AttributeConfig config2 = applicationContext.getBean("config2", AttributeConfig.class);
        System.out.println(config2);

        // 自动注入/装配，byName和byType
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.create();

        // 引入外部属性配置文件
        AttributeConfig dataSource = applicationContext.getBean("dataSource", AttributeConfig.class);
        System.out.println(dataSource);
    }

    @Test
    public void testScope() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        System.out.println("还没有getBean()");
        ScopeBean b1 = applicationContext.getBean("scopeBean", ScopeBean.class);
        ScopeBean b2 = applicationContext.getBean("scopeBean", ScopeBean.class);
        System.out.println(b1);
        System.out.println(b2);
        new Thread(() -> {
            ScopeBean b3 = applicationContext.getBean("scopeBean", ScopeBean.class);
            ScopeBean b4 = applicationContext.getBean("scopeBean", ScopeBean.class);
            System.out.println(b3);
            System.out.println(b4);
        }).start();
    }

    @Test
    public void testBeanInstance() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User userBean = applicationContext.getBean("userBean", User.class);
        System.out.println("userBean：" + userBean);
    }
}

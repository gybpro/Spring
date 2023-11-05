package com.high.spring.test;

import com.high.spring.bean.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Spring整合JUnit5测试
 *
 * @author programmer
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:spring.xml")
public class SpringJUnit5Test {

    @Resource
    private User user;

    // 注意：JUnit5的@Test注解和JUnit4的@Test注解是不一样的
    @Test
    public void testUser() {
        System.out.println(user.getName());
    }

}

package com.high.spring.jdbc.test;

import com.high.spring.jdbc.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class JDBCTest {
    @Test
    public void testInsert() {
        // 启动Spring容器加载配置文件
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        // 获取JdbcTemplate对象
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        // 编写SQL语句
        String sql = "insert into t_user(id, username, password) values(?, ?, ?)";
        // 执行SQL语句(增删改在Spring JDBC中都用update提交执行)
        int count = jdbcTemplate.update(sql, null, "李四", "123456");
        System.out.println("插入的记录条数：" + count);
    }

    @Test
    public void testUpdate(){
        // 获取JdbcTemplate对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        // 执行更新操作
        String sql = "update t_user set username = ?, password = ? where id = ?";
        int count = jdbcTemplate.update(sql, "张三丰", 123456, 5);
        System.out.println("更新的记录条数：" + count);
    }

    @Test
    public void testDelete(){
        // 获取JdbcTemplate对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        // 执行delete
        String sql = "delete from t_user where id = ?";
        int count = jdbcTemplate.update(sql, 6);
        System.out.println("删除了几条记录：" + count);
    }

    @Test
    public void testSelectOne(){
        // 获取JdbcTemplate对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        // 执行select
        String sql = "select id, username, password from t_user where id = ?";
        // 自动映射属性名与列名一致或均为映射经典命名即可匹配
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 3);
        System.out.println(user);
    }

    @Test
    public void testSelectOneValue(){
        // 获取JdbcTemplate对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        // 执行select
        String sql = "select count(1) from t_user";
        Integer count = jdbcTemplate.queryForObject(sql, int.class); // 这里用Integer.class也可以
        System.out.println("总记录条数：" + count);
    }

    @Test
    public void testSelectAll(){
        // 获取JdbcTemplate对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        // 执行select
        String sql = "select id, username, password from t_user";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        System.out.println(users);
    }

    @Test
    public void testAddBatch(){
        // 获取JdbcTemplate对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        // 批量添加
        String sql = "insert into t_user(id, username, password) values(?, ?, ?)";

        Object[] objs1 = {null, "小花", 123};
        Object[] objs2 = {null, "小明", 123};
        Object[] objs3 = {null, "小刚", 123};
        List<Object[]> list = new ArrayList<>();
        list.add(objs1);
        list.add(objs2);
        list.add(objs3);

        int[] count = jdbcTemplate.batchUpdate(sql, list);
        System.out.println(Arrays.toString(count));
    }


    @Test
    public void testUpdateBatch(){
        // 获取JdbcTemplate对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        // 批量修改
        String sql = "update t_user set username = ?, password = ? where id = ?";
        Object[] objs1 = {"小花11", 123456, 7};
        Object[] objs2 = {"小明22", 123456, 8};
        Object[] objs3 = {"小刚33", 123456, 9};
        List<Object[]> list = new ArrayList<>();
        list.add(objs1);
        list.add(objs2);
        list.add(objs3);

        int[] count = jdbcTemplate.batchUpdate(sql, list);
        System.out.println(Arrays.toString(count));
    }


    @Test
    public void testDeleteBatch(){
        // 获取JdbcTemplate对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        // 批量删除
        String sql = "delete from t_user where id = ?";
        Object[] objs1 = {7};
        Object[] objs2 = {8};
        Object[] objs3 = {9};
        List<Object[]> list = new ArrayList<>();
        list.add(objs1);
        list.add(objs2);
        list.add(objs3);
        int[] count = jdbcTemplate.batchUpdate(sql, list);
        System.out.println(Arrays.toString(count));
    }


}

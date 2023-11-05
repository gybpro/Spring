## Spring-2

### 12 Spring IoC注解式开发

#### 12.1 声明Bean的注解

- @Component：组件，声明Bean的最顶级注解
- @Controller：控制层组件，Component的别名注解，增强可读性
- @Service：业务层组件，Component的别名注解，增强可读性
- @Repository：数据访问层组件，Component的别名注解，增强可读性
- 通过源码可以知道，它们其实都是一个注解，只是为了可读性起了别名而已，它们都只有一个属性value，值是bean name/id

#### 12.2 Spring注解的使用

- Spring注解使用要求：
    - 引入Spring AOP依赖（Spring Context自带Spring AOP依赖）
    - 在配置文件中添加context命名空间
    - 在配置文件中指定扫描的包
    - 在Bean类上使用注解

#### 12.3 选择性实例化Bean

- context命名空间中的component-scan扫描包标签中有一个属性use-default-filters，可以选择性实例化Bean
- 当use-default-filters=false时，不扫描包注解，在标签中使用include-filter子标签，type属性选择annotation注解类型，expression指定使用扫描的注解
- use-default-filters默认为true，进行包扫描，如果要排除某个注解，可以使用exclude-filter子标签，type属性选择annotation注解类型，expression指定排除的注解

#### 12.4 负责注入的注解

- @Value：
    - 注入简单类型的值
    - 只有value一个属性，直接注入简单类型值
    - 可以在属性上、方法上、构造方法上、方法形参上
- @Autowired：
    - 自动连接/自动装配，注入引用类型对象
    - 只有required一个属性，默认是true，要求被注入的Bean必须存在，不然报错，值为false的时候则不要求Bean是否存在，存在注入，不存在忽略
    - byType根据类型注入，要byName根据名称注入需要和@Qualifier一起使用
    - 可以在属性上、方法上、构造方法上、方法形参上；当只有一个构造方法时，@Autowired可以省略
- @Qualifier：
    - 合格者/有资格者（符合要求的Bean）
    - 和@Autowired一起使用，byName根据名称进行注入
    - 只有value一个属性，用于指定bean name/id，不写的时候，默认为属性名
- @Resource：
    - JSR-250中规定的注解类型，是JDK扩展包中的注解，属于JDK的一部分
    - @Resource注解默认byName根据名称装配，未指定name时，默认为属性名，通过byName找不到时，再启动备用方案byType根据类型注入
    - @Resource只能用在属性上和setter方法上
- 注入注解用在属性上的时候是一般属性赋值，不需要setter方法（但是Spring的注解不推荐使用，如果要属性注入一般用@Resource注解）
- 现在Spring官方更推荐使用构造注入，当有循环依赖时，添加@Lazy懒加载注解在构造方法上，这样在进行依赖注入时，先生成代理类进行注入，在调用对象方法的时候再通过反射调用目标对象的原方法。

#### 12.5 全注解式开发

- 使用@Configuration来将java类标注为配置类，在Spring容器启动的时候通过AnnotationConfigApplicationContext加载配置类实现Spring配置
- 在配置类上使用@ComponentScan注解指定包扫描，在方法上使用@Bean注解将返回对象作为bean对象交给Spring管理

### 13 JdbcTemplate

#### 13.1 简述

- JdbcTemplate是Spring提供的JDBC模板类，是对JDBC的封装，简化JDBC代码
- 要使用需要引入Spring JDBC依赖

#### 13.2 简单增删改

- 在Spring配置文件/类中配置dataSource数据源对象，实例化JDBCTemplate对象，dataSource属性赋值dataSource数据源对象
- 简单增删改在Spring JDBC中都用update()方法提交执行

```java
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
```

#### 13.3 查询

- 查询单个对象和单个值都用queryForObject()提交执行
    - 第一个参数是sql语句
    - 第二个参数是Spring
      JDBC提供的自动映射类BeanPropertyRowMapper，new自动映射对象的时候传入要映射的类型即可返回指定类型查询结果，如果查询是单个值的时候，传入值类型，也可返回指定类型的查询结果
    - 第三个参数是可变长度参数，是传入占位符的值

```java
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
```

- 查询多个对象用query()

```java
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
```

#### 13.4 批量操作

- 批量添加、修改、删除都是用batchUpdate

```java
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
```

#### 13.5 回调函数

- 通过回调函数可以操作JDBC中的更细致的步骤，可以更灵活的使用JDBC

```java
@Test
public void testCallback(){
    // 获取JdbcTemplate对象
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
    String sql = "select id, username, password from t_user where id = ?";

    User user = jdbcTemplate.execute(sql, new PreparedStatementCallback<User>() {
        @Override
        public User doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
            User user = null;
            // 手动为占位符传值
            ps.setInt(1, 5);
            // 处理查询结果集
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        }
    });
    System.out.println(user);
}
```

#### 13.6 集成德鲁伊连接池

```xml
<bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/bjpowernode"/>
    <property name="username" value="root"/>
    <property name="password" value="123456"/>
</bean>

<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <property name="dataSource" ref="druidDataSource"/>
</bean>
```

### ⭐14 代理模式

#### 14.1 概述

- 代理模式（Proxy Pattern）是GoF23中设计模式之一。属于结构型设计模式。
-
代理模式为其他对象提供一种代理以控制对这个对象的访问。在某些情况下，一个客户不想或者不能直接引用一个对象，此时可以通过一个称之为“代理”的第三者来实现间接引用。代理对象可以在客户端和目标对象之间起到中介的作用，并且可以通过代理对象去掉客户不应该看到的内容和服务或者添加客户需要的额外服务。
通过引入一个新的对象来实现对真实对象的操作或者将新的对象作为真实对象的一个替身，这种实现机制即为代理模式，通过引入代理对象来间接访问一个对象，这就是代理模式的模式动机。

- 代理模式的主要作用：
    - 保护目标对象（隐蔽细节）
    - 增强代理功能（添加需要的功能）
    - 访问无法直接交互的对象（实现交互）
- 代理模式中的角色
    - 代理类（代理主题）
    - 目标类（真实主题）
    - 代理类和目标类的公共接口/父类（抽象主题）
- 代理模式一般分为：
    - 静态代理
    - 动态代理

#### 14.2 静态代理

- 编写一个代理类，和目标类共同实现同一个接口，在代理类对象中调用目标类对象的方法，并添加额外的增强功能

```java
/**
 * 静态代理实现类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class OrderServiceImplProxy implements OrderService {
    private OrderService orderService;

    public OrderServiceImplProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void generate() {
        long start = System.currentTimeMillis();
        orderService.generate();
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "执行累计耗时" + (end - start));
    }

    @Override
    public void modify() {
        long start = System.currentTimeMillis();
        orderService.modify();
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "执行累计耗时" + (end - start));
    }

    @Override
    public void detail() {
        long start = System.currentTimeMillis();
        orderService.detail();
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "执行累计耗时" + (end - start));
    }
}
```

- 静态代理优点：
    - 实现了代理模式的作用
    - 符合OCP开闭原则
    - 采用关联关系，程序耦合度较低
- 缺点：
    - 一个接口需要编写一个对应的代理类，需要反复多次编写重复代码，当接口较多时，会出现类爆炸现象
- 解决静态代理的缺点，用动态代理，在程序运行的过程中生成代理类。

#### 14.3 动态代理

- 动态代理，在程序运行的过程中生成代理类字节码文件，进行类加载实例化代理对象，调用代理对象方法，实现一个功能一次编写承接所有需要相同功能的不同接口。
- 常见动态代理技术：
    - JDK动态代理
    - CGLib动态代理
    - Javassist动态代理

#### ⭐14.4 JDK动态代理

- JDK动态代理是基于JDK的动态代理，使用的是JDK提供的Proxy类实现动态生成代理类字节码
- Proxy类中newProxyInstance()方法可以动态构建代理对象，通过调用代理对象中的代理方法实现间接调用目标对象的目标方法
- newProxyInstance()方法中有三个参数：
    - 第一个参数：ClassLoader类加载器(JDK动态代理要求目标类对象和代理类对象必须为同一个类加载器加载)
    - 第二个参数：Interfaces目标类和代理类的公共接口(接口字节码数组)
    - 第三个参数：InvocationHandler调用处理程序(发生调用事件时调用的事件句柄对象/回调方法对象)
- InvocationHandler调用处理程序/调用事件句柄接口中的invoke方法有三个参数：
    - 第一个参数：代理对象
    - 第二个参数：目标对象调用的目标方法
    - 第三个参数：目标方法的参数列表

```java
/**
 * JDK动态代理工具类
 * 
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class JDKDynamicProxyUtil {
    private JDKDynamicProxyUtil() {
    }

    public static Object newProxy(Object target, InvocationHandler handler) throws Exception {
        Class<?> clazz = target.getClass();
        /*
        newProxyInstance()的三个参数：
            第一个参数：类加载器(JDK动态代理要求目标类对象和代理类对象必须为同一个类加载器加载)
            第二个参数：目标类和代理类的公共接口(接口字节码数组)
            第三个参数：调用处理程序(发生调用事件时调用的事件句柄方法/回调方法)
         */
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
    }
}

/**
 * 计时调用处理程序/调用事件句柄计时类/调用事件发生时计时类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class TimeInvocationHandler implements InvocationHandler {
    // 关联目标对象
    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    /*
    InvocationHandler中invoke方法的三个参数：
        第一个参数：代理对象
        第二个参数：目标对象调用的目标方法
        第三个参数：目标方法的参数列表
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = method.invoke(target, args);
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "执行累计耗时" + (end - start));
        return obj;
    }
}
```

- InvocationHandler中invoke方法的三个参数是我们调用（getProxyInstance()
  方法返回的/动态生成的）$Proxy0对象的代理方法时，代理方法中传入的传入方式为：super.h.invoke(this, m, args)
  ，其中this是我们调用的代理对象，m是目标对象的目标方法，args我们传入代理方法的参数，h是我们写的InvocationHandler实现类对象，所以实际上三个参数都是在我们无法直观察觉到的情况下自己传进去的。$Proxy0源码：

```java
public final class $Proxy0 extends Proxy implements Subject {  
    private static Method m1;  
    private static Method m0;  
    private static Method m3;  
    private static Method m2;  
  
    static {  
        try {  
            m1 = Class.forName("java.lang.Object").getMethod("equals",  
                    new Class[] { Class.forName("java.lang.Object") });  
  
            m0 = Class.forName("java.lang.Object").getMethod("hashCode",  
                    new Class[0]);  
  
            m3 = Class.forName("***.RealSubject").getMethod("request",  
                    new Class[0]);  
  
            m2 = Class.forName("java.lang.Object").getMethod("toString",  
                    new Class[0]);  
  
        } catch (NoSuchMethodException nosuchmethodexception) {  
            throw new NoSuchMethodError(nosuchmethodexception.getMessage());  
        } catch (ClassNotFoundException classnotfoundexception) {  
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());  
        }  
    } //static  
  
    public $Proxy0(InvocationHandler invocationhandler) {  
        super(invocationhandler);  
    }  
  
    @Override  
    public final boolean equals(Object obj) {  
        try {  
            return ((Boolean) super.h.invoke(this, m1, new Object[] { obj })) .booleanValue();  
        } catch (Throwable throwable) {  
            throw new UndeclaredThrowableException(throwable);  
        }  
    }  
  
    @Override  
    public final int hashCode() {  
        try {  
            return ((Integer) super.h.invoke(this, m0, null)).intValue();  
        } catch (Throwable throwable) {  
            throw new UndeclaredThrowableException(throwable);  
        }  
    }  
  
    public final void 目标方法() {  
        try {  
            super.h.invoke(this, m3, null);  
            return;  
        } catch (Error e) {  
        } catch (Throwable throwable) {  
            throw new UndeclaredThrowableException(throwable);  
        }  
    }  
  
    @Override  
    public final String toString() {  
        try {  
            return (String) super.h.invoke(this, m2, null);  
        } catch (Throwable throwable) {  
            throw new UndeclaredThrowableException(throwable);  
        }  
    }  
}
```

#### 14.5 CGLib动态代理

- CGLIB动态代理既能代理接口，又能代理类，更为灵活。CGLIB底层采用继承的方式实现，所以被代理的目标类不能使用final修饰
-
CGLIB中的回调拦截器是MethodInterceptor，类似于JDK动态代理中的InvocationHandler，实现原理也类似，不过MethodInterceptor中的intercept()
方法有4个参数：
    - 目标对象
    - 目标方法
    - 参数
    - 代理方法
- 注意：MethodInterceptor中不是通过目标方法的invoke实现，而是通过代理方法的**invokeSuper()**
  实现，直接使用目标方法调用invoke会报调用目标异常InvocationTargetException，而调用代理方法的invoke则会进入死循环无限递归，导致栈内存溢出错误StackOverflowError

```java
/**
 * CGLIB动态代理工具类
 *
 * @author programmer
 * @version 1.0
 * @since 1.0
 */
public class CgLibDynamicProxyUtils {
    private CgLibDynamicProxyUtils() {

    }

    @SuppressWarnings("unchecked")
    public static <T> T newProxy(T target, MethodInterceptor interceptor) {
        // CGLIB库中的核心对象，用于生成代理类
        Enhancer enhancer = new Enhancer();

        // 设置目标类，即父类
        enhancer.setSuperclass(target.getClass());

        // 设置回调拦截器/方法拦截器
        enhancer.setCallback(interceptor);

        // 创建并返回代理对象
        return (T) enhancer.create();
    }
}

/**
 * 方法计时拦截器
 *
 * @author programmer
 * @version 1.0
 * @since 1.0
 */
public class TimeMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        long start = System.currentTimeMillis();
        Object retValue = methodProxy.invokeSuper(target, args);
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "执行累计耗时" + (end - start));
        return retValue;
    }
}
```

### 15 ⭐SpringAOP

#### 15.1 概述

- AOP：面向切面编程，是一种编程思想
- AOP底层原理就是动态代理
- 将与核心业务内容无关的通用的交叉业务代码抽取出来，形成一个独立的横向切面。切面以不影响业务的方式独立地切入核心业务代码。
- SpringAOP的实现是：JDK动态代理＋CGLIB动态代理
    - 当目标类有实现接口，那么会优先使用 JDK动态代理实现，当目标类没有实现接口，则会使用CGLIB动态代理

#### 15.2 七大术语

- **连接点 Joinpoint**：可以织入切面的**位置**
- **切点 Pointcut**：织入通知的**方法**（**目标方法**）
- **通知 Advice**：织入的代码（**代理增强代码**）
- **切面 Aspect**：切点+通知（**代理方法**）
- 织入 Weaving：把通知应用到目标的过程
- 代理对象 Proxy
- 目标对象 Target

#### 15.3 切点表达式

- 切点表达式用来定义通知（Advice）往哪些方法上切入

- 语法：

    ```java
    execution([访问控制权限修饰符] 返回值类型 [全限定类名]方法名(形式参数列表) [异常])
    ```

    - 访问控制权限修饰符：
        - 可选项
        - 省略，默认包含4种权限
        - 写public，就表示只包含公开的方法
    - 返回值类型：
        - 必填项
        - *表示任意返回值类型
    - 全限定类名：
        - 可选项
        - ".."两个点代表当前包及子包下所有类
    - 方法名：
        - 必填项
        - *表示所有方法
        - set*表示所有set方法
    - 形参列表：
        - 必填项
        - ()表示没有参数的方法
        - (..)表示任意参数类型和个数
        - (*)表示只有一个参数的方法
        - (*, String)表示第一个参数类型任意，第二个参数类型是String
    - 异常：
        - 可选项
        - 省略表示任意异常类型

#### ⭐15.4 基于Aspect注解的AOP开发

- **@Aspect**：切面注解，用在类上
    - 在Spring配置中开启自动代理机制后，在扫描类时，会判断类上是否有@Aspect注解，有则会自动生成代理类
- **@Poincut**：通用切点表达式，用在空方法上，用于定义通用切点表达式，方便复用及维护
- **@Before**：前置通知
- **@AfterReturning**：后置通知，在方法return后通知
    - 注意区别后置通知和最终通知
- **@AfterThrowing**：异常通知，在方法抛出异常后通知
- **@After**：最终通知，无论方法是否发生异常，在方法结束后通知
    - 注意区别后置通知和最终通知
- **@Around**：环绕通知，范围最大，前环绕通知在前置通知之前，后环绕通知在最终通知之后
    - 注意：后环绕通知在方法抛出异常后不执行。
- **@Order**：通知排序，决定切面的执行顺序，数字越小，优先级越高

#### 15.5 基于XML的AOP开发

```java
<beans>
    <!-- 组件扫描 -->
    <!-- <context:component-scan base-package="com.high.spring"/> -->
    <!--纳入spring bean管理-->
    <bean id="logAspect" class="com.high.spring.aspect.LogAspect"/>
    <bean id="securityAspect" class="com.high.spring.aspect.SecurityAspect"/>
    <bean id="orderService" class="com.high.spring.service.impl.OrderServiceImpl"/>

    <!-- 开启aspect自动代理
        spring容器在扫描类时，会判断类上是否有@Aspect注解，如果有则会生成代理类
        proxy-target-class="true" 表示强制使用CGLIB动态代理
        默认为false，优先使用JDK动态代理，目标类没有实现接口时才使用CGLIB动态代理 -->
    <!-- <aop:aspectj-autoproxy/> -->

    <!-- AOP配置 -->
    <aop:config>
        <!-- 定义通用切点表达式 -->
        <aop:pointcut id="servicePointcut" expression="execution(* com.high.spring.service.impl.*.*(..))"/>
        <!-- 定义切面，切面 = 通知 + 切点 -->
        <aop:aspect ref="logAspect">
            <!-- 前置通知 -->
            <aop:before method="beforeLog" pointcut-ref="servicePointcut"/>
            <!-- 后置通知 -->
            <aop:after-returning method="afterReturningLog" pointcut-ref="servicePointcut"/>
            <!-- 异常通知 -->
            <aop:after-throwing method="afterThrowingLog" pointcut-ref="servicePointcut"/>
            <!-- 最终通知 -->
            <aop:after method="afterLog" pointcut-ref="servicePointcut"/>
            <!-- 环绕通知 -->
            <aop:around method="aroundLog" pointcut-ref="servicePointcut"/>
        </aop:aspect>
    </aop:config>
</beans>
```

#### 15.6 AOP实际应用

- 在实际开发中，AOP常用于：
    - 事务控制
    - 日记记录
    - 安全控制
    - ......

### 16 Spring事务支持

#### 16.1 概述

- Spring对事务的处理底层就是SpringAOP
- Spring事务管理器的核心接口是PlatformTransactionManager接口，在Spring6中它有两个实现：
    - DataSourceTransactionManager：支持 JdbcTemplate、MyBatis、Hibernate等事务管理
    - JtaTransactionManager：支持分布式事务管理

#### 16.2 注解式实现

- @Transactional：可在方法和类上使用
- @Transactional中重要的属性：
    - transactionManager：事务管理器
    - Propagation：事务传播行为
    - isolation：事务隔离级别
    - timeout：事务超时
    - readOnly：只读事务
    - rollbackFor：设置回滚事务的异常
    - noRollbackFor：设置不回滚事务的异常

#### ⭐16.3 事务重要属性

##### ⭐16.3.1 事务传播行为

- **REQUIRED（默认）**：支持当前事务，如果不存在就新建一个事务**【没有就新建，有就加入】**（required必须的）
- SUPPORTS：支持当前事务，如果没有就以非事务方式执行**【有就加入，没有就不管了】**（supports支持）
- MANDATORY：必须运行在一个事务中，如果当前没有事务正在发生，将抛出一个异常**【有就加入，没有就抛异常】**（mandatory强制性的）
- **REQUIRES_NEW**：开启一个新的事务，如果一个事务已经存在，则将这个存在的事务挂起**
  【不管有没有，直接开启一个新事务，开启的新事务和之前的事务不存在嵌套关系，之前事务被挂起】**（requires_new必须是新的）
- NOT_SUPPORTED：以非事务方式运行，如果有事务存在，挂起当前事务**【不支持事务，存在就挂起】**（not_supported不支持的）
- NEVER：以非事务方式运行，如果有事务存在，抛出异常**【不支持事务，存在就抛异常】**（never绝不）
- NESTED：如果当前正有一个事务在进行中，则该方法应当运行在一个嵌套式事务中。被嵌套的事务可以独立于外层事务进行提交或回滚。如果外层事务不存在，行为就像REQUIRED一样。
  **【有事务就在事务里再嵌套一个完全独立的事务，嵌套的事务可以独立的提交和回滚。没有就新建。】**（nested嵌套的）

##### ⭐16.3.2 事务隔离级别

- 事务三大读问题（数据不一致问题）
    - 脏读：读取到未提交数据
    - 不可重复读：在同一个事务中多次读取的数据不一致
    - 幻读：读到假数据（过期数据）
- 事务4个隔离级别：
    - READ_UNCOMMITTED：读未提交
        - 存在脏读问题
    - READ_COMMITTED：读已提交
        - 存在不可重复读问题
    - REPEATABLE_READ：可重复读
        - 存在幻读问题
    - SERIALIZABLE：序列化（串行化）
        - 不支持并发

##### ⭐16.3.3 事务超时

- 通过timeout可以配置事务的超时时间，timeout是以秒为单位的
- **timeout事务的超时时间是所有DML语句执行完的超时时间，如果所有DML语句已经执行完了，那么之后的代码运行超时也不会回滚**

##### ⭐16.3.4 只读事务

- 只读事务是只能执行select语句的事务，不能执行insert、update、delete语句
- Spring底层会对只读事务进行优化，提高select的执行效率

##### 16.3.5 异常设置

- rollbackFor：设置回滚事务的异常
    - 不是回滚事务异常的异常不会进行回滚
- noRollbackFor：设置不回滚事务的异常
    - 不是不回滚事务异常的异常才会进行回滚

### 17 Spring整合JUnit5

- Spring整合JUnit4和JUnit5，不用再重复写读配置和取JavaBean的代码，直接注入即可测试

- 整合JUnit4：

    ```java 
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("classpath:spring.xml")
    ```

- 整合JUnit5：

    ```java
    @ExtendWith(SpringExtension.class)
    @ContextConfiguration("classpath:spring.xml")
    ```

  注意：JUnit5的@Test和 JUnit4的@Test是不一样的

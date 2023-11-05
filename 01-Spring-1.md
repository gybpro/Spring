## Spring-1

### 1 启示录

#### 1.1 软件设计七大原则

- OCP开闭原则
- DIP依赖倒置原则
- SRP单一职责原则
- ISP接口隔离原则
- LSP里氏替换原则
- LOD迪米特原则
- C/ARPC合成（组合/聚合）复用原则

#### 1.2 OCP开闭原则

- OCP开闭原则是软件设计七大原则之首，是最基本，最核心的，其他六个原则都是为OCP服务的。
- 开放：对扩展开放
- 关闭：对修改关闭
- 一个软件在进行功能扩展的时候，如果动了之前稳定的程序，那么之前的所有程序都需要重新测试，这样的设计是失败的

#### 1.3 DIP依赖倒置原则

- 上层(抽象)不能依赖下层(具体)
- 要面向接口编程，面向抽象编程，不能面向具体编程
- 目的：降低程序耦合度，提高扩展力
- 下层改动影响上层，这样的设计就是失败的

#### 1.4 IoC控制反转

- 将组件对象的控制权（创建、销毁、关系维护等）反转
- 控制反转是一种编程思想，也是一种新型的设计模式
- IoC控制反转主要的两种实现方式：
    - DI依赖注入：被动注入，在A对象创建的时候，通过类型、名称搜寻并合适的类创建B对象并注入到A的属性中
    - DL依赖查找：主动注入，调用方法，在配置文件中配置要创建的对象类型及注入时间

#### 1.5 DI依赖注入

- DI依赖注入是IoC控制反转的一种具体实现，常见的注入方式有：
    - set注入
    - 构造方法注入
- 依赖：对象与对象之间的关系
- 注入：维护对象间关系的一种手段

### 2 概述

#### 2.1 简介

- Spring是一个轻量级的实现了控制反转IoC和面向切面AOP的容器框架
- Spring最初出现是为了解决EJB（企业级JavaBean）臃肿的设计，以及难以测试等问题
- Spring为简化开发而生，让程序员只需关心核心业务的实现，尽可能的不再关注大量重复的非业务逻辑代码（事务控制，安全日志等）

#### 2.2 Spring八大模块

- Spring5之前是七大模块，在Spring5中新增了WebFlux模块（响应式web）
- 八大模块：
    - Spring Core：Spring的核心模块，实现了控制反转IoC，让Spring能称为容器的模块
        -
        Spring最核心最基础的模块，提供了依赖注入DI实现容器对Bean的管理，核心容器的主要组件是BeanFactory，BeanFactory是整个Spring应用的核心。它用DI实现IoC将应用配置和依赖从实际的应用代码中分离出来。
    - Spring Context：Spring上下文，提供对各种扩展技术的支持，也是让Spring能称为框架的模块
        - 扩展技术：国际化信息（I18N）、事件传播、验证的支持、企业服务（电子邮件、JNDI访问、EJB集成、远程以及时序调度服务等）、模板技术Velocity和FreeMarker集成的支持等
    - 第二重点模块Spring AOP面向切面AOP
        - Spring AOP提供了对面向切面编程的丰富支持，为Spring容器中的对象提供了事务管理服务，通过Spring
          AOP，不用依赖组件，就可以将声明式事务管理集成到应用程序中，可以自定义拦截器、切点、日志等操作
    - Spring Web MVC：Spring自己提供的MVC框架，也称Spring MVC
        - Spring为构建Web应用提供的一个功能全面的MVC框架。Spring MVC相比其他MVC框架（如Struts等）的优点是使用IoC对控制逻辑和业务对象提供了完全的分离。
    - Spring DAO：提供了JDBC的抽象层（是抽象的，没有指定具体实现，可以是JDBC或其他ORM框架）和异常层次结构，消除了烦琐的JDBC编码和数据库厂商特有的错误代码解析，用于简化JDBC
        - Spring
          DAO不是严格意义上的Spring模块，而是一种约定，并没有DAO的实现、模板或接口，主要是为了于DAO底层技术（JDBC、JPA、MyBatis、Hibernate等）相关联的异常一致地转换为适当的DataAccessException子类
    - Spring ORM：支持常见的第三方ORM框架，如MyBatis、Hibernate等
        - Spring并不视图实现它自己的ORM解决方案（Spring DAO如上述；Spring JDBC只是简化JDBC；Spring
          Data则是提供更通用的方式访问数据，涵盖了SQL和NOSQL的数据源），而是为流行的ORM框架提供集成方案，包括Hibernate、MyBatis等，这些都遵从Spring通用事务和DAO异常层次结构。
    - Spring WebFlux：Spring5新增的自己提供的响应式Web框架
        - 一个完全非阻塞的，支持反应式流（Reactive Stream）背压的Web反应式堆栈框架
    - Spring Web：支持常见的Web框架，如Struts、WebWork等

#### 2.3 Spring容器的基本实现和小细节

- Spring的基本实现是XML解析+工厂模式+反射机制
- xml文件中每个bean的id是唯一，不能重复的
- 底层是在启动Spring容器，加载Spring配置文件的时候通过工厂模式+反射机制调用类的无参数构造方法创建对象（同时完成依赖注入）
- Spring配置文件可以有多个，名字可以有多种，不同路径可重名，配置的类可以是JDK中的类、自定义类、第三方库的类，只要是有无参构造的Java类即可
- 构建对象的时候，id不存在会报错，不会返回null
- 可以通过类路径ClassPath或文件系统FileSystem（系统的绝对路径）加载配置文件
- 核心Spring容器ApplicationContext的超级父接口是BeanFactory，BeanFactory是Spring最核心的接口

#### 2.4 启用Log4j2日志框架

- Spring5之后支持集成Log4j2日志框架
- Spring6底层则是默认使用Log4j2日志框架，需要引入并启用Log4j2日志框架
- 配置log4j2.xml文件（必须在类路径下）

```xml
<?xml version="1.0" encoding="UTF-8"?>

<configuration>

    <loggers>
        <!--
            level指定日志级别，从低到高的优先级：
                ALL < TRACE < DEBUG < INFO < WARN < ERROR < FATAL < OFF
        -->
        <root level="DEBUG">
            <appender-ref ref="spring6log"/>
        </root>
    </loggers>

    <appenders>
        <!--输出日志信息到控制台-->
        <console name="spring6log" target="SYSTEM_OUT">
            <!--控制日志输出的格式-->
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss SSS} [%t] %-3level %logger{1024} - %msg%n"/>
        </console>
    </appenders>

</configuration>
```

### ⭐3 SpringIoC

#### 3.1 IoC控制反转

- 控制反转是一种编程思想，一种设计模式
- 控制反转是为了降低程序耦合度，提高程序扩展力，达到OCP原则和DIP原则
- 控制反转是将对象的控制权（创建、销毁、对象间关系维护等）交给第三方容器负责
- 控制反转的主要实现：DI依赖注入（Spring）、DP依赖查找

#### 3.2 DI依赖注入

- DI依赖注入是控制反转IoC编程思想的一种实现
- Spring通过DI依赖注入的方式来完成Bean管理
- Bean管理：Bean对象的创建、销毁、对象中属性的赋值（对象之间关系维护）等
- 依赖注入分别解释：
    - 依赖：对象之间的关系
    - 注入：一种用于维护对象间关系的数据传递行为（引用传递）
- 依赖注入常见的实现方式：
    - set注入
        - 基于set方法实现，要求属性必须对外提供set方法
        - 原理：通过属性名推断set方法名为"set拼接首字母大写属性名"，通过反射机制调用set方法
    - 构造注入
        - 基于构造方法实现，必须实现有参构造，同时传递参数必须与方法形式参数一致，否则报错（Spring是不会传递null的）
        - 原理：通过反射调用构造方法，并传递参数
        - 构造注入的时候，可以通过下标注入，参数名注入，也可进行类型自动推断

#### 3.3 set注入专题

- set注入是Spring常用的依赖注入方式

##### 3.3.1 外部注入

- 声明两个bean，在beanA中通过ref引用注入beanB（必须有id）

##### 3.3.2 内部注入

- 在beanA中嵌套声明beanB（可以无id，了解即可，基本不用）

##### 3.3.3 注入简单类型

- 可直接注入value值，不用ref

- 分析Spring源码BeanUtils类的isSimpleValueType()方法，Spring适配的简单类型包括：
    - 基本数据类型及其包装类
    - CharSequence类型（如String类型）
    - Number类型（如数值型包装类）
    - Date类型（对格式要求高，一般不当作简单类型）
    - Enum类型
    - URI类型
    - URL类型（Spring6之后，会进行URL有效性检测，不通过会报错）
    - Temporal类型（JDK8提供的新日期类型）
    - Locale类型（本地系统语言）
    - Class类型（字节码类型）
    - 包括所有简单类型对应的数组类型

##### 3.3.4 级联属性赋值

- 可以通过级联的方式为引用对象的属性赋值（了解）

- 需要注意顺序，先创建引用对象，再进行赋值
- 级联调用要求引用对象的类中必须提供getter方法

##### 3.3.5 注入数组

- 用<array>标签注入，简单类型可以value注入，非简单类型要ref注入

##### 3.3.6 注入List集合

- 用<list>标签注入，简单类型可以value注入，非简单类型要ref注入

##### 3.3.7 注入Set集合

- 用<set>标签注入，简单类型可以value注入，非简单类型要ref注入

##### 3.3.8 注入Map集合

- 用<map>标签中的<entry>标签注入
    - key属性：简单类型可以用key注入，非简单类型要用key-ref注入
    - value属性：简单类型可以用value注入，非简单类型要用value-ref注入

##### 3.3.9 注入Properties集合

- Properties也是一个Map集合，用<props>标签嵌套<prop>标签注入，key和value属性同Map集合注入一致

##### 3.3.10 注入null和空字符串

- 注入空字符串用：<value/>或value=""
- 注入null使用：<null/>或不为属性赋值

##### 3.3.11 注入特殊字符

- XML文件中有5个特殊字符及其转义字符：
    - < ：\&gt;
    - \> ：\&lt;
    - ' ：\&apos;
    - " ：\&quot;
    - & ：\&amp;
- 注入特殊字符可用转义字符代替，也可用XML语法中的<![CDATA[特殊符号]]>标签将其识别为普通字符串

#### 3.4 p命名空间注入

- 用命名空间可以简化配置
- p命名空间是用来简化set注入的
- 使用：
    1. 在XML头部信息中添加p命名空间的配置信息：xmlns:p="http://www.springframework.org/schema/p"
    2. p命名空间是基于setter方法的，所以bean必须提供对应属性的setter方法

#### 3.5 c命名空间注入

- c命名空间是用来简化构造注入的
- 使用：
    1. 需要在xml配置文件头部添加信息：xmlns:c="http://www.springframework.org/schema/c"
    2. c命名空间是基于构造方法的，所以bean必须提供用于注入属性的构造方法
- **注意：不管是p命名空间还是c命名空间，注入的时候都可以注入简单类型以及非简单类型。**

#### 3.6 util命名空间

- util命名空间是用来复用配置的

#### 3.7 基于XML的自动装配

- Spring可以完成自动化的注入，自动化注入又称为自动装配。它可以根据**名字**进行自动装配，也可以根据**类型**进行自动装配。
- 自动装配是基于setter方法的
- 基于类型的自动装配，要求不能有相同类型的多个对象

#### 3.8 Spring引入外部属性配置文件

- 通过\<context:property-placeholder>标签的location属性引入外部属性配置文件

- 需要注意的是：引入外部属性配置文件，读取内容时，${}中读取属性是先从Windows操作系统中读取，然后再读取属性配置文件的内容，所以一般属性配置文件前面都会加技术名.，如JDBC属性配置文件中的内容为：

    ```properties
    jdbc.driver=com.mysql.cj.jdbc.Driver
    jdbc.url=jdbc:mysql://localhost:3306/spring
    jdbc.username=root
    jdbc.password=root123
    # 如果这里不加jdbc.，则username会被读取为Windows操作系统用户名
    ```

### 4 Bean的作用域

- bean的scope属性可以指定bean的作用域

- 一般有8个作用域，还可以自定义作用域

- 8个作用域：

    - singleton：单例，在启动Spring容器加载Spring配置文件的时候创建bean对象，默认作用域
    - prototype：原型，每一次调用getBean()方法创建一个新的Bean对象，每次注入都是新对象
    - request：一次请求对应一个Bean对象。仅限于WEB应用中使用
    - session：一次会话对应一个Bean对象。仅限于WEB应用中使用
    - global session：portlet门户应用专用，WEB应用有Servlet规范Servlet服务应用，还有Portlet规范Portlet门户应用。如果在Servlet服务应用中使用global
      session，会默认为session作用域
    - application：一个应用对应一个Bean。仅限于在WEB应用中使用
    - websocket：一个websocket生命周期对应一个Bean。仅限于WEB应用中使用

- 8个可选作用域中常用的是前4个：singleton、prototype、request、session

- 除了singleton单例，其他都是要在getBean的时候获取

- 自定义scope使用步骤（较少使用）：

    1. 自定义Scope类（实现Scope接口）

        - 如Spring内置的线程作用域类org.springframework.context.support.SimpleThreadScope

    2. 将自定义Scope类注册到Spring容器中

        ```xml
        <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
          <property name="scopes">
            <map>
              <entry key="myThread">
                <bean class="org.springframework.context.support.SimpleThreadScope"/>
              </entry>
            </map>
          </property>
        </bean>
        ```

    3. 使用Scope作用域

### ⭐5 GoF之工厂模式

#### 5.1 GoF设计模式

- 设计模式：一种可以被重复利用的解决方案
- 设计模式一般都尽可能地遵循软件设计的七大原则，只有某些特定情况下会只满足一部分原则
- GoF（Gang of Four）四人组编写了名为《设计模式》一书，书中描述了23种设计模式，我们平常所说的设计模式就是指GoF23种设计模式
- 不过除了GoF23种设计模式之外，还有其他设计模式，比如JavaEE设计模式（MVC模式、DAO模式等）
- GoF23种设计模式可分为三大类：
    - 创建型（5个）：解决对象创建问题
        - 单例模式
        - 工厂方法模式
        - 抽象工厂模式
        - 建造者模式
        - 原型模式
    - 结构型（7个）：一些类或对象组合在一起的经典结构（组合形成更大的结构）
        - 代理模式
        - 装饰模式
        - 适配器模式
        - 组合模式
        - 享元模式
        - 外观模式
        - 桥接模式
    - 行为模式（11个）：解决类或对象之间的交互问题（对象间的访问调用等）
        - 策略模式
        - 模板方法模式
        - 责任链模式
        - 观察者模式
        - 迭代子模式
        - 命令模式
        - 备忘录模式
        - 状态模式
        - 访问者模式
        - 中介者模式
        - 解释器模式
- 工厂模式是解决对象创建问题的，是创建型设计模式，Spring框架底层使用了大量工厂模式

#### 5.2 工厂模式的三种形态

工厂模式通常有三种形态：

- 简单工厂模式（Simple Factory）：不属于23种设计模式。
    - 简单工厂模式又叫做：静态工厂方法模式
    - 简单工厂模式是工厂方法模式的一种特殊实现
- 工厂方法模式（Factory Method）：23种设计模式之一
- 抽象工厂模式（Abstract Factory）：23种设计模式之一

#### 5.3 简单工厂模式

- 简单工厂模式（Simple Factory Pattern）：是工厂方法模式的一种特殊实现，又被称为静态工厂方法模式
- 简单工厂模式的角色包括三个：
    - 抽象产品 角色
    - 具体产品 角色
    - 工厂类 角色
- 简单工厂模式的优点：
    - 客户端程序无需关心对象的创建细节，需要哪个对象，就向工厂索要即可。
    - 客户端负责消费，工厂负责生产，生产消费分离，初步实现了责任的分离。
- 简单工厂模式的缺点：
    - 工厂类集中了所有产品的创造逻辑，形成了一个无所不知、无所不能的全能类、上帝类。这显然是不稳定的，工厂类一旦出现问题，可能会导致整个系统瘫痪。
    - 不符合OCP开闭原则，在进行系统扩展、产品扩展的时候，需要修改工厂类，修改稳定运行的源码是大忌。
- Spring中的BeanFactory就使用了简单工厂模式

5.4 工厂方法模式

- 工厂方法模式既保留了简单工厂模式的优点，同时又解决了简单工厂模式的缺点
- 工厂方法模式的角色包括：
    - 抽象工厂角色
    - 具体工厂角色
    - 抽象产品角色
    - 具体产品角色
- 工厂方法模式的优点：
    - 保留了简单工厂模式的两个优点：封装无需关心细节，责任分离
    - 多了一个解决OCP开闭原则的优点：扩展性高，如果想扩展产品，只要再扩展一个工厂类即可。
- 工厂方法模式的缺点：
    - 每次增加一个产品时，都需要一个具体类实现工厂，使得系统的类成倍增长，在一定程度上增加了系统的复杂度，当出现类爆炸，系统过于复杂时，系统会十分难维护
    - 同时创建不同对象需要不同的工厂，这增加了系统对具体类的依赖，不符合DIP依赖倒置原则，要尽量面向接口编程

#### 5.4 抽象工厂模式（了解）

- 抽象工厂模式和工厂方法模式简单对比：
    - 工厂方法模式针对一个产品系列的，即工厂方法模式是一个产品系列一个工厂类
    - 抽象工厂模式是针对多个产品系列的，即抽象工厂模式是多个产品系类一个工厂类
- 抽象工厂模式简单说，是简单工厂模式+工厂方法模式：
    - 上层是工厂方法模式，顶层是超级抽象工厂，中间是将不同功能范围的产品分为不同的抽象工厂族
    - 下层是简单工厂模式，一个抽象工厂可以创建多种抽象工厂功能范围内的具体产品类
    - 其实抽象工厂模式就像生物学分类一样，界门纲目科属种，最上面的超级抽象工厂是生物，下面是界门纲目科属种，但是没有分那么细，分到种，最下面的具体工厂类其实也是一种抽象，可以生产多种产品/生物。
- 抽象工厂中包含4个角色：
    - 抽象工厂角色
    - 具体工厂角色
    - 抽象产品角色
    - 具体产品角色
- 抽象工厂模式的优缺点：
    - 优点：当一个产品族中的多个对象被设计成一起工作时，它能保证客户端始终只使用同一个产品族中的对象。
    - 缺点：产品族扩展非常困难，要增加一个系列的某一产品，既要在AbstractFactory里加代码，又要在具体的里面加代码。（所以一开始就要尽量把上层的工厂设计好，尽可能保证以后扩展的产品都在上层包含的范围内）

### 6 Bean的实例化

#### Bean的四种实例化方式

- Spring为Bean提供了多种实例化方式（有多种备用方案目的是为了更加灵活），通常包括4种方式
    - 直接通过构造方法实例化

        ```xml
        <bean id="userBean" class="com.high.spring6.bean.User"/>
        ```

    - 通过简单工厂模式实例化

        ```xml
        <bean id="userBean" class="com.high.spring6.bean.UserFactory" factory-method="getUser"/>
        ```

    - 通过factory-bean实例化

        ```xml
        <bean id="userFactory" class="com.high.spring6.bean.UserFactory"/>
        <bean id="userBean" factory-bean="userFactory" factory-method="getUser"/>
        ```

    - 通过FactoryBean接口实例化

        ```java
        public class UserFactory implements FactoryBean<User> {}
        ```

        ```xml
        <bean id="userBean" class="com.high.spring6.bean.UserFactory"/>
        ```

#### BeanFactory和FactoryBean的区别

- BeanFactory是Spring IoC容器的顶级对象，是Bean工厂，Bean工厂是负责创建Bean对象的
- FactoryBean是一个工厂Bean，是辅助Spring实例化其他Bean对象的一个Bean
- 在Spring中，Bean可以分为两类：
    - 普通Bean
    - 工厂Bean，可以辅助Spring实例化其他Bean对象的Bean

#### 注入自定义Date

- 日期在Spring中虽然是简单类型，但是格式要求非常严格，所以一般不当作简单类型处理。

```java
/**
 * 日期工厂
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class DateFactory implements FactoryBean<Date> {
    private String dateStr;

    private SimpleDateFormat simpleDateFormat;

    public DateFactory(String dateStr) {
        this.dateStr = dateStr;
        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public Date getObject() throws Exception {
        return simpleDateFormat.parse(dateStr);
    }

    @Override
    public Class<?> getObjectType() {
        return Date.class;
    }
}
```

```xml
<!--注入自定义Date-->
<bean id="date" class="com.high.spring.bean.DateFactory">
    <constructor-arg index="0" value="2002-02-22"/>
</bean>
<bean id="people" class="com.high.spring.bean.People">
    <property name="birth" ref="date"/>
</bean>
```

### ⭐7 Bean生命周期

#### Bean完整生命周期

- Bean的生命周期就是Bean从创建到销毁的整个过程，在这个过程中Spring为我们提供了十个执行代码的时机

- Bean完整生命周期：
    - 第一步：实例化Bean的时机（构造方法）
    - 第二步：Bean属性赋值的时机（set方法）
    - 第三步：进入Bean后置处理器之前的Aware时机（Aware意识到，察觉，发现）
        - BeanNameAware接口
        - BeanClassLoaderAware接口
        - BeanFactoryAware接口
    - 第四步：在Bean后置处理器中初始化方法之前的时机（beforeInit）
        - BeanPostProcessor后置处理器接口
    - 第五步：Bean初始化中的时机（其实是初始化之前）
        - InitializingBean接口
    - 第六步：Bean初始化的时机（init方法，自定义的方法）
    - 第七步：在Bean后置处理器中初始化方法之后的时机（afterInit）
    - 第八步：使用Bean的时机
    - 第九步：Bean抛弃前的时机
        - DisposableBean接口
    - 第十步：Bean销毁前的时机

#### Bean的作用域不同，管理方式不同

- Spring是根据Bean的作用域来选择管理方式的
    - 对于singleton作用域的Bean，Spring能够精确的知道这些Bean实例的完整生命周期，Spring管理singleton作用域Bean的完整生命周期
    - 而对于其他，如prototype作用域的Bean，Spring只负责创建初始化，当Bean的实例交给客户端管理的时候，Spring就不再管理它们的生命周期

#### 将自己new的对象交给Spring管理

- 当我们需要将自己new的对象，或已经脱离Spring管理的Bean实例，交给Spring管理的时候，需要将该对象注册到Spring当中

    ```java
    @Test
    public void testBeanRegister() {
    // 将自己new的对象交给Spring管理
        User user = new User();
        System.out.println(user);
    
        // 创建默认可列举Bean工厂，DefaultListableBeanFactory是BeanFactory的默认实现
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        // 注册为单例Bean
        defaultListableBeanFactory.registerSingleton("user", user);
        User user1 = defaultListableBeanFactory.getBean("user", User.class);
        System.out.println(user1);
    }
    ```

### ⭐8 循环依赖问题

#### 8.1 简述

- Bean的循环依赖：即A对象中有B对象的引用，B对象中有A对象的引用，Bean对象间的互相依赖问题
- Bean的循环依赖问题可以从作用域和注入方式两个角度去观察
    - 作用域：singleton、prototype
    - 注入方式：set注入、构造注入

#### 8.2 singleton下的set注入

- 在singleton+setter模式下产生的循环依赖问题被Spring轻松解决
- 这是因为singleton+setter模式下，Spring对Bean的创建到属性赋值阶段管理清晰的分为两个阶段：
    - 第一阶段：在Spring容器加载的时候，实例化Bean，只要任意一个Bean实例化后，马上进行Bean的"曝光"处理（不等属性的赋值）
    - 第二阶段：Bean"曝光"后，再进行属性的赋值（调用set方法）

#### 8.3 prototype下的set注入

- 在prototype+setter模式下，循环依赖出现了问题**BeanCurrentlyInCreationException**当前请求的Bean正在创建中异常
- 因为prototype下每一次获取Bean都是实例化新的对象，不能像singleton一样在实例化后曝光（因为bean
  name/id只有一个，如果重复曝光会导致请求获取者不知道获取哪个对象），A对象创建成功后属性赋值的时候，发现需要获取B对象，而B对象创建成功后属性赋值的时候也需要获取A对象，而每一次获取都是创建新的对象，这就出现了Bean的循环依赖问题
- 在set注入情况下，只有当两个对象都是prototype原型作用域的时候，才会出现循环依赖问题，只要其中一个是singleton，则不会有循环依赖问题

#### 8.4 singleton下的构造注入

- 在singleton+构造注入模式下，同样出现了循环依赖问题**BeanCurrentlyInCreationException**当前请求的Bean正在创建中异常
- 因为在构造注入的情况下，Bean的实例化对象过程和属性赋值过程没有分离，导致在实例化对象的过程中，A对象获取B对象，B对象也在获取A对象，出现循环依赖问题

#### 8.5 Spring解决循环依赖的原理

-
在singleton+setter模式下，Spring将实例化对象和属性赋值清晰的分离，且因为Bean在singleton单例作用域下只能有一个对象，所以可以在实例化对象后，提前将Bean对象"
曝光"给外界

- 源码分析：DefaultSingletonBeanRegistry默认单例Bean注册器

  在DefaultSingletonBeanRegistry类中包含三个重要的属性（Map集合，key都是bean name/id）：
    - singletonObjects：单例对象的缓存，存储Bean对象【一级缓存】
    - earlySingletonObjects：早期单例对象的缓存，存储早期的Bean对象（刚实例化完，未进行属性赋值）【二级缓存】
    - singletonFactories：单例工厂缓存，存储Bean对象对应的Factory对象【三级缓存】

- 在该类中还包含一个方法addSingletonFactory()，这个方法的作用是将Bean对象对应的Factory工厂对象提前曝光（加入三级缓存）

- 当调用该类的getSingleton()方法获取单例Bean的时候，会先从一级缓存中获取，没有则从二级缓存获取，没有再从三级缓存获取，获取后将该早期的Bean对象存入二级缓存，同时移除三级缓存中对应的单例工厂对象

- 当单例对象实例化创建成功后，三级缓存会移除对应的Factory工厂对象，而属性赋值成功后，不会移除二级缓存的早期Bean对象
-
回归正题：Spring解决循环依赖的原理是在任意一个Bean实例化后就将所有Bean曝光在三级缓存中，在获取Bean的时候逐级从缓存中获取，在三级缓存中获取后，会将早期Bean对象存入二级缓存中，同时移除三级缓存对应的Factory工厂对象。

### ⭐9 回顾反射机制

- 方法四要素：
    - 对象
    - 方法
    - 参数
    - 返回值
- 反射获取类：Class.forName(className)
- 反射实例化对象：clazz.getDeclaredConstructor().newInstance()
- 反射获取set方法名："set" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1)
- 反射获取属性类型：clazz.getDeclaredField(propertyName).getType()
- 反射获取方法：clazz.getDeclaredMethod(方法名, 参数类型...)
- 反射调用方法：method.invoke(对象, 参数...)

### 10 回顾注解

- Spring6倡导全注解开发，简化XML配置

#### 10.1 注解的使用

- 注解的定义：@interface
- 注解的属性：类型 变量名() [default 默认属性值]
- 注解的使用：放在类、属性、方法等上方，语法为@注解类型名(属性名=属性值, 属性名=属性值, 属性名=属性值......)
    - 属性名为value的时候可以省略
    - 属性值是数组的时候，要用{}扩起来，单个元素值的时候可以省略{}

#### 10.2 包扫描原理

- 通过包路径获取报下所有类
- 遍历类并读取注解
- 对带有标记注解的类进行操作（如实例化Bean对象并存入Bean集合）

```java
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
```

### ⭐11 手写Spring

#### 11.1 创建Spring的核心接口

- 创建BeanFactory

```java
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
```

- 创建ApplicationContext接口继承BeanFactory

```java
public interface ApplicationContext extends BeanFactory {}
```

- 创建SingletonBeanRegostry接口

```java
/**
 * 单例Bean注册器接口
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);
}
```

#### 11.2 创建核心接口实现类

- 创建ClassPathXmlApplicationContext

```java
/**
 * MySpring容器加载类路径XML配置文件类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {
    private static final Logger logger = LoggerFactory.getLogger(ClassPathXmlApplicationContext.class);

    /**
     * 构造方法：根据类路径加载XML配置文件，解析并初始化所有的Bean对象
     *
     * @param configLocation myspring配置文件路径
     */
    public ClassPathXmlApplicationContext(String configLocation) {
        // 解析XML配置文件，实例化Bean，将Bean曝光(存放到singletonObjects集合中)
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }
}
```

- 创建DefaultSingletonBeanRegistry

```java
/**
 * 默认单例Bean注册器
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private final Map<String, Object> singletonObjects = new HashMap<>();

    public Map<String, Object> getSingletonObjects() {
        return singletonObjects;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return null;
    }
}
```

#### 11.3 解析XML实例化Bean对象

- 解析XML文件反射实例化Bean对象并存入singletonObjects集合中

```java
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
}
```

#### 11.4 Bean对象的属性赋值

- 完成Bean对象的属性赋值

```java
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
```

#### 11.5 扫描注解实例化Bean对象并赋值

- 创建注解@Component

```java
/**
 * 组件注解
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    String value();
}
```

- 创建注解@Value

```java
/**
 * 简单属性赋值
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Value {
    String value();
}
```

- 创建注解@Qualifier（简单实现，省略@Autowired注解）

```java
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
```

- 创建AnnotationConfigApplicationContext实现扫描注解实例化Bean对象并赋值

```java
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
```

#### 11.6 手写Spring总结

- Spring框架的主要设计思想：
    - 工厂模式
    - 动态获取类属性方法，反射实例化Bean
    - 实例化Bean和属性赋值分离
    - 面向接口
    - ......


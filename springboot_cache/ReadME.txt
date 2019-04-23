一.SpringBoot关于缓存的几个重要概念&缓存注解
    Cache               缓存接口，定义缓存操作。实现有： RedisCache、 EhCacheCache、
    ConcurrentMapCache  等
    CacheManager        缓存管理器，管理各种缓存（Cache）组件
    @Cacheable          主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
    @CacheEvict         清空缓存
    @CachePut           保证方法被调用，又希望结果被缓存。
    @EnableCaching      开启基于注解的缓存
    keyGenerator        缓存数据时key生成策略
    serialize           缓存数据时value序列化策略

二.@Cacheable/@CachePut/@CacheEvict 主要的参数
    value:
        缓存的名称，在 spring 配置文件中定义，必须指定至少一个
        例如：@Cacheable(value=”mycache”) 或者 @Cacheable(value={”cache1”,”cache2”}
    key:
        缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合
        例如：@Cacheable(value=”testcache”,key=”#userName”)
    condition：
        缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存/清除缓存，在调用方法之前之后都能判断
        例如：@Cacheable(value=”testcache”,condition=”#userName.length()>2”)
    (@CacheEvict )：
        是否清空所有缓存内容，缺省为 false，如果指定为true，则方法调用后将立即清空所有缓存
        例如：@CachEvict(value=”testcache”,allEntri    es=true)
    beforeInvocation (@CacheEvict)：
        是否在方法执行前就清空，缺省为 false，如果指定为 true，则在方法还没有执行的时候就清空缓存，缺省情况下，如果方法执行抛出异常，则不会清空缓存
        例如：@CachEvict(value=”testcache”，beforeInvocation=true)
    unless (@CachePut)(@Cacheable)：
        用于否决缓存的，不像condition，该表达式只在方法执行之后判断，此时可以拿到返回值result进行判断。条件为true不会缓存， fasle才缓存
        例如：@Cacheable(value=”testcache”,unless=”#result ==null”)
三.缓存SpEL可用的元数据
   1.methodName
        位置：root object
        描述： 当前被调用的方法名
        示例： #root.methodName
   2.method
        位置：root object
        描述： 当前被调用的方法
        示例： #root.method.name
   3.target
        位置：root object
        描述： 当前被调用的目标对象
        示例： #root.target
   4.targetClass
        位置：root object
        描述： 当前被调用的目标对象类
        示例： #root.targetClass
   5.args
        位置：root object
        描述： 当前被调用的方法的参数列表
        示例： #root.args[0]
   6.caches
        位置：root object
        描述： 当前方法调用使用的缓存列表（如@Cacheable(value={"cache1","cache2"})）， 则有两个cache
        示例： #root.caches[0].name
   7.argument name
        位置：evaluation context
        描述： 方法参数的名字. 可以直接 #参数名 ，也可以使用 #p0或#a0 的形式， 0代表参数的索引
        示例： #iban 、 #a0 、 #p0
   8.result
        位置：evaluation context
        描述： 方法执行后的返回值（仅当方法执行之后的判断有效，如‘unless'， ’cache put’的表达式 ’cache evict’的表达式beforeInvocation=false）
        示例： #result
   相关的配置案例说明：https://www.cnblogs.com/yueshutong/p/9381540.html
四.SpringBoot对消息队列(MQ)的支持
         1.异步消息的主要目的是为了系统与系统之间的通信，所谓异步消息即消息发送者无需等待消息接收者的处理以及返回，甚至无需关心消息是否发送成功
        2.在异步消息中有两个很重要的概念，即消息代理和目的地，当消息发送者发送消息之后，消息将由消息代理接管，消息代理保证消息传递到指定目的地。
        3.异步消息主要有两种目的地形式，队列（queue）和主题(topic)，队列用于点对点形式的消息通信，主题用于发布订阅式的消息通信。
     4.1 目的地形式分类
        4.1.1 点对点式
            当消息发送者发送消息，消息代理将消息后将消息放进一个队列里，当有消息接收者来接收消息的时候，消息将从队列中取出传递给消息接收者，这时候队列里就没有了这条消息。
        点对点式确保每一条消息只有唯一的发送者和接收者，但这并不能说明只有一个接收者能够从队列中接收消息，因为队列中有多个消息，点对点式只保证每一条消息只有唯一的发送者和接收者
        4.1.2 发布/订阅式
            发布订阅式是消息发送者发送消息到主题，而多个消息接收者监听这个主题，此时的消息发送者和接收者分别叫做发布者和订阅者
     4.2 企业级消息代理
        JMS即JAVA消息服务，是基于JVM的消息代理规范，ActiveMQ是一个JMS的实现,AMQP也是一个消息代理的规范，他不仅兼容JMS，还支持跨语言和平台，AMQP的主要实现是RabbitMQ
     4.3 Spring以及SpringBoot对消息的支持
        Spring针对JMS和RabbitMQ分别提供了JmsTemplete和RabbitTemplete来发送消息。为我们提供了@JmsListener,@RabbitListener注解来监听消息代理发送的消息。我们分别需要通过@EnableJms和@EnableRabbit来开启支持
      SpringBoot自动配置了上述@EnableJms，@EnableRabbit，JmsTemplete，RabbitTemplete的支持，同时我们可以在application.properties文件中分别以spring.activemq和spring.rabbitmq来分别配置所需的属性。
    4.4 RabbitMQ简介
      博客连接： https://www.cnblogs.com/dwlsxj/p/RabbitMQ.html
    4.5 各种消息队列（消息中间件）的比较
            Kafka： Kafka是LinkedIn开源的分布式发布-订阅消息系统，目前归属于Apache顶级项目。Kafka主要特点是基于Pull的模式来处理消息消费，追求高吞吐量，一开始的目的就是用于日志收集和传输。
        0.8版本开始支持复制，不支持事务，对消息的重复、丢失、错误没有严格要求，适合产生大量数据的互联网服务的数据收集业务。

            RabbitMQ：RabbitMQ是使用Erlang语言开发的开源消息队列系统，基于AMQP协议来实现。AMQP的主要特征是面向消息、队列、路由（包括点对点和发布/订阅）、可靠性、安全。AMQP协议更多用在企业系统内，
        对数据一致性、稳定性和可靠性要求很高的场景，对性能和吞吐量的要求还在其次。

            RocketMQ：RocketMQ是阿里开源的消息中间件，它是纯Java开发，具有高吞吐量、高可用性、适合大规模分布式系统应用的特点。RocketMQ思路起源于Kafka，但并不是Kafka的一个Copy，它对消息的可靠传输及事务性做了优化，
        目前在阿里集团被广泛应用于交易、充值、流计算、消息推送、日志流式处理、binglog分发等场景。
    4.6 RabbitMQ中组件介绍
        1. Server(broker):  接受客户端连接，实现AMQP消息队列和路由功能的进程。
        2. Virtual Host:    其实是一个虚拟概念，类似于权限控制组，一个Virtual Host里面可以有若干个Exchange和Queue，但是权限控制的最小粒度是Virtual Host
        3.Exchange:         接受生产者发送的消息，并根据Binding规则将消息路由给服务器中的队列。ExchangeType决定了Exchange路由消息的行为，例如，在RabbitMQ中，ExchangeType有direct、Fanout和Topic三种，不同类型的Exchange路由的行为是不一样的。
        4.Message Queue：   消息队列，用于存储还未被消费者消费的消息。
        5.Message:          由Header和Body组成，Header是由生产者添加的各种属性的集合，包括Message是否被持久化、由哪个Message Queue接受、优先级是多少等。而Body是真正需要传输的APP数据。
        6.Binding:          Binding联系了Exchange与Message Queue。Exchange在与多个Message Queue发生Binding后会生成一张路由表，路由表中存储着Message Queue所需消息的限制条件即Binding Key。当Exchange收到Message时会解析其Header得到Routing Key，Exchange根据Routing Key与Exchange Type将Message路由到Message Queue。Binding Key由Consumer在Binding Exchange与Message Queue时指定，而Routing Key由Producer发送Message时指定，两者的匹配方式由Exchange Type决定。
        7.Connection:       连接，对于RabbitMQ而言，其实就是一个位于客户端和Broker之间的TCP连接。
        8.Channel:          信道，仅仅创建了客户端到Broker之间的连接后，客户端还是不能发送消息的。需要为每一个Connection创建Channel，AMQP协议规定只有通过Channel才能执行AMQP的命令。一个Connection可以包含多个Channel。之所以需要Channel，是因为TCP连接的建立和释放都是十分昂贵的，如果一个客户端每一个线程都需要与Broker交互，如果每一个线程都建立一个TCP连接，暂且不考虑TCP连接是否浪费，就算操作系统也无法承受每秒建立如此多的TCP连接。RabbitMQ建议客户端线程之间不要共用Channel，至少要保证共用Channel的线程发送消息必须是串行的，但是建议尽量共用Connection。
        9.Command:          AMQP的命令，客户端通过Command完成与AMQP服务器的交互来实现自身的逻辑。例如在RabbitMQ中，客户端可以通过publish命令发送消息，txSelect开启一个事务，txCommit提交一个事务。
五.springboot的任务
    5.1 异步任务
        Service 层的方法上使用  @Async
        Application启动类上使用  @EnableAsync
    5.2 定时任务
        Service 层的方法上使用    @Scheduled(cron = "* * * * * MON-SAT")   表示此方法是定时执行的方法，定时规则就是cron指定的
        Application启动类上使用   @@EnableScheduling  开启定时注解功能
    5.3 邮件任务
        第一步：添加邮件场景启动依赖
               <!--添加邮件场景启动器 start-->
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-email</artifactId>
                </dependency>
                <!--添加邮件场景启动器 end-->
        第二步：在proertis中配置邮箱信息
六.springboot 安全框架
    6.1 配置Spring Security
        6.1.1. 首先当我们要自定义Spring Security的时候我们需要继承自WebSecurityConfigurerAdapter来完成，相关配置重写对应 方法即可。
        6.1.2. 我们在这里注册CustomUserService的Bean，然后通过重写configure方法添加我们自定义的认证方式。
        6.1.3. 在configure(HttpSecurity http)方法中，我们设置了登录页面，而且登录页面任何人都可以访问，然后设置了登录失败地址，也设置了注销请求，注销请求也是任何人都可以访问的。
        6.1.4. permitAll表示该请求任何人都可以访问，.anyRequest().authenticated(),表示其他的请求都必须要有权限认证。
        6.1.5. 这里我们可以通过匹配器来匹配路径，比如antMatchers方法，假设我要管理员才可以访问admin文件夹下的内容，我可以这样来写：.antMatchers("/admin/**").hasRole("ROLE_ADMIN")，也可以设置admin文件夹下的文件可以有多个角色来访问，写法如下：.antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN","ROLE_USER")
        6.1.6. 可以通过hasIpAddress来指定某一个ip可以访问该资源,假设只允许访问ip为210.210.210.210的请求获取admin下的资源，写法如下.antMatchers("/admin/**").hasIpAddress("210.210.210.210")
        6.1.7. 更多的权限控制方式参看下表：


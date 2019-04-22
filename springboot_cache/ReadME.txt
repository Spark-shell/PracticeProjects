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


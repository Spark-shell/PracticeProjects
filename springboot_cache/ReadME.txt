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



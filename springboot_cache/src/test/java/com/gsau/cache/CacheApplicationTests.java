package com.gsau.cache;

import com.gsau.cache.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {
    //专门操作K-V String类型的
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    //专门操作对象K-V
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * Reids数据操作  DEMO
     */
    @Test
    public void testRedis(){
        // redisTemplate.opsForHash();
        // redisTemplate.opsForList();
        // redisTemplate.opsForSet();
        // redisTemplate.opsForZSet();
        //
        //插入字符串类型的
        ValueOperations operations=stringRedisTemplate.opsForValue();
        operations.append("msg","1234564");
       String data= stringRedisTemplate.opsForValue().get("msg");
        System.out.println(data);
        Department department=new Department(2,"一号公寓");
        //测试保存对象
        redisTemplate.opsForValue().set("emp1",department);
        System.out.println(redisTemplate.opsForValue().get("emp1"));
    }
    @Test
    public void contextLoads() {
    }

}

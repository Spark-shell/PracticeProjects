package com.gsau.cache;

import com.gsau.cache.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {
    //专门操作K-V String类型的
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    //专门操作对象K-V
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    JavaMailSenderImpl javaMailSenderImpl;

    /**
     * Reids数据操作  DEMO
     */
    @Test
    public void testRedis() {
        // redisTemplate.opsForHash();
        // redisTemplate.opsForList();
        // redisTemplate.opsForSet();
        // redisTemplate.opsForZSet();
        //
        //插入字符串类型的
        ValueOperations operations = stringRedisTemplate.opsForValue();
        operations.append("msg", "1234564");
        String data = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(data);
        Department department = new Department(2, "一号公寓");
        //测试保存对象
        redisTemplate.opsForValue().set("emp1", department);
        System.out.println(redisTemplate.opsForValue().get("emp1"));
    }

    /**
     * 测试邮件发送，发送简单邮件
     * 发件人、收件人、邮件内容、主题必填写
     */
    @Test
    public void testMail() {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setSubject("啦啦啦");
        mail.setTo("2543979494@qq.com");
        mail.setText("开会");
        mail.setFrom("1456065693@qq.com");
        javaMailSenderImpl.send(mail);
    }

    /**
     * 发送复杂邮件
     */
    @Test
    public void testMail2() throws Exception {
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject("啦啦啦");
        helper.setTo("2543979494@qq.com");
        helper.setText("<h1 sytyle='color:red'></h1>",true);
        helper.setFrom("1456065693@qq.com");
        helper.addAttachment("证据",new File("C:\\Users\\宏银\\Desktop\\在人间.mp4"));
        javaMailSenderImpl.send(mimeMessage);
    }

    @Test
    public void contextLoads() {
    }

}

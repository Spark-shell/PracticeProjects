package com.us.example.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.OpenJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author WangGuoQing
 * @date 2019/4/9 16:49
 * @Desc    @EnableJpaRepositories用来扫描和发现指定包及其子包中的Repository定义
 */
@Configuration
@EnableJpaRepositories("com.us.example.dao")
@EnableTransactionManagement
@ComponentScan
public class JpaConfig {
    @Autowired
    private DataSource dataSource;

    /**
     *  1.EntityManager是JPA中用于增删改查的接口，它的作用相当于一座桥梁，连接内存中的java对象和数据库的数据存储
     *  使用EntityManager中的相关接口对数据库实体进行操作的时候， EntityManager会跟踪实体对象的状态，并决定在
     *  特定时刻将对实体的操作映射到数据库操作上面
     *  2.这里@Bean注解向IOC（spring）容器注入EntityManagerFactory, 然后通过EntityManagerFactory创建EntityManager和JPATransactionManager，
     *  这样通过@Autowired @Qualifier @Resource等注解可以使用EntityManager的相关配置。
     * @return
     */
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();    //创建JPA供应商适配器   vendor n.供应商
        //LocalEntityManagerFactoryBean负责创建一个适合于仅使用JPA进行数据访问环境的EntityManager工厂
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.us.example.bean");
        factory.setDataSource(dataSource);


        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
        jpaProperties.put("hibernate.jdbc.batch_size",50);
        jpaProperties.put("hibernate.show_sql",true);

        factory.setJpaPropertyMap(jpaProperties);
        factory.afterPropertiesSet(); //设置EntityManagerFactory属性
        return factory.getObject();   //返回一个单例EntityManagerFactory
    }

    /**
     * 创建事务管理器
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        //创建Jpa事务管理器JpaTransactionManager
        JpaTransactionManager txManager = new JpaTransactionManager();
        //设置此实例应该管理事务的EntityManagerFactory(就是txManager下产生的EntityManager进行事务管理)
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
}

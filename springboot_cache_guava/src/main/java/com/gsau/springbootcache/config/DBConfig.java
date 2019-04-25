package com.gsau.springbootcache.config;

/**
 * @author WangGuoQing
 * @date 2019/4/9 13:50
 * @Desc    @Configuration: 标注当前类是配置类，并会将当前类内声明的一个或多个以@Bean注解标记的方法的实例纳入到spring容器中，
 * 并且实例名就是方法名。
 */
import java.beans.PropertyVetoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration                   //定义这是一个配置类
public class DBConfig {
    @Autowired
    private Environment env;

    @Bean(name="dataSource")
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("ms.db.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("ms.db.url"));
        dataSource.setUser(env.getProperty("ms.db.username"));
        dataSource.setPassword(env.getProperty("ms.db.password"));
        dataSource.setMaxPoolSize(20);
        dataSource.setMinPoolSize(5);
        dataSource.setInitialPoolSize(10);
        dataSource.setMaxIdleTime(300);
        dataSource.setAcquireIncrement(5);
        dataSource.setIdleConnectionTestPeriod(60);

        return dataSource;
    }
}

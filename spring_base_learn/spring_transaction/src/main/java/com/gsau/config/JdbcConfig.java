package com.gsau.config;


import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@PropertySource("classpath:jdbcConfig.properties")
public class JdbcConfig {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean(name="dataSource")
    public DataSource createDataSource(){
        try {
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setDriverClassName(driver);
            ds.setUrl(url);
            ds.setUsername(username);
            ds.setPassword(password);
            return ds;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    /**
     * @ Author: WangGQ
     * @ Date: 2019/12/28 21:23
     * @ Version: 1.0
     * @ Description: 注入JdbcTemplate,替换SpringBeans.xml中的：
     *             <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
     *                 <property name="dataSource" ref="dataSource"></property>
     *             </bean>
     */
    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public QueryRunner queryRunner(){
        return new QueryRunner();
    }
}

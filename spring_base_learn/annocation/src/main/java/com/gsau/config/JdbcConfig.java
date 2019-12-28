package com.gsau.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
 /**
  * @ Author: WangGQ
  * @ Date: 2019/12/28 21:07
  * @ Version: 1.0
  * @ Description: 数据库配置类
  */
public class JdbcConfig {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    /**
     * 用于创建一个QueryRunner对象
     * @param dataSource
     * @return
     */
    @Bean(name="runner")
    @Scope("prototype")
    public QueryRunner createQueryRunner(@Qualifier("dataSource") DataSource dataSource){
        return new QueryRunner(dataSource);
    }
    /**
     * 创建数据源对象
     * @return
     */
    @Bean(name="dataSource")
    public DataSource createDataSource(){
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Bean(name="dataSource1")
    public DataSource createDataSource1(){
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
            ds.setUser(username);
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
}

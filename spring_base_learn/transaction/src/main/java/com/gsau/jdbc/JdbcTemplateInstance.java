package com.gsau.jdbc;

import com.gsau.factory.BeanFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static java.lang.System.out;

public class JdbcTemplateInstance {
    public static void main(String[] args) {
        // DriverManagerDataSource dmds=new DriverManagerDataSource();
        // dmds.setDriverClassName("com.mysql.jdbc.Driver");
        // dmds.setUrl("jdbc:mysql://localhost:3306/test");
        // dmds.setUsername("root");
        // dmds.setPassword("root");
        // JdbcTemplate jtplte=new JdbcTemplate();
        // jtplte.setDataSource(dmds);
        // jtplte.execute("select * from test.account");
        // out.println("JdbcTemplateInstance.java--main--14-->");
        JdbcTemplate jtplate=BeanFactory.getBean("jdbcTemplate",JdbcTemplate.class);
        out.println("JdbcTemplateInstance.java--main--21-->");
    }
}

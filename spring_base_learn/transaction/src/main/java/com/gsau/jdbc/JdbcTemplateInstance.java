package com.gsau.jdbc;

import com.gsau.dao.impl.AccountDaoImpl;
import com.gsau.factory.BeanFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

public class JdbcTemplateInstance {
    public static void main(String[] args) {
        AccountDaoImpl jtplate=BeanFactory.getBean("accountDaoImpl",AccountDaoImpl.class);
        jtplate.findAccountsByName("12");
    }
}

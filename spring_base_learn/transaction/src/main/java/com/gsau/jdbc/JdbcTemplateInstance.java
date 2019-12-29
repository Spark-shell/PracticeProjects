package com.gsau.jdbc;

import com.gsau.dao.impl.AccountDaoImpl;
import com.gsau.factory.BeanFactory;
import static java.lang.System.out;
public class JdbcTemplateInstance {
    public static void main(String[] args) {
        AccountDaoImpl jtplate=BeanFactory.getBean("accountDaoImpl",AccountDaoImpl.class);
        jtplate.findAccountsByName("12");
        out.println("JdbcTemplateInstance.java--main--18-->"+jtplate.findAll() );
    }
}

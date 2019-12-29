package com.gsau.jdbc;

import com.gsau.dao.IAccountDao;
import com.gsau.dao.impl.AccountDaoImpl;
import com.gsau.factory.BeanFactory;
import static java.lang.System.out;
public class JdbcTemplateInstance {
    public static void main(String[] args) {
        IAccountDao jtplate=BeanFactory.getBean("accountDao",IAccountDao.class);
        jtplate.findAccountsByName("12");
        out.println("JdbcTemplateInstance.java--main--18-->"+jtplate.findAccountsByName("") );
    }
    public void test(){

    }
}

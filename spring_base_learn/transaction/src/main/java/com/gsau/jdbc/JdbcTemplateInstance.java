package com.gsau.jdbc;

import com.gsau.dao.IAccountDao;
import com.gsau.dao.impl.AccountDaoImpl;
import com.gsau.factory.BeanFactory;
import static java.lang.System.out;
public class JdbcTemplateInstance {
    public static void main(String[] args) {
        //放入容器中的组件如果实现了接口,那么从容器取的时候,第二个参数必须传接口的class类型,不然找不到
        IAccountDao jtplate=BeanFactory.getBean("accountDao",IAccountDao.class);
        jtplate.findAccountsByName("12");
        out.println("JdbcTemplateInstance.java--main--18-->"+jtplate.findAccountsByName("") );
    }
}

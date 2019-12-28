package com.gsau.dao.impl;

import com.gsau.dao.AccountDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import javax.naming.Name;
import static java.lang.System.out;
@Repository
@Scope("prototype")
public class AccountDaoImpl1 implements AccountDao {
    @Override
    public void saveAccount() {
        out.println("AccountDaoImpl1.java--saveAccount--10-->"+ "保存成功");
    }
}

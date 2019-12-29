package com.gsau.utils;

import com.gsau.domain.Account;
import org.springframework.jdbc.core.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.lang.System.out;

public class AccountMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setName(rs.getString("name"));
        account.setPrice(rs.getFloat("price"));
        return account;
    }
}

package com.sysoiev.app.util.mappers;

import com.sysoiev.app.model.Account;
import com.sysoiev.app.model.AccountStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper {
    public static Account mapperAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getLong("id"));
        account.setAccountStatus(AccountStatus.valueOf(resultSet.getString("account_status")));
        return account;
    }
}

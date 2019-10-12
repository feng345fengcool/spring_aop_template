package com.indi.dao;

import com.indi.domain.Account;

public interface AccountDao {

    Account findAccountById(Integer id);

    Account findAccountByName(String name);

    void updateAccount(Account account);

}

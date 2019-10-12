package com.indi.biz.impl;

import com.indi.biz.AccountBiz;
import com.indi.dao.AccountDao;
import com.indi.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountBizImpl implements AccountBiz {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public Account findAccountByName(String accountName) {
        return accountDao.findAccountByName(accountName);
    }

    @Override
    public void transfer(String sourceName, String targetName, Integer money) {
        Account source = accountDao.findAccountByName(sourceName);
        Account target = accountDao.findAccountByName(targetName);
        source.setBalance(source.getBalance() - money);
        target.setBalance(target.getBalance() + money);
        accountDao.updateAccount(source);

        int i = 1 / 0;

        accountDao.updateAccount(target);
    }
}

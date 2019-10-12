package com.indi.dao.impl;

import com.indi.dao.AccountDao;
import com.indi.domain.Account;
import com.indi.tx.TLConnection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private QueryRunner runner;

    @Autowired
    private TLConnection tlConn;


    @Override
    public Account findAccountById(Integer id) {
        try {
            return runner.query(tlConn.getConnection()
                    , "select * from account where id = ? "
                    , new BeanHandler<>(Account.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountByName(String name) {
        try {
            List<Account> accounts = runner.query(tlConn.getConnection()
                    , "select * from account where name=?"
                    , new BeanListHandler<>(Account.class), name);
            if (accounts == null || accounts.size() == 0) {
                return null;
            }
            return accounts.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            runner.update(tlConn.getConnection()
                    , "update account set name=?, balance=? where id=?"
                    , account.getName(), account.getBalance(), account.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package com.indi.biz;

import com.indi.domain.Account;

public interface AccountBiz {

    Account findAccountById(Integer accountId);

    Account findAccountByName(String accountName);

    void transfer(String sourceName, String targetName, Integer money);

}

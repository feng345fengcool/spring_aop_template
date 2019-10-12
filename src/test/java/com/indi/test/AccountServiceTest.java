package com.indi.test;

import com.indi.config.SpringConfig;
import com.indi.biz.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class AccountServiceTest {

    @Autowired
    private IAccountService as;

    @Test
    public void testTransfer() {
        as.transfer("zhangsan", "lisi", 100);
    }

}

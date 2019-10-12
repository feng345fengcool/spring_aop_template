package com.indi.tx;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TransactionManager {

    @Autowired
    private TLConnection tlConn;

    @Pointcut("execution(* com.indi.biz.impl.*.*(..))")
    private void pt() {
    }

    public void begin() {
        try {
            tlConn.getConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            tlConn.getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            tlConn.getConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void release() {
        try {
            tlConn.getConnection().close(); // 归还连接
            tlConn.removeConnection(); // 解绑线程
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Around("pt()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        Object rtValue;
        try {
//            1.获取参数
            Object[] args = pjp.getArgs();
//            2.开启事务
            this.begin();
//            3.执行方法
            rtValue = pjp.proceed(args);
//            4.提交事务
            this.commit();
//            返回结果
            return rtValue;
        } catch (Throwable e) {
//            5.回滚事务
            this.rollback();
            throw new RuntimeException(e);
        } finally {
//            6.释放资源
            this.release();
        }
    }
}

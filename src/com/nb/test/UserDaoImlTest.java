package com.nb.test;

import com.nb.dao.UserDao;
import com.nb.dao.impl.UserDaoIml;
import com.nb.pojo.User;
import org.junit.Test;


public class UserDaoImlTest {
    private UserDao userDao=new UserDaoIml();
    @Test
    public void queryUserById() {
        System.out.println(userDao.queryUserById(1));
    }
    @Test
    public void queryForPageItems() {
        System.out.println(userDao.queryForPageItems(1,2,"%2020%"));
    }
    @Test
    public void queryForPageTotalCount() {
        System.out.println(userDao.queryForPageTotalCount("%%"));
    }
    @Test
    public void querUsertByUsername() {
        System.out.println(userDao.queryUserByUsername("111111"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        System.out.println(userDao.queryUserByUsernameAndPassword("111111","111111"));
    }

    @Test
    public void saveUser() {
        userDao.saveUser(new User("333333","333333","333333@qq.com"));

    }
    @Test
    public void addNowBorrowCount() {
        System.out.println(userDao.addNowBorrowCount(1));
    }
    @Test
    public void reduceNowBorrowCount(){
        System.out.println(userDao.reduceNowBorrowCount(1));
    }

    @Test
    public void addBorrowCount() {
        System.out.println(userDao.addBorrowCount(1));
    }

    @Test
    public void addLendCount() {
        System.out.println(userDao.addLendCount(1));
    }

    @Test
    public void addCountLate() {
        System.out.println(userDao.addCountLate(1));
    }

    @Test
    public void isfull() {
        System.out.println(userDao.isfull(1));
    }
}
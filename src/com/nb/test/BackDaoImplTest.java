package com.nb.test;

import com.nb.dao.BackDao;
import com.nb.dao.impl.BackDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BackDaoImplTest {
    private BackDao backDao=new BackDaoImpl();
    @Test
    public void queryPageTotalCount() {
        System.out.println(backDao.queryPageTotalCount());
    }

    @Test
    public void queryPageItems() {
        System.out.println(backDao.queryPageItems(0,5));
    }

    @Test
    public void queryPageTotalCountByBook_Id() {
        System.out.println(backDao.queryPageTotalCountByBook_Id(1));
    }

    @Test
    public void queryPageItemsByBook_Id() {
        System.out.println(backDao.queryPageItemsByBook_Id(0,5,1));
    }
    @Test
    public void updateBorrowFlagByBookId() {
        backDao.updateBorrowFlagByBookId(1);
    }
}
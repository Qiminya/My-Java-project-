package com.nb.test;

import com.nb.dao.BorrowDao;
import com.nb.dao.impl.BorrowDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BorrowDaoImplTest {
BorrowDao borrowDao=new BorrowDaoImpl();
    @Test
    public void queryPageTotalCount() {
        System.out.println(borrowDao.queryPageTotalCount("%%"));
    }

    @Test
    public void queryPageItems() {
        System.out.println(borrowDao.queryPageItems(0,2,"%%"));
    }
    @Test
    public void updateBookStateById() {
borrowDao.updateBookStateById(1);
    }

    @Test
    public void addOneBorrow() {
        borrowDao.addOneBorrow(9,9);
    }
}
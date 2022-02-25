package com.nb.test;

import com.nb.pojo.Borrow;
import com.nb.pojo.Page;
import com.nb.sevice.BorrowService;
import com.nb.sevice.impl.BorrowServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BorrowServiceImplTest {
BorrowService borrowService=new BorrowServiceImpl();
    @Test
    public void pageAllBorrows() {
        System.out.println(borrowService.pageAllBorrows(1,2,"郑次勇"));
    }
}
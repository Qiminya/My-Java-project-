package com.nb.test;

import com.nb.sevice.BackService;
import com.nb.sevice.impl.BackServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BackServiceImplTest {
BackService backService=new BackServiceImpl();
    @Test
    public void pageAllBacks() {
        System.out.println(backService.pageAllBacks(1,2));
    }

    @Test
    public void pageAllBacksByBook_Id() {
        System.out.println(backService.pageAllBacksByBook_Id(1,2,1));
    }
    @Test
    public void userBackBook() {
        System.out.println(backService.userBackBook(1));
    }
}
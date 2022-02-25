package com.nb.test;

import com.nb.dao.ShareDao;
import com.nb.dao.impl.ShareDaoImpl;
import com.nb.pojo.Book;
import com.nb.pojo.Share;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShareDaoImplTest {
ShareDao shareDao=new ShareDaoImpl();
    @Test
    public void queryShareBookById() {
        System.out.println(shareDao.queryShareBookById(1));
    }
    @Test
    public void deleteShareById() {
        shareDao.deleteShareById(1);
    }

    @Test
    public void addBookToBooks() {
        shareDao.addBookToBooks(new Share(1,"三国演义","罗贯中","中国出版社","文学",1,"","sad"));
    }

    @Test
    public void updateSharesBook() {
        shareDao.updateSharesBook(new Share(1,"三国演义","罗贯中","中国出版社","文学",1,"","sad"));
    }

    @Test
    public void queryPageItems() {
        System.out.println(shareDao.queryPageItems(0,2,"三国"));
    }

    @Test
    public void queryPageTotalCount() {
        System.out.println(shareDao.queryPageTotalCount("三国"));
    }
}
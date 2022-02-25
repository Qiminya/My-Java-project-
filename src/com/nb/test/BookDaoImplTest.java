package com.nb.test;

import com.nb.dao.BookDao;
import com.nb.dao.impl.BookDaoImpl;
import com.nb.pojo.Book;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookDaoImplTest {

    BookDao BookDao=new BookDaoImpl();
    @Test
    public void queryBooktById() {
        System.out.println(BookDao.queryBooktById(1));
    }

    @Test
    public void queryBookByName() {
        System.out.println(BookDao.queryBooksByName("钢铁是怎样炼成的"));
    }
    @Test
    public void add(){
        BookDao.addBook(new Book("三国演义","罗贯中","中国出版社","文学",1,"sad"));
    }
    @Test
    public void delete(){
        BookDao.deleteBookById(1);
    }
    @Test
    public void queryForPageTotalCount() {
        System.out.println(BookDao.queryForPageTotalCount("%free%","%java%"));
    }

    @Test
    public void queryForPageItems() {
        System.out.println(BookDao.queryForPageItems(0,3,"%free%","%java%"));
    }

    @Test
    public void queryForPageItemsByName() {
        System.out.println(BookDao.queryForPageItemsByName(1,4,"java"));
    }

    @Test
    public void updateBook(){
        BookDao.updateBook(new Book(31,"三国演义","罗贯中","中国出版社",1,"sad",999,"free"));
    }
    @Test
    public void queryForPageItemsByState(){
        System.out.println(BookDao.queryForPageItemsByState(1,2,"空闲"));
    }
    @Test
    public void queryForPageTotalCountByState(){
        System.out.println(BookDao.queryForPageTotalCountByState("空闲"));
    }
    @Test
    public void queryForPageTotalCountByName(){
        int i=BookDao.queryForPageTotalCountByName("java");
        System.out.println(i);
    }
}